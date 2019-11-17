The "; echo" at the end of each line is so that the mac terminal will create a new line after the output.  It is not not actually needed.

Using the Mac Terminal you can use curl

  Get a single cat
curl --user catuser:catpass -X GET "http://localhost:8080/cat/1"; echo

  Find cats examples:
curl --user catuser:catpass -X GET "http://localhost:8080/cat"; echo
curl --user catuser:catpass -X GET "http://localhost:8080/cat?name=n"; echo
curl --user catuser:catpass -X GET "http://localhost:8080/cat?color=black"; echo
curl --user catuser:catpass -X GET "http://localhost:8080/cat?name=n&color=orange"; echo

  Add a cat
curl --user catuser:catpass -H "Content-Type: application/json" -d '{"name":"Tiger", "color":"Yellow", "age": 3, "weight": 7.5}' -X POST "http://localhost:8080/cat"; echo

  Delete a cat, can only be done by the catadmin.  (The catuser one will get an error!)
curl --user catadmin:catpass -X DELETE "http://localhost:8080/cat/1"; echo
curl --user catuser:catpass -X DELETE "http://localhost:8080/cat/1"; echo

Or using a web browser you can do the GET commands
It will ask you for the user and password "catuser" and "catpass"
  http://localhost:8080/cat/1
  http://localhost:8080/cat?name=f&color=white

java -jar cat-web-service.jar com.example.demo.DemoApplication
