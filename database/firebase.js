const admin = require("firebase-admin");
const serviceAccount = require("../serviceAccountKey.json");

// Konfigurasi Firebase Admin SDK
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://travelee-capstone-projects.firebaseio.com",
});

const db = admin.firestore();

module.exports = db;

