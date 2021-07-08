package services;
import dto.UserOtus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetUserOtusApi {


    private static final String BASE_URI = "https://petstore.swagger.io/v2/user";
    private RequestSpecification spec;


    public GetUserOtusApi () {

        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getUser(String user) {

        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .get(user);


    }



}
