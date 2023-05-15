const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = process.env.PORT || 5000;

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// routes
const registerRoute = require('./routes/register');
app.use('/register', registerRoute);

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
