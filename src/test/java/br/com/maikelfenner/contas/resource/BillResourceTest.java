package br.com.maikelfenner.contas.resource;

import br.com.maikelfenner.contas.ContasApplicationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class BillResourceTest extends ContasApplicationTest {

    @Test
    public void shouldReturnBillOnSave() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "Bill 01");
        params.put("value", "100");
        params.put("dueDate", "2019-07-10");
        params.put("paymentDate", "2019-07-15");

        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(params)
                .when()
                .post("/bill")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("Bill 01"),
                        "dueDate", equalTo("2019-07-10"),
                        "paymentDate", equalTo("2019-07-15"));
    }

    @Test
    public void shouldThrowAnErrorWhenSavingBillWithInvalidName() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "");
        params.put("value", "100");
        params.put("dueDate", "2019-07-10");
        params.put("paymentDate", "2019-07-15");

        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(params)
                .when()
                .post("/bill")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Name is mandatory"));
    }

    @Test
    public void shouldListAllBills() throws Exception {
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .when()
                .get("/bill")
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItem("Bill 01"));
    }

}