'use strict'

const mongoose = require('mongoose')
const Schema = mongoose.Schema;


const CorpAreaSchema = new Schema({
    areaId: { type: Number, unique: true },
    displayName: { type:String, unique:true },
})

module.exports = mongoose.model('CorpArea', CorpAreaSchema);