const { auth } = require("./firebase-config");
const nodemailer = require("nodemailer");

const transporter = nodemailer.createTransport({
  // Konfigurasi pengaturan SMTP email
  host: "smtp.gmail.com",
  port: 465,
  secure: true, // true jika menggunakan SSL/TLS
  auth: {
    user: "traveleeapp23@gmail.com",
    pass: "byeulepryntfcbgm",
  },
});

async function sendEmailVerification(user) {
  const displayName = user.displayName;
  const email = user.email;
  const actionCodeSettings = {
    url: "https://travelee-capstone-projects.firebaseapp.com/",
    handleCodeInApp: true,
  };
  const verificationLink = await auth.generateEmailVerificationLink(
    email,
    actionCodeSettings
  );
  const appName = "Travelee";

  const mailOptions = {
    from: "traveleeapp23@gmail.com",
    to: email,
    subject: `Verify Your Email Address for ${appName}`,
    html: `
      <p>Hello ${displayName},</p>
      <p>Follow this link to verify your email address:</p>
      <p><a href="${verificationLink}">${verificationLink}</a></p>
      <p>If you didn’t ask to verify this address, you can ignore this email.</p>
      <p>Thanks,</p>
      <p>Your ${appName} team</p>
    `,
  };

  try {
    await transporter.sendMail(mailOptions);
    console.log("Email verification link sent to the user.");
  } catch (error) {
    console.error("Error sending email verification:", error);
  }
}
async function sendPasswordReset(user) {
  const displayName = user.displayName;
  const email = user.email;
  const actionCodeSettings = {
    url: "https://travelee-capstone-projects.firebaseapp.com/reset-password",
    handleCodeInApp: true,
  };
  const resetPasswordLink = await auth.generatePasswordResetLink(
    email,
    actionCodeSettings
  );
  const appName = "Travelee";

  const mailOptions = {
    from: "traveleeapp23@gmail.com",
    to: email,
    subject: `Reset your password for  ${appName}`,
    html: `
        <p>Hello ${displayName},</p>
        <p>Follow this link to reset your ${appName} password account:</p>
        <p><a href="${resetPasswordLink}">${resetPasswordLink}</a></p>
        <p>If you didn’t ask to reset your password, you can ignore this email.</p>
        <p>Thanks,</p>
        <p>Your ${appName} team</p>
      `,
  };

  try {
    await transporter.sendMail(mailOptions);
    console.log("Email verification link to reset password sent to the user.");
  } catch (error) {
    console.error("Error sending email verification:", error);
  }
}

module.exports = {
  sendEmailVerification,
  sendPasswordReset
};
