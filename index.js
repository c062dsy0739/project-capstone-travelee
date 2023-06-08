const express = require('express');

// Import app.js dari setiap folder
// Import app.js dari setiap folder
const app1 = require('./authentication/app');
const app2 = require('./search-engine/app');
const app3 = require('./favorite-place/app');
const app4 = require('./event/app');
//const app5 = require('./folder5/app');
const index = express();

index.use(app1);
index.use(app2);
index.use(app3);
index.use(app4);