const nodemailer = require("nodemailer");
const randomstring = require("randomstring");

// Fungsi untuk menghasilkan kode OTP
function generateOTP() {
  return randomstring.generate({
    length: 4,
    charset: "numeric",
  });
}

async function sendEmail(email) {
  try {
    const otpCode = generateOTP();

    // Simpan kode OTP di Firestore
    await db.collection("otp").doc(email).set({
      code: otpCode,
    });

    // Kirim kode OTP ke email user
    const transporter = nodemailer.createTransport({
      service: "Gmail",
      auth: {
        user: "<your-email>",
        pass: "<your-email-password>",
      },
    });

    const mailOptions = {
      from: "<your-email>",
      to: email,
      subject: "Verification Code",
      text: `Your verification code is: ${otpCode}`,
    };

    const result = await transporter.sendMail(mailOptions);
    console.log("Email sent:", result.response);
  } catch (error) {
    console.error("Error sending email:", error);
  }
}

module.exports = sendEmail;
