const express = require("express");
const { auth, dbAdmin } = require("./database/firebase-config");
const {
  sendEmailVerification,
  sendPasswordReset,
} = require("./database/send-email-verification");
const jwt = require("jsonwebtoken");

const app1 = express();
app1.use(express.json());

app1.post("/auth/register", async (req, res) => {
  try {
    const { username, firstname, lastname, email, password, user_category } =
      req.body;

    if (!username || !firstname || !lastname || !email || !password) {
      return res.status(400).json({ message: "All fields are required" });
    }

    if (password.length < 8) {
      return res
        .status(400)
        .json({ message: "Password should be at least 8 characters long" });
    }

    const availableCategories = [
      "Bahari",
      "Budaya",
      "Taman Nasional",
      "Taman Hiburan",
      "Cagar Alam",
      "Desa Wisata",
    ];
    const category1 = user_category[0];
    const category2 = user_category[1];
    const category3 = user_category[2];

    if (!user_category.every((category) => availableCategories.includes(category))) {
      return res.status(400).json({ message: "Invalid category" });
    }

    const userSnapshot = await dbAdmin.collection("users").get();
    const userCount = userSnapshot.size;

    const userRecord = await auth.createUser({
      email,
      password,
      displayName: `${firstname} ${lastname}`,
      emailVerified: false,
    });

    await sendEmailVerification(userRecord);

    const userRef = dbAdmin.collection("users").doc(userRecord.uid);
    await userRef.set({
      user_id: userCount + 1,
      username,
      firstname,
      lastname,
      email,
      password,
      user_category:[category1, category2, category3]// Menyimpan preferensi kategori ke database
    });
    
    res.status(200).json({ message: "Registration successful" });
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

app1.post("/auth/login", async (req, res) => {
  try {
    const { email, password } = req.body;

    if (!email || !password) {
      return res
        .status(400)
        .json({ message: "Email and password are required" });
    }

    const userRecord = await auth.getUserByEmail(email);

    if (!userRecord.emailVerified) {
      return res.status(400).json({ message: "Email not verified" });
    }

    const token = jwt.sign({ uid: userRecord.uid }, "your-secret-key");

    const userRef = dbAdmin.collection("users").doc(userRecord.uid);
    await userRef.update({ token: token });

    res.status(200).json({ message: "Login successful", token });
  } catch (error) {
    console.error("Login error:", error);
    res.status(500).json({ message: "Login failed" });
  }
});

// Validasi Email --> Forgot Password
app1.post("/auth/users/forgot-password", async (req, res) => {
  const { email } = req.body;

  // Memeriksa apakah email terdaftar
  const userSnapshot = await dbAdmin
    .collection("users")
    .where("email", "==", email)
    .get();

  if (userSnapshot.empty || userSnapshot.docs[0].data().email !== email) {
    return res.status(404).json({ message: "User not found" });
  } else {
    res.status(200).json({ message: "User found" });
  }

  try {
    const userRecord = await auth.getUserByEmail(email);

    // Lanjutkan dengan pengiriman email verifikasi atau token reset password
    await sendPasswordReset(userRecord);

    res.status(200).json({ message: "Password reset email sent" });
  } catch (error) {
    console.error("Forgot password error:", error);
    res.status(500).json({ message: "Password reset failed" });
  }
});


// Membuat kata sandi baru
app1.put("/auth/users/reset-password/:id", async (req, res) => {
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
app1.post("/auth/logout", async (req, res) => {
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
app1.get("/auth/users/profile", async (req, res) => {
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
app1.get("/auth/users/profile-information", async (req, res) => {
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

module.exports = app1;
