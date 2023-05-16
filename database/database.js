const admin = require("firebase-admin");
const serviceAccount = require("google-services.json");

// Konfigurasi Firebase Admin SDK
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://travelee-capstone-projects.firebaseio.com",
});

// Fungsi untuk mendapatkan referensi koleksi pengguna
const getUsersCollection = () => {
  return admin.firestore().collection("users");
};

module.exports = getUsersCollection;
