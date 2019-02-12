var app = require('express')();
var bodyParser = require('body-parser');

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded

app.get('/home', function(req, res) {
    console.log(req.query);
    console.log(req.query.senha);
    res.json("Ola mundo");
});

app.post('/data', function(req, res) {
    console.log(req.body);
    res.end();
});

app.listen(3000);
