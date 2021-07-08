package User.CreateUser;

import dto.UserOtus;
import io.restassured.response.Response;
import lombok.ToString;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.GetUserOtusApi;
import services.UserOtusApi;

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetUserOtusTest {
    private UserOtusApi userOtusApi = new UserOtusApi();
    private GetUserOtusApi getUser = new GetUserOtusApi();
    private Logger logger = LogManager.getLogger(CreateUserOtusTest.class);

    @Test
    // Тест-кейс создает тестового юзера, а затем делает поиск  по userName через GET Апи. Проверяется успешность запроса (код ответа 200 и сверяется юзернейм)
     public void findUser() {
        String userName = "newUser";
        UserOtus user = UserOtus.builder()
                .userStatus(101L)
                .email("a@a.a")
                .firstName("dfds1")
                .lastName("sdfsdf")
                .id(101L)
                .password("fdsf")
                .phone("545425")
                .username(userName)
                .build();

        userOtusApi.createUser(user);
        Response response = getUser.getUser(userName);

        response.then()
                .log().all()
                .body("username",equalTo(userName))
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    // Тест-кейс ищет заведомо несуществующего пользователя и проверяет возврат кода 404
    public void findNonExistentUser() {
        String userName = "dsgsdlkglqutrijglksfgiurnv.ngkafsmg;a;";


        Response response = getUser.getUser(userName);

        response.then()
                .log().all()
                .body("type",equalTo("error"))
                .body("message",equalTo("User not found"))
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
