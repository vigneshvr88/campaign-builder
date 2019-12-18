# campaign-builder

This spring-boot application is used for drawing the best possible combination of sold campaign which would bring highest revenue possible.

Some facts before building and running the micro-service,

Lambok is used to generate getters and setters for those object automatically. In case, you are familiar with lambok and using it in intellij, you can skip the below additional step and build the application directly.

To use lambok in Intellij, 

1. Install latest Lombok-Plugin from IDEA market place.
2. Enable annotation processing (Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors)

To make testing convenient, postman collection is added to the root of the project. It can be imported to postman and run the test cases within the collection. (postman-collection/Campaign Builder API.postman_collection.json)

