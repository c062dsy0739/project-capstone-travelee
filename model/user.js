const { getUsersCollection } = require('database/database.js');

// Fungsi untuk membuat pengguna baru
const createUser = async (firstName, lastName, email, password) => {
  const usersCollection = getUsersCollection();
  const user = {
    firstName,
    lastName,
    email,
    password
  };

  const newUser = await usersCollection.add(user);
  return newUser.id;
};

module.exports = {
  createUser
};