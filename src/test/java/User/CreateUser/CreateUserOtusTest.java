package User.CreateUser;

import dto.UserOtus;
import io.restassured.response.Response;
import lombok.ToString;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.UserOtusApi;

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CreateUserOtusTest {
    private UserOtusApi userOtusApi = new UserOtusApi();
    private Logger logger = LogManager.getLogger(CreateUserOtusTest.class);

    @Test
    // Тест-кейс проверяет возврат кода 200 (Успех) и все поля ответа на соответствие ожидаемым, в том числе, что в поле
    // message возвращается id юзера
    public void checkUser() {

        long userId = new Random().nextLong();
        userId = Math.abs(userId);
       String messageExpected = String.valueOf(userId);

        logger.info(userId);
        UserOtus user = UserOtus.builder()
                .userStatus(101L)
                .email("a@a.a")
                .firstName("dfds1")
                .lastName("sdfsdf")
                .id(userId)
                .password("fdsf")
                .phone("545425")
                .username("sdfdsf")
                .build();

      Response response = userOtusApi.createUser(user);

      response.then()
              .log().all()
              .body("type",equalTo("unknown"))
              .body("message",equalTo(messageExpected))
              .time(lessThan(5000L))
              .statusCode(HttpStatus.SC_OK);

  //    String messageActual = response.jsonPath().get("message");
    //  Assertions.assertEquals(userId.,messageActual.);

    }

    @Test
    // Тест-кейс проверяет получение ответа 500 при неверно поданных данных - поле userStatus выходит за границы int32
    public void checkUserFalse() {

        UserOtus user = UserOtus.builder()
                .userStatus(4555343463463636L)
                .email("a@a.a")
                .id(4555343463463636L)
                .firstName("dfds1")
                .lastName("sdfsdf")
                .password("fdsf")
                .phone("545425")
                .username("sdfdsf")
                .build();

        Response response = userOtusApi.createUser(user);

        response.then()
                .log().all()
                .body("type",equalTo("unknown"))
                .body("message",equalTo("something bad happened"))
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);




    }

}
