'use strict'

const mongoose = require('mongoose');
const PurchaseOrder = require('../models/purchaseOrder');
const CorpArea = require('../models/company-areas');
const service = require('../services')
const fs = require('fs');
const xml2js = require('xml2js');
const parser = new xml2js.Parser();

var controller = {
    
    importPObyUserP: function(req, res) {
        PurchaseOrder.find({area: req.body.area, status: '1'}, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existe el usuario'})

            req.user = user
            res.status(200).send({
                success : true,
                message : 'Ordenes importadas de forma correcta',
                data : user
            })
        }) 
    },

    importPObyUserA: function(req, res) {
        PurchaseOrder.find({area: req.body.area, status: '0'}, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existe el usuario'})

            req.user = user
            res.status(200).send({
                success : true,
                message : 'Ordenes importadas de forma correcta',
                data : user
            })
        }) 
    },

    importPObyProviderA: function(req, res) {
        PurchaseOrder.find({idProvider: req.body.idProvider, status: '1'}, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existen el usuario'})

            
            req.user = user
            res.status(200).send({
                success : true,
                message : 'Ordenes importadas de forma correcta',
                data : user
            })
        }) 
    },


    importPObyProviderH: function(req, res) {
        PurchaseOrder.find({idProvider: req.body.idProvider, status: '0'}, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existen el usuario', data: '0'})

            
            req.user = user
            res.status(200).send({
                success : true,
                message : 'Ordenes importadas de forma correcta',
                data : user
            })
        }) 
    },


    importPObyNo: function(req, res) {
        PurchaseOrder.find({nuOC: req.body.nuOC}, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existen la orden'})

            
            req.user = user
            res.status(200).send({
                success : true,
                message : 'Orden importada de forma correcta',
                data : user
            })
        }) 
        
    
    },

    uploadPO: function(req, res){

            var pOrder = new PurchaseOrder ({
                nuRQ: req.body.nuRQ,
                nuOC: req.body.nuOC,
                moneda: req.body.moneda,
                subTotalOC: req.body.subTotalOC,
                igvOC: req.body.igvOC,
                amount: req.body.amount,
                createdAt: req.body.createdAt,
                createdby: req.body.createdby,
                notifyAt: req.body.notifyAt, 
                idProvider: req.body.idProvider,
                noRazonSocial: req.body.noRazonSocial,
                rootXML: null,
                rootPDF: null,
                rootSustentos: null,
                noFactura: null,
                feFactura: null,
                currency: null,
                subTotal: null,
                igv: null,
                totalAmount: null,
                condPago: req.body.condPago,
                status: req.body.status,
                aprovedAt: req.body.aprovedAt,
                area: req.body.area,
                notes: null
            })
            
            pOrder.save((err)=>{
                if(err) return res.status(500).send({message: "Error al guardar la Orden: "+err})
                return res.status(201).send({ message: "Orden guardada correctamente" })                
            
            })
    },


    uploadXMLfile: function(req, res){
		var projectId = req.params.nuOC;
		var fileName = 'Imagen no subida...';

		if(req.files){
			var filePath = req.files.rootXML.path;
			var fileSplit = filePath.split('\\');
			var fileName = fileSplit[1];
			var extSplit = fileName.split('\.');
			var fileExt = extSplit[1];


			if(fileExt == 'xml'){

				PurchaseOrder.findOneAndUpdate(projectId, {rootXML: fileName}, {new: true}, (err, projectUpdated) => {
					if(err) return res.status(500).send({message: 'La imagen no se ha subido' + err});

					if(!projectUpdated) return res.status(404).send({message: 'El proyecto no existe y no se ha asignado la imagen'});
                    
                    var xml = service.parseXML(fileName)
                    xml.then(function(datos){
                        const xmldata2 = service.extractData(datos);
                        
                        return res.status(200).send({
                            project : projectUpdated,
                            xml : {
                                nuFactura: xmldata2.nuFactura.toString(),
                                tipoDoc: xmldata2.tipoDoc.toString(),
                                feFactura: xmldata2.feFactura.toString(),
                                currency: xmldata2.currency.toString(),
                                igv: xmldata2.igv,
                                totalAmount: xmldata2.totalAmount,
                            }
                        })
                        })
                    xml.catch(error => console.error(error));

 
				});

			}else{
				fs.unlink(filePath, (err) => {
					return res.status(200).send({message: 'La extensión no es válida'});
				});
			}

		}else{
			return res.status(200).send({
				message: fileName
			});
		}

    },


    downloadXML: function(req, res){
		var file = req.params.rootXML;
		var path_file = './uploads/'+file;

		fs.exists(path_file, (exists) => {
			if(exists){
				return res.sendFile(path.resolve(path_file));
			}else{
				return res.status(200).send({
					message: "No existe el archivo..."
				});
			}
		});
	},

    uploadPDFfile: function(req, res){
		var projectId = req.params.nuOC;
		var fileName = 'Imagen no subida...';

		if(req.files){
			var filePath = req.files.rootPDF.path;
			var fileSplit = filePath.split('\\');
			var fileName = fileSplit[1];
			var extSplit = fileName.split('\.');
			var fileExt = extSplit[1];

			if(fileExt == 'pdf'){

				PurchaseOrder.findOneAndUpdate(projectId, {rootFactura: fileName}, {new: true}, (err, projectUpdated) => {
					if(err) return res.status(500).send({message: 'La imagen no se ha subido' + err});

					if(!projectUpdated) return res.status(404).send({message: 'El proyecto no existe y no se ha asignado la imagen'});

					return res.status(200).send({
						project: projectUpdated
					});
				});

			}else{
				fs.unlink(filePath, (err) => {
					return res.status(200).send({message: 'La extensión no es válida'});
				});
			}

		}else{
			return res.status(200).send({
				message: fileName
			});
		}

    },
    
    downloadPDF: function(req, res){
		var file = req.params.rootPDF;
		var path_file = './uploads/'+file;

		fs.exists(path_file, (exists) => {
			if(exists){
				return res.sendFile(path.resolve(path_file));
			}else{
				return res.status(200).send({
					message: "No existe el archivo..."
				});
			}
		});
	},

    updatePOstatus: function(req, res) {
        PurchaseOrder.updateOne({nuOC: req.body.nuOC}, {
            aprovedAt: Date.now(),
            status: req.body.status, 
            area: req.body.area
        }, (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexión con el servidor" + err})
            // no es necesario esta validación porque la llamada se hará desde la misma orden
            //if (user == 0) return res.status(404).send({ message: 'No existen la orden'})

            req.user = user
            res.status(200).send({
                success : true,
                message : 'Orden actualizada de forma correcta',

            //En el frontend llamar nuevamente al método importPO
            })
        }) 
        
    
    },

    



};

module.exports = controller;