require('dotenv').config()
const express = require('express')

const app = express()


app.get("/", (req, res)=>{
    res.send("<h1>Server is running</h1>")

})

app.post("/register", async (req, res)=>{
    try{
        //get all data from body
        const{firstname, lastname, email, password}= req.body
        //all the data should exists
        if (!(firstname && lastname && email && password)){
            res.status(400).send('All field are required.')

        }

    }catch{

    }
});

module.exports = app