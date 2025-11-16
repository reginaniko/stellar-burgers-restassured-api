import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogInUserTests extends BaseTest {

    @Test
    @DisplayName("Login as an existing user")
    public void testLoginValidUserIsSuccessful() {
        userResponse = createUniqueUserAndReturnAsResponse(userRequest); // create user
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        ValidatableResponse response = baseHttpClient.postRequest(LOGIN_USER_ENDPOINT, loginUserRequest); // authorize user
        UserResponse responseBody = response.extract().body().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(200); // verify status code
        assertThat(responseBody.getUser().getEmail())
                .isEqualTo(userResponseBody.getUser().getEmail()); // verify response fields match created user
        assertThat(responseBody.getUser().getName())
                .isEqualTo(userResponseBody.getUser().getName());
        assertThat(responseBody.getSuccess()).isEqualTo(true); // verify success and non-empty fields
        assertThat(responseBody.getAccessToken()).isNotNull();
        assertThat(responseBody.getRefreshToken()).isNotNull();
    }

    @Test
    @DisplayName("Login with empty email and password")
    public void testEmptyLoginReturnsError() {
        ValidatableResponse response = baseHttpClient.postRequest(LOGIN_USER_ENDPOINT, emptyLoginUserRequest);
        UserResponse responseBody = response.extract().body().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(401); // verify status code
        assertThat(responseBody.getMessage())
                .isEqualTo("email or password are incorrect"); // verify error message
    }

    @Test
    @DisplayName("Login with empty password")
    public void testNoPasswordLoginReturnsError() {
        ValidatableResponse response = baseHttpClient.postRequest(LOGIN_USER_ENDPOINT, noPasswordLoginUserRequest);
        UserResponse responseBody = response.extract().body().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(401); // verify status code
        assertThat(responseBody.getMessage())
                .isEqualTo("email or password are incorrect"); // verify error message
    }

    @Test
    @DisplayName("Login with empty email")
    public void testNoEmailLoginReturnsError() {
        ValidatableResponse response = baseHttpClient.postRequest(LOGIN_USER_ENDPOINT, noEmailLoginUserRequest);
        UserResponse responseBody = response.extract().body().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(401); // verify status code
        assertThat(responseBody.getMessage())
                .isEqualTo("email or password are incorrect"); // verify error message
    }
}
