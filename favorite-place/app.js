const express = require("express");
const { dbAdmin } = require("../authentication/database/firebase-config");
const connection = require("../search-engine/config/database");
const selectQuery = require("../search-engine/config/dataparser");
const jwt = require("jsonwebtoken");

// Inisialisasi aplikasi Express
const app3 = express();
app3.use(express.json());

// Middleware untuk verifikasi token JWT
function verifyToken(req, res, next) {
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
  
// Endpoint untuk menyimpan favorite place ke Firestore
app3.post("/favorite_place/:id", verifyToken, async (req, res) => {
    const { id } = req.params;
  
    // Melakukan query untuk mendapatkan data berdasarkan place_id
    connection.query(
      "SELECT * FROM place_destination WHERE place_id = ?",
      [id],
      async (error, results) => {
        if (error) {
          console.error(error);
          return res.status(500).json({
            status: "error",
            message: "Internal server error",
          });
        }
  
        const placeData = JSON.parse(JSON.stringify(results[0]));
  
        const userId = req.user.uid;
  
        try {
          // Menyimpan favorite place di dokumen pengguna
          const userRef = dbAdmin.collection("users").doc(userId);
          const favoritePlaceRef = userRef.collection("favoritePlace");
  
          await favoritePlaceRef.add({
            placeId: placeData.place_id,
            placeName: placeData.place_name,
            placeDescription: placeData.place_description,
            placeCategory:placeData.category,
            placeCity:placeData.city,
            placePrice:placeData.price,
            placeRating:placeData.rating,
            placeDescription_Location:placeData.description_location,
            placeImg:placeData.place_img,
            placeGalleryPhotoImg1:placeData.gallery_photo_img1,
            placeGalleryPhotoImg2:placeData.gallery_photo_img2,
            placeGalleryPhotoImg3:placeData.gallery_photo_img3
          });
  
          console.log("Favorite place berhasil disimpan di Firestore");
          res.json({
            message: "Favorite place berhasil disinkronisasi ke Firestore",
          });
        } catch (error) {
          console.error(
            "Error saat menyimpan favorite place ke Firestore:",
            error
          );
          res.status(500).json({ error: "Internal server error" });
        }
      }
    );
});


module.exports = app3;
