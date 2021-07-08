package services;
import dto.UserOtus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserOtusApi {


    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private RequestSpecification spec;
    private static final String USER = "/user";


    public UserOtusApi () {

        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response createUser(UserOtus user) {

        return
                given(spec)
                .with()
                .body(user)
                .log().all()
                .when()
                .post(USER);


    }



}
