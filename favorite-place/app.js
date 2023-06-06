const express = require('express');
const { auth, dbUser } = require('./firebase/firebase-config');
const connection = require('./mysql/database');
const selectQuery = require('./mysql/dataparser');
const jwt = require('jsonwebtoken');

// Inisialisasi aplikasi Express
const app = express();
app.use(express.json());

// Middleware untuk verifikasi token JWT
function verifyToken(req, res, next) {
  const token = req.headers.authorization;

  if (!token) {
    return res.status(401).json({ message: 'Token not provided' });
  }

  jwt.verify(token, 'your-secret-key', (err, decoded) => {
    if (err) {
      return res.status(401).json({ message: 'Invalid token' });
    }

    // Tambahkan decoded token ke objek request untuk digunakan di endpoint berikutnya
    req.user = decoded;
    next();
  });
}

// Endpoint untuk menyimpan favorite place ke Firestore
app.post('/sync-data', verifyToken, async (req, res) => {
  const { userId, placeId } = req.body;

  try {
    // Menyimpan favorite place di dokumen pengguna
    const userRef = dbUser.collection('users').doc(userId);
    await userRef.update({
      favoritePlace: placeId
    });

    console.log('Favorite place berhasil disimpan di Firestore');
    res.json({ message: 'Favorite place berhasil disinkronisasi ke Firestore' });
  } catch (error) {
    console.error('Error saat menyimpan favorite place ke Firestore:', error);
    res.status(500).json({ error: 'Internal server error' });
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
