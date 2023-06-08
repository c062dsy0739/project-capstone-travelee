const express = require("express");
const app2 = express();
const connection = require("./config/database");
const selectQuery = require("./config/dataparser");

app2.use(express.json());

// Mengatur endpoint untuk mengambil semua data
app2.get("/place", (req, res) => {
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
app2.get("/place/:id", (req, res) => {
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
      }

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
app2.get("/place/search/name", (req, res) => {
  const placeName = req.query.name;

  // Melakukan query untuk mencari data berdasarkan place_name
  connection.query(
    "SELECT * FROM place_destination WHERE place_name LIKE ?",
    [`%${placeName}%`],
    (error, results) => {
      if (error) {
        console.error(error);
        res.status(500).json({
          status: "error",
          message: "Internal server error",
        });
      }

      res.json({
        status: "success",
        data: results,
      });
    }
  );
});

// Mengatur endpoint untuk mencari data berdasarkan city
app2.get("/place/search/city", (req, res) => {
  const city = req.query.city;

  // Melakukan query untuk mencari data berdasarkan city
  connection.query(
    "SELECT * FROM place_destination WHERE city LIKE ?",
    [`%${city}%`],
    (error, results) => {
      if (error) {
        console.error(error);
        res.status(500).json({
          status: "error",
          message: "Internal server error",
        });
      }

      res.json({
        status: "success",
        data: results,
      });
    }
  );
});

// Mengatur endpoint untuk mencari data berdasarkan city
app2.get("/place/search/category", (req, res) => {
    const category = req.query.category;
  
    // Melakukan query untuk mencari data berdasarkan city
    connection.query(
      "SELECT * FROM place_destination WHERE category LIKE ?",
      [`%${category}%`],
      (error, results) => {
        if (error) {
          console.error(error);
          res.status(500).json({
            status: "error",
            message: "Internal server error",
          });
        }
  
        res.json({
          status: "success",
          data: results,
        });
      }
    );
  });

module.exports = app2;
