'use strict'

const mongoose = require('mongoose')
const Auth = require('../models/auth')
const service = require('../services')

var controller = {

    signin: function(req, res){
        Auth.find({email: req.body.email}, (err, user) => {
            if(err) return res.status(500).send({message: err})
            if (user==0) return res.status(404).send({ message: 'No existe el usuario'})

            req.user = user
            res.status(200).send({
                success : true,
                message : 'Te has logueado correctamente',
                token : service.createToken(user),
                id : user[0].id.toString(),
                codigo : user[0].email,
            })
        })       
    
    },

    getusers: function(req, res){
        Auth.find( (err, user) => {
            if(err) return res.status(500).send({message: "No hay conexion con el servidor" + err})
            if (user == 0) return res.status(404).send({ message: 'No existen registros'})

            req.user = user
            res.status(200).send({
                success : true,
                message : 'Usuarios importados correctamente',
                data : user
            })
        })
    },

    signup: function(req, res){
        const user = new Auth ({
            email: req.body.email,
            displayName: req.body.displayName,
            password: req.body.password,
            codigo : req.body.codigo,
            avatar: req.body.avatar
        })

        user.save((err)=>{
            if(err) return res.status(500).send({message: "Error al crear el usuario: "+err})
            return res.status(201).send({ token: service.createToken(user) })
        })
    },

    forgotpassword: function(req, res){
        return res.status(200).send({message:"Devolviendo el api de forgotpassword"});
    },

    resetpassword: function(req, res){
        return res.status(200).send({message:"Devolviendo el api de resetpassword"});
    }

};

module.exports = controller;
