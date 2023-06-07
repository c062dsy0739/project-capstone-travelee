const express = require("express");
const app = express();
const connection = require("./config/database");
const selectQuery = require("./config/dataparser");

app.use(express.json());

// Mengatur endpoint untuk mengambil semua data
app.get("/event", (req, res) => {
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

module.exports = app;