const cors = require('cors');
const express = require('express');
const bodyParser = require('body-parser');
const User = require('./models/users');
const bcrypt = require('bcrypt');
const jwt = require('jwt-simple');
const moment = require('moment');

const app = express();


require('dotenv').config();


app.use(cors());
app.use(bodyParser.json());

app.get('/users', async (req, res) => {
    const users = await User.getAll();
    res.json(users);
});


app.post('/register', async (req, res) => {
    console.log(req.body);
    const user = await User.getByUsername(req.body.username);
    if (user !== undefined) {
        res.json({error: 'Error, Usuario ya creado'})
    }
    req.body.password = bcrypt.hashSync(req.body.password, 10);
    const result = await User.registerUser(req.body);
    res.json({result: "ok"});
});

app.post('/login', async (req, res) => {
    const user = await User.getByUsername(req.body.username);
    if (user === undefined) {
        res.json({error: 'Error, no se encuentra el usuario'})
    } else {
        const equals = bcrypt.compareSync(req.body.password, user.password);
        if (!equals) {
            res.json({error: 'Error, email o contraseña incorrecta'});
        } else {
            res.json({
                token: createToken(user),
                result: 'Login correcto'
            });
        }
    }
});

const createToken = (user) => {
    let payload = {
        user_id: user.id,
        created_at: moment().unix(),
        expires_at: moment().add(1, 'day').unix(),
    }
    return jwt.encode(payload, process.env.TOKEN_KEY);
}


const port = process.env.PORT;
app.listen(port, () => console.log(`Listening on port http://localhost:${port}..`));

module.exports = app;