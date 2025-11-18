import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTests extends BaseTest {

    @Test
    @DisplayName("Create a unique user")
    public void testCreateUniqueUserIsSuccessful() {
        userResponse = createUniqueUserAndReturnAsResponse(userRequest);
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        assertThat(userResponse.extract().statusCode()).isEqualTo(200); // verify status code
        assertThat(userResponseBody.getUser().getEmail()).isEqualTo(userRequest.getEmail()); // verify response fields match request
        assertThat(userResponseBody.getUser().getName()).isEqualTo(userRequest.getName());
        assertThat(userResponseBody.getSuccess()).isEqualTo(true); // verify success field
        assertThat(userResponseBody.getAccessToken()).isNotNull(); // verify tokens are returned
        assertThat(userResponseBody.getRefreshToken()).isNotNull();
    }

    @Test
    @DisplayName("Create a user who is already registered")
    public void testCreateDuplicateUserReturnsError() {

        userResponse = createUniqueUserAndReturnAsResponse(userRequest);
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        ValidatableResponse userResponse1 = createUniqueUserAndReturnAsResponse(userRequest); // create user with same data
        UserResponse duplicateUserResponse = userResponse1.extract().as(UserResponse.class);

        assertThat(userResponse1.extract().statusCode()).isEqualTo(403);
        assertThat(duplicateUserResponse.getSuccess()).isEqualTo(false);
        assertThat(duplicateUserResponse.getMessage()).isEqualTo("User already exists");
    }

    @Test
    @DisplayName("Create a user without a name")
    public void testCreateUserWithoutNameReturnsError() {
        userResponse = createUniqueUserAndReturnAsResponse(noNameUserRequest); // create user without a name
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        assertThat(userResponse.extract().statusCode()).isEqualTo(403); // verify status code
        assertThat(userResponseBody.getSuccess()).isEqualTo(false);
        assertThat(userResponseBody.getMessage()).isEqualTo("Email, password and name are required fields"); // verify error message
    }

    @Test
    @DisplayName("Create a user without an email")
    public void testCreateUserWithoutEmailReturnsError() {
        userResponse = createUniqueUserAndReturnAsResponse(noEmailUserRequest); // create user without an email
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        assertThat(userResponse.extract().statusCode()).isEqualTo(403); // verify status code
        assertThat(userResponseBody.getSuccess()).isEqualTo(false);
        assertThat(userResponseBody.getMessage()).isEqualTo("Email, password and name are required fields"); // verify error message
    }

    @Test
    @DisplayName("Create a user without a password")
    public void testCreateUserWithoutPasswordReturnsError() {
        userResponse = createUniqueUserAndReturnAsResponse(noPasswordUserRequest);
        userResponseBody = userResponse.extract().body().as(UserResponse.class);

        assertThat(userResponse.extract().statusCode()).isEqualTo(403); // verify status code
        assertThat(userResponseBody.getSuccess()).isEqualTo(false);
        assertThat(userResponseBody.getMessage()).isEqualTo("Email, password and name are required fields"); // verify error message
    }
}
