const ref = db.ref('account/user1');

// Read data
collectionRef.get()
  .then((snapshot) => {
    snapshot.forEach((doc) => {
      console.log(doc.id, '=>', doc.data());
    });
  })
  .catch((error) => {
    console.log('Error getting documents:', error);
  });

// Write data
const newData = { name: 'John', age: 30 };
collectionRef.add(newData)
  .then((docRef) => {
    console.log('Document written with ID:', docRef.id);
  })
  .catch((error) => {
    console.error('Error adding document:', error);
  });