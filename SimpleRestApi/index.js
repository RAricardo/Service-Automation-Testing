var express = require("express");
var app = express();
let bodyParser = require('body-parser');
const {MongoClient} = require('mongodb');
let apiRoutes = require("./routes")
let mongoose = require('mongoose');

const uri = "mongodb+srv://ricardoazo:Ginnojo2020*@cluster0.szl3u.mongodb.net/RestApi?retryWrites=true&w=majority";
mongoose.connect(uri, { useNewUrlParser: true, useUnifiedTopology: true});
var db = mongoose.connection;

app.use(function(req, res, next) {
    if (req.headers['content-type'] === 'application/json;') {
      req.headers['content-type'] = 'application/json';
    }
    next();
  });

app.use(bodyParser.urlencoded({
    extended: true
 }));
 app.use(bodyParser.json());

app.use('/api', apiRoutes)



app.listen(3000, () => {
 console.log("Server running on port 3000");
});