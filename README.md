# Kodtest
Här är mitt förslag på lösning.

Jag var lite osäker på hur ni vill att resultatet ska lämnas men jag har gjort en endpoint som tar emot en lista av artikelbeställningar (artikel id + antal) och levererar en json struktur med bl a totalbelopp för hela beställningen samt en "rad"/post per beställd artikel. I de fall köp av en artikel genererar fria exemplar av en annan artikel kommer de fria/gratis artiklarna redovisas på egen rad.

Det var lite andra "krav" jag också var osäker på, bl a om man beställer fler än 4 av artikel 9. Jag har implementerat det så att hela beställningen avvisas om man beställer fler. Min tanke är att om beställaren inte får beställa så många 9:or man önskas så kanske man inte vill köpa något alls.

# Testa applicationen
Swagger-ui nås nås på http://localhost:8080/swagger-ui/index.html#/
