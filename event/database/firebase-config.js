const admin = require("firebase-admin");
const serviceAccount = require("./serviceAccountKey.json");

// Konfigurasi Firebase Admin SDK
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  //databaseURL: "https://travelee-capstone-projects-default-rtdb.firebaseio.com",
  databaseURL: "https://travelee-capstone-projects.firebaseio.com",
  projectId: "travelee-capstone-projects"
});



const dbAdmin = admin.firestore();
const auth = admin.auth()
module.exports = {
  dbAdmin,
  auth,
};

