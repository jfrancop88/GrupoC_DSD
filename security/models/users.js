const mysql = require('mysql');

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'mysql',
    database: 'system-payment',
    multipleStatements: true
});

db.connect((err) => {
    if (!err)
        console.log('Connection Established Successfully');
    else
        console.log('Connection Failed!' + JSON.stringify(err, undefined, 2));
});

const getAll = () => {
    return new Promise((resolve, reject) => {
        db.query('SELECT * FROM user', (err, rows) => {
            if (err) reject(err)
            resolve(rows);
        });
    });
};

// Registrar nuevos usuarios
const registerUser = ({username, password, email, ecommerce_id}) => {
    return new Promise((resolve, reject) => {
        db.query('INSERT INTO user ( username, password, email, ecommerce_id) values (?,?, ?, ?)', [username, password, email, ecommerce_id],
            (err, result) => {
                if (err) reject(err)
                if (result) {
                    resolve(result)
                }
                ;
            });
    });
};

// Obtener sesión por username
const getByUsername = (username) => {
    return new Promise((resolve, reject) => {
        db.query("SELECT * FROM user where username = ?", [username], (err, rows) => {
            if (err) reject(err)
            resolve(rows[0])
        });
    })
}

// Login de usuario


module.exports = {
    getAll: getAll,
    registerUser: registerUser,
    getByUsername: getByUsername
}