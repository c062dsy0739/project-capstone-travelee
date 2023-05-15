require("dotenv").config();
const app = express();
const {createUser} = require("./model/user");
const bodyParser = require("body-parser");
app.use(bodyParser.json());
app.get("/", (req, res) => {
  res.send("<h1>Server is running</h1>");
});

// Rute untuk registrasi pengguna baru
app.post('/register', async (req, res) => {
    const { firstName, lastName, email, password } = req.body;
  
    try {
      const userId = await createUser(firstName, lastName, email, password);
      // Kirimkan token OTP ke email pengguna di sini
      const otpToken = generateOtpToken(); // Fungsi untuk menghasilkan token OTP
      sendOtpEmail(email, otpToken); // Fungsi untuk mengirim email OTP
      res.status(200).json({ message: 'Registration successful', userId });
    } catch (error) {
      res.status(500).json({ error: 'Registration failed' });
    }
  });

module.exports = app;
