const cors = require('cors');
const express = require('express');
const bodyParser = require('body-parser');

const mysql = require('mysql');

const mysqlConnection = mysql.createConnection({
    host: process.env.DB_HOST,
    user : process.env.DB_USER,
    password: process.env.DB_PASS,
    port: process.env.DB_PORT,
    database: process.env.DB_NAME
    
});

require('dotenv').config();

const app = express();

app.use(cors());
app.use(bodyParser.json());

mysqlConnection.connect((err)=> {
    if(!err)
    console.log('Connection Established Successfully');
    else
    console.log('Connection Failed!'+ JSON.stringify(err,undefined,2));
    });

const port = process.env.PORT;
app.listen(port, () => console.log(`Listening on port ${port}..`));

module.exports = app;