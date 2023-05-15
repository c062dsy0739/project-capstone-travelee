const admin = require('firebase-admin')

const serviceAccount = require('./google-services.json');

admin.initializeApp({
    credential:admin.credential.cert(serviceAccount),
    databaseURL: 'https://travelee-capstone-projects.firebaseio.com'
})

const db = admin.database();