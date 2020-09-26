'use strict'

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const PostulanteSchema = new Schema({
    dni: { type: String, unique: true },
    apellidos: { type: String },
    nombres: { type: String },
    telefono: { type: String },
    email: { type: String },
    distrito: { type: String },
    fechaRecepcionCV: { type:  Date, default: Date.now() },
    sexoPostulante: { type: String },
    edadPostulante: { type: String },
    statusCV: { type: String },
});

module.exports = mongoose.model('Postulante', PostulanteSchema);