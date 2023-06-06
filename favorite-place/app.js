const express = require("express");
const { auth, dbUser } = require("./firebase/firebase-config");
const connection = require("./mysql/database");
const selectQuery = require("./mysql/dataparser");
const jwt = require("jsonwebtoken");

// Inisialisasi aplikasi Express
const app = express();
app.use(express.json());

// Endpoint untuk login
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

    // Simpan token terbaru ke Firestore
    const userRef = dbUser.collection("users").doc(userRecord.uid);
    await userRef.update({ token: token });

    res.status(200).json({ message: "Login successful", token });
  } catch (error) {
    console.error("Login error:", error);
    res.status(500).json({ message: "Login failed" });
  }
});

// Middleware untuk verifikasi token JWT
function verifyToken(req, res, next) {
  const token = req.headers.authorization;

  if (!token) {
    return res.status(401).json({ message: "Token not provided" });
  }

  // Mengambil decoded token dari Firestore menggunakan token yang disimpan saat login
  const userRef = dbUser.collection("users").doc(decoded.uid);
  userRef
    .get()
    .then((snapshot) => {
      const userData = snapshot.data();
      if (userData && userData.token === token) {
        // Token valid, tambahkan decoded token ke objek request
        req.user = decoded;
        next();
      } else {
        // Token tidak valid
        return res.status(401).json({ message: "Invalid token" });
      }
    })
    .catch((error) => {
      console.error("Error while verifying token:", error);
      res.status(500).json({ error: "Internal server error" });
    });
}

// Endpoint untuk menyimpan favorite place ke Firestore
app.post("/sync-data", verifyToken, async (req, res) => {
  const { placeId } = req.body;
  const userId = req.user.uid;

  try {
    // Menyimpan favorite place di dokumen pengguna
    const userRef = dbUser.collection("users").doc(userId);
    const favoritePlaceRef = userRef.collection("favoritePlace");

    await favoritePlaceRef.add({
      placeId: placeId,
    });

    console.log("Favorite place berhasil disimpan di Firestore");
    res.json({
      message: "Favorite place berhasil disinkronisasi ke Firestore",
    });
  } catch (error) {
    console.error("Error saat menyimpan favorite place ke Firestore:", error);
    res.status(500).json({ error: "Internal server error" });
  }
});

// Mengatur endpoint untuk mengambil semua data
app.get("/place", (req, res) => {
  selectQuery()
    .then((results) => {
      res.json({
        status: "success",
        data: results,
      });
    })
    .catch((error) => {
      console.error(error);
      res.status(500).json({
        status: "error",
        message: "Internal server error",
      });
    });
});

// Mengatur endpoint untuk mengambil data berdasarkan place_id
app.get("/place/:id", (req, res) => {
  const placeId = req.params.id;

  // Melakukan query untuk mendapatkan data berdasarkan place_id
  connection.query(
    "SELECT * FROM place_destination WHERE place_id = ?",
    [placeId],
    (error, results) => {
      if (error) {
        console.error(error);
        res.status(500).json({
          status: "error",
          message: "Internal server error",
        });
      } else {
        res.json(results);
      }
    }
  );
});

module.exports = app;
