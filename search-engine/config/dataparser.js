const csv = require("csv-parser");
const fs = require("fs");

const dataParser = () => {
  return new Promise((resolve, reject) => {
    const results = [];
    fs.createReadStream("eco-place.csv")
      .pipe(csv())
      .on("data", (data) => {
        results.push(data);
      })
      .on("end", () => {
        console.log("Proses membaca file CSV selesai");
        resolve(results);
      })
      .on("error", (error) => {
        reject(error);
      });
  });
};

module.exports = dataParser;
