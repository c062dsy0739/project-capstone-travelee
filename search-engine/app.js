const express = require("express");
const app = express();
app.use(express.json());

const connection = require("./config/database");
const dataParser = require("./config/dataparser");


dataParser()
  .then((results) => {
    console.log(results); // Menampilkan data yang di-parse dari file CSV
    // Melakukan operasi lain dengan data yang di-parse jika diperlukan
  })
  .catch((error) => {
    console.error(error); // Menampilkan pesan kesalahan jika terjadi error
  });

// Mengatur endpoint untuk mengambil data berdasarkan place_id
app.get("/place/:id", (req, res) => {
  const placeId = req.params.id;

  // Melakukan query untuk mendapatkan data berdasarkan place_id
  connection.query(
    "SELECT * FROM place_destination WHERE place_id = ?",
    [placeId],
    (error, results) => {
      if (error) throw error;

      if (results.length > 0) {
        res.json({
          status: "success",
          data: results[0],
        });
      } else {
        res.json({
          status: "not found",
        });
      }
    }
  );
});

// Mengatur endpoint untuk mencari data berdasarkan place_name
app.get("/place/search/name", (req, res) => {
  const placeName = req.query.name;

  // Melakukan query untuk mencari data berdasarkan place_name
  connection.query(
    "SELECT * FROM place_destination WHERE place_name LIKE ?",
    [`%${placeName}%`],
    (error, results) => {
      if (error) throw error;

      res.json({
        status: "success",
        data: results,
      });
    }
  );
});

// Mengatur endpoint untuk mencari data berdasarkan city
app.get("/place/search/city", (req, res) => {
  const city = req.query.city;

  // Melakukan query untuk mencari data berdasarkan city
  connection.query(
    "SELECT * FROM place_destination WHERE city LIKE ?",
    [`%${city}%`],
    (error, results) => {
      if (error) throw error;

      res.json({
        status: "success",
        data: results,
      });
    }
  );
});

module.exports = app;
