const express = require("express");
//const bodyParser = require("body-parser");
const { auth, dbAdmin } = require("./database/firebase-config");
const {
  sendEmailVerification,
  sendPasswordReset,
} = require("./database/send-email-verification");
const jwt = require("jsonwebtoken");

const app = express();
//app.use(bodyParser.json());
app.use(express.json());

app.post("/auth/register", async (req, res) => {
  try {
    const { username, firstname, lastname, email, password } = req.body;

    // Memeriksa apakah semua data terisi
    if (!username || !firstname || !lastname || !email || !password) {
      return res.status(400).json({ message: "All fields are required" });
    }

    // Memeriksa apakah password memenuhi persyaratan
    if (password.length < 8) {
      return res
        .status(400)
        .json({ message: "Password should be at least 8 characters long" });
    }

    // Membuat akun pengguna menggunakan Firebase Authentication
    const userRecord = await auth.createUser({
      email,
      password,
      displayName: `${firstname} ${lastname}`,
      emailVerified: false,
    });

    // Mengirim email verifikasi
    await sendEmailVerification(userRecord);

    // // Menyimpan data pengguna ke Firebase Database
    // await dbAdmin.ref(`users/${userRecord.uid}`).set({
    //   username,
    //   firstname,
    //   lastname,
    //   email,
    // });

    //Simpan data user di firebase
    const userRef = dbAdmin.collection("users").doc(userRecord.uid);
    await userRef.set({
      username,
      firstname,
      lastname,
      email,
      password,
    });

    // Generate JWT token
    const token = jwt.sign({ uid: userRecord.uid }, "your-secret-key");

    res.status(200).json({ message: "Registration successful", token });
  } catch (error) {
    console.error("Registration error:", error);
    res.status(500).json({ message: "Registration failed" });
  }
});

//Validasi token JWT for User
function verifyToken(req, res, next) {
  const token = req.headers.authorization;

  if (!token) {
    return res.status(401).json({ message: "Token not provided" });
  }

  jwt.verify(token, "your-secret-key", (err, decoded) => {
    if (err) {
      return res.status(401).json({ message: "Invalid token" });
    }

    // Tambahkan decoded token ke objek request untuk digunakan di endpoint berikutnya
    req.user = decoded;
    next();
  });
}

app.post("/auth/login", async (req, res) => {
  try {
    const { email, password } = req.body;

    // Memeriksa apakah semua data terisi
    if (!email || !password) {
      return res
        .status(400)
        .json({ message: "Email and password are required" });
    }

    // Mengautentikasi pengguna menggunakan Firebase Authentication
    const userRecord = await auth.getUserByEmail(email);

    // Memeriksa apakah pengguna telah memverifikasi email
    if (!userRecord.emailVerified) {
      return res.status(400).json({ message: "Email not verified" });
    }

    const token = jwt.sign({ uid: userRecord.uid }, "your-secret-key");

    //Simpan token terbaru ke Firestore
    const userRef = dbAdmin.collection("users").doc(userRecord.uid);
    await userRef.update({ token: token });

    res.status(200).json({ message: "Login successful", token });
  } catch (error) {
    console.error("Login error:", error);
    res.status(500).json({ message: "Login failed" });
  }
});

//Validasi Email --> Forgot Password
app.post("/auth/users/forgot-password", async (req, res) => {
  const { email } = req.body;

  // Memeriksa apakah email terdaftar
  const userSnapshot = await dbAdmin
    .collection("users")
    .where("email", "==", email)
    .get();

  if (userSnapshot.empty || userSnapshot.docs[0].data().email !== email) {
    return res.status(404).json({ message: "User not found" });
  }

  const userRecord = await auth.getUserByEmail(email);

  // Lanjutkan dengan pengiriman email verifikasi atau token reset password
  await sendPasswordReset(userRecord);
});

// Membuat kata sandi baru
app.put("/auth/users/reset-password/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const { newPassword, confirmPassword } = req.body;

    // Memeriksa apakah data terisi
    if (!newPassword || !confirmPassword) {
      return res.status(400).json({ message: "New password is required" });
    }

    // Memeriksa apakah kata sandi baru sesuai dengan konfirmasi
    if (newPassword !== confirmPassword) {
      return res.status(400).json({ message: "Passwords do not match" });
    }

    // Memeriksa panjang kata sandi baru
    if (newPassword.length < 8) {
      return res
        .status(400)
        .json({ message: "Password should be at least 8 characters long" });
    }

    // Memeriksa apakah pengguna dengan id yang diberikan ada dalam koleksi "users"
    const userSnapshot = await dbAdmin.collection("users").doc(id).get();
    if (!userSnapshot.exists) {
      return res.status(404).json({ message: "User not found" });
    }

    // Memperbarui kata sandi pengguna
    await dbAdmin.collection("users").doc(id).update({ password: newPassword });

    res.status(200).json({ message: "Password reset successful" });
  } catch (error) {
    console.error("Reset password error:", error);
    res.status(500).json({ message: "Reset password failed" });
  }
});

// user logout
app.post("/auth/logout", async (req, res) => {
  try {
    const { token } = req.body;

    // Hapus token dari Firestore
    const userSnapshot = await dbAdmin
      .collection("users")
      .where("token", "==", token)
      .get();

    if (!userSnapshot.empty) {
      const userRef = userSnapshot.docs[0].ref;
      await userRef.update({ token: "" });
    }

    res.status(200).json({ message: "Logout successful" });
  } catch (error) {
    console.error("Logout error:", error);
    res.status(500).json({ message: "Logout failed" });
  }
});

//Memunculkan nama pengguna di user profile
app.get("/auth/users/profile", async (req, res) => {
  try {
    const { token } = req.headers; // atau gunakan metode autentikasi sesuai kebutuhan
    // Ambil data pengguna berdasarkan token dari Firestore
    const userSnapshot = await dbAdmin
      .collection("users")
      .where("token", "==", token)
      .get();

    if (!userSnapshot.empty) {
      const userData = userSnapshot.docs[0].data();
      const { firstname, lastname } = userData;
      res.status(200).json({ firstname, lastname });
    } else {
      res.status(404).json({ message: "User not found" });
    }
  } catch (error) {
    console.error("Fetch user profile error:", error);
    res.status(500).json({ message: "Failed to fetch user profile" });
  }
});

//Memunculkan data user di halaman personal information
app.get("/auth/users/profile-information", async (req, res) => {
    try {
      const { token } = req.headers; // atau gunakan metode autentikasi sesuai kebutuhan
      // Ambil data pengguna berdasarkan token dari Firestore
      const userSnapshot = await dbAdmin
        .collection("users")
        .where("token", "==", token)
        .get();
  
      if (!userSnapshot.empty) {
        const userData = userSnapshot.docs[0].data();
        const { username, firstname, lastname, email } = userData;
        res.status(200).json({ username, firstname, lastname, email });
      } else {
        res.status(404).json({ message: "User not found" });
      }
    } catch (error) {
      console.error("Fetch user profile error:", error);
      res.status(500).json({ message: "Failed to fetch user profile" });
    }
  });

module.exports = app;
