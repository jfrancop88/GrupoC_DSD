'use strict'

const mongoose = require('mongoose');
const Schema = mongoose.Schema;
//require ('./company-areas');
//const CorpArea = mongoose.model('CorpArea')


const PurchaseSchema = new Schema({
    nuRQ: { type: String },
    nuOC: { type: String, unique: true },
    moneda: { type: String },
    subTotalOC: { type: String },
    igvOC: { type: String },
    amount: { type: String },
    createdAt: { type: String },
    createdby: { type: String },
	notifyAt: { type: Date, default: Date.now() }, 
    idProvider: { type: String }, //nuRUC
    noRazonSocial: { type: String },
    rootXML: { type: String },
    rootPDF: { type: String },
    rootSustentos: { type: String },
    noFactura: { type: String },
    feFactura: { type: String },
    currency: { type: String },
    tipoDoc: { type: String },
    subTotal: { type: String },
    igv: { type: String },
    totalAmount: { type: String },
    condPago: { type: String },
    status: { type: String },
    aprovedAt: { type: Date, default: Date.now() },
    area: { type: String },
    notes: { type: String }
});

module.exports = mongoose.model('PurchaseOrder', PurchaseSchema);