var app = require('express')();
var bodyParser = require('body-parser');
var path = require('path');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/home', function(req, res) {
    console.log('Dados passados na URL: ');
    console.log(req.query);
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.get('/teste_redirect', function(req, res) {
    res.redirect(301, 'https://www.google.com');
});

app.post('/teste_post', function(req, res) {
    console.log('Dados passados no corpo da mensagem: ');
    console.log(req.body);
    if (!req.body.nome) {
        res.status(400);
        res.send('Nome não fornecido!');
    } else {
        resposta = 'Olá ' + req.body.nome + ' ' + req.body.sobrenome;
        res.json(resposta);
    }
});

app.delete('/teste_post', function(req, res) {
    res.json('Resposta de teste');
});

app.listen(3000);
