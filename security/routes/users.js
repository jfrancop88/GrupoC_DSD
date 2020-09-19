const express = require('express');
const router = express.Router();
const User = require('../models/users');
const bcrypt = require('bcrypt');
const jwt = require('jwt-simple');
const moment = require('moment');

//Obtener el listado de usuarios registrados
router.get('/', async (req, res)=>{
    const users = await User.getAll();
    res.json(users);
});


router.post('/register', async (req, res)=>{
    console.log(req.body);
    req.body.password = bcrypt.hashSync(req.body.password, 10);
    const result = await User.registerUser(req.body);
    res.json(result);
});

router.post('/login', async (req, res) => {
    const user = await Users.getbyUsername(req.body.username);
    if (user == undefined){
        res.json({error:'Error, no se encuentra el usuario'})
    }
    else {
        const equals = bcrypt.compareSync(req.body.password, user.password);
        if (!equals){
            res.json({error:'Error, email o contraseña incorrecta'});
        }
        else{
            res.json({successfull: createToken(user),
            done: 'Login correcto'});
        }
    }
});

const createToken = (user)=>{
    let payload ={
        user_id : user.id,
        created_at : moment().unix(),
        expires_at : moment().add(1, 'day').unix(),
    }
    return jwt.encode(payload,process.env.TOKEN_KEY);
}


module.exports = router;