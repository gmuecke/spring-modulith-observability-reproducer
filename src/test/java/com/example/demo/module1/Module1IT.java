package com.example.demo.module1;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static io.restassured.RestAssured.given;

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Module1IT {
    @LocalServerPort
    private int port;
    private RequestSpecification specification;

    @BeforeEach
    public void setupSpecification() {
        specification =
                new RequestSpecBuilder()
                        .setBasePath("/module1")
                        .setPort(port)
                        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                        .build();
    }

    @Test
    void test_withInterface() {

        given()
                .spec(specification)
                .when()
                .get("/test")
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.equalTo("ok"));
    }

}