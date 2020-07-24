# howl.in.disguise

Transformers REST API
-----------------------------------------------------------------------------------------------------------------------------

This service can be used to get, create, update, and delete transformers in a JPA repository.
The final battle function can be used to pit transformers against eachother, depending on their allegiance!

By default, this service will start on http://localhost:8080/

HOW TO BUILD
--------------
*Assuming Java 8 and Maven 3+ is installed*

1. Download the zip or clone the Git repository
2. Unzip the zip file (if you downloaded one)
3. Open Command Prompt and change directory (cd) to folder containing pom.xml
4. Run ```mvn clean spring-boot:run``` to start the service
5. Service should be running, test by going to http://localhost:8080/transformers


HOW TO USE (http://localhost:8080/ GET REQUESTS)
---------------------------------------------------
- /transformers :            (GET) Return all transformers in repository
- /transformers/{id} :       (GET) Return transformer based on id given
- /battle/{transformerIds} : (GET) Pit transformers against eachother (autobots vs decepticons)
```ex: /battle/1,3,4,7 -> Transformers with id's 1 3 4 and 7 will battle eachother```

HOW TO POST, PUT, DELETE (Postman https://www.postman.com)
--------------------------------------------------------------
- [POST] http://localhost:8080/transformers with Body = {JSON Transformer information}
- [PUT] http://localhost:8080/transformers/{id} with Body = {JSON Transformer information}
- [DELETE] http://localhost:8080/transformers/{id}



### Transformer.java ###
Transformer class holds transformer attributes, getters, setters, constructors, and compareTo method.

### TransformerResource.java ###
TransformerResource class is the Rest Controller defining HTTP Methods allowed by this API. 

### BattleSim.java ###
BattleSim class generates the results of the battle using the fight method. String output.
