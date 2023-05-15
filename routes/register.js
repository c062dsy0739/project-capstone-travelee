// const express = require('express');
// const { check, validationResult } = require('express-validator');
// const admin = require('firebase-admin')
// const router = express.Router();

// //Registrasi Route
// router.post('/', [
//   check('firstName', 'First name is required').notEmpty(),
//   check('lastName', 'Last name is required').notEmpty(),
//   check('email', 'Email is required').notEmpty(),
//   check('email', 'Invalid email').isEmail(),
//   check('password', 'Password must be at least 8 characters long').isLength({ min: 8 })
// ], async (req, res) => {
//   const errors = validationResult(req);
//   if (!errors.isEmpty()) {
//     return res.status(422).json({ errors: errors.array() });
//   }

//   const { firstName, lastName, email, password } = req.body;
//   // Generate and store OTP
//   const otp = Math.floor(1000 + Math.random() * 9000);
//   const otpRef = db.collection('otp').doc(email);
//   await otpRef.set({ otp });

//   // Send OTP to user's email
//   const mailOptions = {
//     from: 'noreply@travelee.bangkit.academy',
//     to: email,
//     subject: 'Your OTP for registration',
//     text: `Your OTP is ${otp}`,
//   };
//   await transporter.sendMail(mailOptions);

//   // Create JWT token
//   const token = jwt.sign({ email }, JWT_SECRET, { expiresIn: '15m' });

//   res.status(201).json({
//     message: 'User registered successfully'
//   });
// });

// module.exports = router;
