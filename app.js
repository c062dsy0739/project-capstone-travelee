require("dotenv").config();
//Framework
const express = require("express");
const app = express();
const bodyParser = require("body-parser");
app.use(bodyParser.json());

//Database Firebase untuk user
const db = require("./database/firebase");
const sendEmail = require("./database/sendEmail");

//Running Server
app.get("/", (req, res) => {
  res.send("<h1>Server is running</h1>");
});

// Rute untuk registrasi pengguna baru
app.post("/register", async (req, res) => {
  try {
    const { firstname, lastname, email, password } = req.body;

    // Simpan data user ke Firestore
    const userRef = db.collection("users").doc(); // Buat dokumen baru dengan ID acak
    await userRef.set({
      firstname,
      lastname,
      email,
      password
    });

    // Kirim link verifikasi ke email pengguna
    const verificationToken = generateVerificationToken(); // Fungsi untuk menghasilkan token verifikasi
    const verificationLink = `${process.env.BASE_URL}/verify/${verificationToken}`;
    // Kirim email verifikasi dengan link ke pengguna
    await sendVerificationEmail(email, verificationLink);
    res.status(200).json({ message: "User registered successfully." });
  //   // Kirim kode OTP ke email user
  //   const otpCode = generateOTP(); // Fungsi untuk menghasilkan kode OTP
  //   await sendEmail(email, otpCode);
  //   res.status(200).json({ message: "User registered successfully." });
  } catch (error) {
    console.error("Error registering user:", error);
    res.status(500).json({ error: "Failed to register user." });
  }
});


// Verify code Link
app.post('/verify', async (req, res) => {
  try {
    const { token } = req.body;

    // Cari pengguna dengan token verifikasi yang cocok
    const userQuery = await db
      .collection('users')
      .where('verificationToken', '==', token)
      .limit(1)
      .get();

    if (userQuery.empty) {
      return res.status(400).json({ error: 'Invalid verification token.' });
    }

    const user = userQuery.docs[0];
    const userId = user.id;

    // Verifikasi akun dan hapus token verifikasi
    await db.collection('users').doc(userId).update({
      verified: true,
      verificationToken: null
    });

    res.status(200).json({ message: 'Account verification successful.' });
  } catch (error) {
    console.error('Error verifying account:', error);
    res.status(500).json({ error: 'Failed to verify account.' });
  }
});

// //Verify code OTP 
// app.post('/verify', async (req, res) => {
//   try {
//     const { email, otpCode } = req.body;

//     // Ambil kode OTP dari Firestore
//     const otpDoc = await db.collection('otp').doc(email).get();
//     if (!otpDoc.exists) {
//       return res.status(400).json({ error: 'Invalid email or OTP code.' });
//     }

//     const storedOtpCode = otpDoc.data().code;
//     if (otpCode !== storedOtpCode) {
//       return res.status(400).json({ error: 'Invalid email or OTP code.' });
//     }

//     // Hapus kode OTP dari Firestore setelah verifikasi berhasil
//     await db.collection('otp').doc(email).delete();

//     res.status(200).json({ message: 'Verification successful.' });
//   } catch (error) {
//     console.error('Error verifying OTP:', error);
//     res.status(500).json({ error: 'Failed to verify OTP.' });
//   }
// });

module.exports = app;