const getAll = () => {
    return new Promise((resolve, reject) => {
        db.query('SELECT * FROM user', (err, rows) => {
            if (err) reject(err)
            resolve(rows);
        });
    });
};

// Registrar nuevos usuarios
const registerUser = ({ user_id, username, password, create_time, ecommerce_id})=>{
    return new Promise((resolve, reject)=>{
        db.query('INSERT INTO users (user_id, username, password, create_time, ecommerce_id) values (?, ?, ?, ?, ?)', [user_id, username, password, create_time, ecommerce_id], (err, result)=>{
            if(err) reject(err)
            if(result){
                resolve(result)
            };
        });
    });
};

// Obtener sesión por username
const getbyUsername = (getUsername) => {
    return new Promise((resolve, reject) => {
        db.query('SELECT * FROM users where username = ?', [getUsername],(err, rows) => {
            if (err) reject(err)
            resolve(rows[0])
        });
    })
}

// Login de usuario



module.exports ={
    getAll: getAll,
    registerUser : registerUser,
    getbyUsername : getbyUsername
}