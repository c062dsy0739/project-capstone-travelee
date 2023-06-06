const mysql = require("mysql");

const connection = mysql.createConnection({
  host: "34.101.210.80",
  user: "root",
  password: "12345",
  database: "place",
});

console.log('Success connection to database Cloud SQL')

module.exports = connection;