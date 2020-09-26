'use strict'

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ReclutamientoRqSchema = new Schema({
    numeroRQ: { type: String, unique: true },
    dni: { type: String },
    apellidos: { type: String },
    nombres: { type: String },
    sexo: { type: String },
    telefono: { type: String },
    correo: { type: String },
    distrito: { type: String },
    modoContacto: { type: String },
    status: { type: String },
	archivoCV: { type: String }, 
    status: { type: String },
});

module.exports = mongoose.model('ReclutamientoRQ', ReclutamientoRqSchema);