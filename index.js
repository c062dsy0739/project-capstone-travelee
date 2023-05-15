const app = require('./app')
// const bodyParser = require('body-parser');
process.env.PORT = 7000
app.listen(PORT, ()=>{
  console.log(`Server is running on port: ${PORT}`)
})




// // parse application/x-www-form-urlencoded
// app.use(bodyParser.urlencoded({ extended: false }));

// // parse application/json
// app.use(bodyParser.json());

// routes
// const registerRoute = require('./routes/register');
// app.use('/register', registerRoute);

