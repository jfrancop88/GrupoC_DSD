'use strict'

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const CVrequestSchema = new Schema({
    numeroRQ: { type: String, unique: true },
    creadopor : { type: String },
    restaurante: { type: String },
    puesto: { type: String },
    observacion: { type: String },
    motivoCobertura: { type: String },
    nombreAnfitrionCesante: { type: String },
    categoria: { type: String },
    modalidad: { type: String },
    turno: { type: String },
	horario: { type: String }, 
    sexo: { type: String },
    cantidadRQ: { type: String },
    lider: { type: String },
    sof: { type: String },
    fechaCreacion: { type:  Date, default: Date.now() },
    asistenteSeleccion: { type: String },
    edad: { type: String },
    responsableReclutamiento: { type: String },
    fechaRequerimiento: { type:  Date },
    status: { type: String },
});

module.exports = mongoose.model('CVrequest', CVrequestSchema);