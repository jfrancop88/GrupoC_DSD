'use strict'

const mongoose = require('mongoose');
const CompanyAreas = require('../models/company-areas');

var controller = {

    createArea: function (req, res) {
        var coAreas = new CompanyAreas ({
                areaId: req.body.areaId,
                displayName: req.body.displayName,
            
            })

            coAreas.save((err)=>{
                if(err) return res.status(500).send({message: "Error al guardar el area: "+err})
                return res.status(201).send({ message: "Area guardada correctamente" })                
            
            })

    }


}

module.exports = controller;