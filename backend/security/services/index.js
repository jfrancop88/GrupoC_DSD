'use strict'

const jwt = require('jwt-simple');
const moment = require('moment');
const config = require('../config');
const fs = require('fs');
const xml2js = require('xml2js');
const parser = new xml2js.Parser();

function createToken (user) {
    const payload = {
        sub: user._id,
        iat: moment().unix(),
        exp: moment().add(14, 'days').unix()
    }

    return jwt.encode(payload, config.SECRET_TOKEN)
}

function decodeToken (token) {
    const decoded = new Promise((resolve, reject) =>{
        try{
            const payload = jwt.decode(token, config.SECRET_TOKEN)
            if (payload.exp <= moment.unix()){
            reject({
                status : 401,
                message: "El token ha expirado"
            })
            }
            resolve(payload.sub)

        } catch(err){
            reject({
                status: 500,
                message: "Invalid Token"
            })
        }
    })
return decoded
}

function parseXML(req){
    const xmldata = new Promise(function(resolve, reject){
        var root = './uploads/'+req;

        try{ 
        fs.readFile(root, "utf-8", (err, text) => {
            if(err) reject(err);
            //console.log(text);
            
            parser.parseString(text, (err, result) => {               
                var nuFactura = result['Invoice']['cbc:ID'][0];
                var tipoDoc = result['Invoice']['cbc:InvoiceTypeCode'][0];
                var feFactura = result['Invoice']['cbc:IssueDate'][0];
                var currency = result['Invoice']['cbc:DocumentCurrencyCode'][0];
                var igv = result['Invoice']['cac:TaxTotal'][0]['cbc:TaxAmount'][0]['_'];
                var totalAmount = result['Invoice']['cac:InvoiceLine'][0]['cac:Price'][0]['cbc:PriceAmount'][0]['_'];

                const data = {
                    nuFactura : nuFactura,
                    tipoDoc : tipoDoc,
                    feFactura : feFactura,
                    currency : currency,
                    igv : igv,
                    totalAmount: totalAmount
                }
                
                resolve(data);
            });    
         
        });
          } catch(err){
            reject({
                status: 500,
                message: "Datos vacíos"
            }) 
      }

    })

    return xmldata
        
}

function extractData(xmldata){
// Funcion para el .then de la Promise
    const data2 = {
        nuFactura : xmldata.nuFactura,
        tipoDoc : xmldata.tipoDoc,
        feFactura: xmldata.feFactura,
        currency: xmldata.currency,
        igv: xmldata.igv,
        totalAmount: xmldata.totalAmount
    };
    
    return data2;
}

module.exports = {
    createToken,
    decodeToken,
    parseXML,
    extractData
}