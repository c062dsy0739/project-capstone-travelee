const express = require("express");
const app4 = express();
const connection = require("./config/database");
const selectQuery = require("./config/dataparser");

app4.use(express.json());

// Mengatur endpoint untuk mengambil semua data
app4.get("/event", (req, res) => {
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