// Konfigurasi Firebase
const admin = require("firebase-admin");

const serviceAccount = require("serviceAccountKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://travelee-capstone-projects.firebaseio.com"
});

// Konfigurasi JWT
const jwt = require("jsonwebtoken");
const JWT_SECRET = "your-secret-key";

// Konfigurasi Nodemailer dan SMTPTransport
const nodemailer = require("nodemailer");
const SMTP_HOST = "smtp.gmail.com";
const SMTP_PORT = 465;
const SMTP_USER = "your-email@gmail.com";
const SMTP_PASS = "your-email-password";

const transporter = nodemailer.createTransport({
  host: SMTP_HOST,
  port: SMTP_PORT,
  secure: true,
  auth: {
    user: SMTP_USER,
    pass: SMTP_PASS
  }
});
