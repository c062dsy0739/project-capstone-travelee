const express = require("express");
const app4 = express();
const connection = require("./config/database");
const selectQuery = require("./config/dataparser");
const jwt = require("jsonwebtoken");

app4.use(express.json());

// Middleware untuk verifikasi token JWT
function verifyToken (req, res, next) {
  const token = req.headers.authorization;

  if (!token) {
    return res.status(401).json({ message: 'Token not provided' });
  }

  jwt.verify(token, 'your-secret-key', (err, payload) => {
    if (err) {
      return res.status(401).json({ message: 'Invalid token' });
    }

    // Tambahkan payload token ke objek request untuk digunakan di endpoint berikutnya
    req.user = payload;
    next();
  });
}

// Mengatur endpoint untuk mengambil semua data
app4.get("/event", verifyToken, (req, res) => {
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

module.exports = app4;
