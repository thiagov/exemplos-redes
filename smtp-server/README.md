Rodar o servidor SMTP e POP3:

```
java -Dgreenmail.setup.test.smtp -Dgreenmail.setup.test.pop3 -Dgreenmail.auth.disabled -jar greenmail.jar

```

Ao enviar um email, por exemplo, para `teste@localhost.com`, o servidor cria uma caixa postal para o email
destinatário. O login e senha dessa caixa postal são iguais ao endereço de email utilizado.

Para testar SMTP:

```
telnet localhost 3025
HELO localhost
MAIL FROM: <thiagosilvavilela@gmail.com>
RCPT TO: <teste@localhost>
DATA
Subject: Assunto de teste
From: thiagosilvavilela@gmail.com
To: teste@localhost

Esse eh o corpo da mensagem.

Att.,
Thiago
.
QUIT

```

Para testar POP3:


```
telnet localhost 3110
USER teste@localhost
PASS teste@localhost
LIST
RETR 1
DELE 1
QUIT
```
