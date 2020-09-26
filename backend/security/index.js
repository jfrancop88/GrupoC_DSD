'use strict'

var mongoose = require('mongoose');
var app = require('./app');
var port = 3800;

mongoose.Promise = global.Promise;

mongoose.connect('mongodb://localhost:27017/RRHH', { useCreateIndex: true, useNewUrlParser: true })
.then(()=>{
    console.log("Conectado correctamente a la DB");  

    // Creacion del servidor
    app.listen(port, () => {
        console.log("Conectado el servidor al puerto: "+ port)
    });

})
.catch(err => console.log(err));


