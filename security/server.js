'use strict'

const app = require('./app');


mysqlConnection.connect((err)=> {
    if(!err)
    console.log('Connection Established Successfully');
    else
    console.log('Connection Failed!'+ JSON.stringify(err,undefined,2));
    });


const port = process.env.PORT || 8080;
app.listen(port, () => console.log(`Listening on port ${port}..`));