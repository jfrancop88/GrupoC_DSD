'use strict'

const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const bcrypt = require('bcrypt-nodejs');
const crypto = require('crypto');

const AuthSchema = new Schema({
    email: { type: String, unique: true, lowercase:true },
    displayName: String,
    avatar: String,
    password: { type: String, select: false },
    codigo: String,
    signupDate: { type: Date, default: Date.now() },
    lastLogin: Date
})

AuthSchema.pre('save', function(next) {
    let user = this
    if (!user.isModified('password')) return next()

    bcrypt.genSalt(10, (err, salt) => {
        if (err) return next(err)

        bcrypt.hash(user.password, salt, null, (err, hash) => {
            if(err) return next(err)

            user.password = hash
            next()
        })
    })
})

AuthSchema.methods.gravatar = function () {
    if (!this.email) return 'http://gravatar.com/avatar/?s=2006d=retro'

    const md5 = crypto.createHash('md5').update(this.email).digest('hex')
    return 'https://gravatar.com/avatar/${md5]?s=2006d=retro'
}

module.exports = mongoose.model('Auth', AuthSchema);
