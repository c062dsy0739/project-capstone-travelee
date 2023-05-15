const app = require('./app')
// const bodyParser = require('body-parser');
const port = process.env.PORT || 6000;
app.listen(port, ()=>{
  console.log(`Server is running on port: ${port}`)
})


