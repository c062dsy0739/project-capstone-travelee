const connection = require('./database');

function selectQuery() {
  return new Promise((resolve, reject) => {
    const selectQuery = 'SELECT * FROM event_description';
    connection.query(selectQuery, (error, results) => {
      if (error) {
        reject(error);
      } else {
        resolve(results);
      }
    });
  });
}

module.exports = selectQuery;
