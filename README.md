# spring-modulith-observability-reproducer
Reproducer for conflict of Spring modulith observability and REST API interfaces

# Summary

When the `spring-modulith-observability` dependency is present, 
RestControllers that implement _any_ interface are no longer serving their endpoints
or apear in the OpenAPI documentation (Swagger).

This is not limited to interfaces with Rest Annotations (like `@GetMapping`), or
OpenAPI Annotations, but also to interfaces that are not related to the REST API, including
empty marker interfaces.

The expected behavior is, that the  `spring-modulith-observability` doesn't have 
an effect on the availability of RestControllers implementing an interface.

# Steps to Reproduce
Requires JDK21

Steps to reproduce:

## Expected behavior:

0. keep the spring-modulith-observability commented-out in pom.xml
   ```xml
   <!--   <dependency>-->
   <!--      <groupId>org.springframework.modulith</groupId>-->
   <!--      <artifactId>spring-modulith-observability</artifactId>-->
   <!--      <scope>runtime</scope>-->
   <!--   </dependency>-->
   ```
1. Build application:
   ```
   ./mvnw verify
   ```
2. Start application
   ```
   ./mvnw spring-boot:run
   ```
3. Test Endpoints:
    ```
    curl http://localhost:8080/module1/test
    > ok
    
    curl http://localhost:8080/module2/test
    > ok
   
    curl http://localhost:8080/module3/test
    > ok
    ``` 
4. Verify Swagger-UI
   http://localhost:8080/swagger-ui/index.html#
   You should see the endpoints 
   - "/module1/test"
   - "/module2/test"
   - "/module3/test"

## Actual behavior:

0. un-comment the spring-modulith-observability dependency in pom.xml, lines 58-62
   ```xml
   <dependency>
      <groupId>org.springframework.modulith</groupId>
      <artifactId>spring-modulith-observability</artifactId>
      <scope>runtime</scope>
   </dependency>
   ```
1. Build application:
    ```
    ./mvnw verify
    ```
   this build's ok, including tests
2. Start application
   ```
   ./mvnw spring-boot:run
   ```
3. Test Endpoints:
    ```
    curl http://localhost:8080/module1/test
    > ok
    
    curl http://localhost:8080/module2/test
    > 404 Not Found
   
    curl http://localhost:8080/module3/test
    > 404 Not Found
    ``` 
4. Verify Swagger-UI
   http://localhost:8080/swagger-ui/index.html#
   - You only see "/module1/test".
   - The endpoints "/module2/test", "/module3/test" are missing