const mongoose = require('mongoose');
const { Schema } = mongoose;

const userSchema = new Schema({
  _id: {
    type: Schema.Types.ObjectId,
    auto: true // Génère automatiquement un ObjectId
  },
  nom: {
    type: String,
    required: true,
    unique: true
  },
  prenom: {
    type: String,
    required: true,
    unique: true
  },
  email: {
    type: String,
    required: true,
    unique: true,
    match: [/^\S+@\S+\.\S+$/, 'Veuillez entrer une adresse email valide.']
  },
  password: {
    type: String,
    required: true
  },
  isOnline: {
    type: Boolean,
    default: false
  },
  created_at: {
    type: Date,
    default: Date.now
  },

}, { timestamps: { createdAt: 'created_at'} });

module.exports = mongoose.model('User', userSchema);



const messageSchema = new Schema({
    _id: {
      type: Schema.Types.ObjectId,
      auto: true // Génère automatiquement un ObjectId
    },
    sender_id: {
      type: Schema.Types.ObjectId,
      ref: 'User', // Référence à la collection `users`
      required: true
    },
    receiver_id: {
      type: Schema.Types.ObjectId,
      ref: 'User', // Référence à la collection `users`
      required: true
    },
    content: {
      type: String,
      required: true,
      maxLenght: 200
    },

    dateSent: {
      type: Date,
      default: Date.now
    },
    dateView: {
      type: Date,
      default: null // Par défaut, le message n'est pas vu
    }
  });
  
  module.exports = mong
  