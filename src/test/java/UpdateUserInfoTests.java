import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateUserInfoTests extends BaseTest {

    @Before
    public void createUser() {
        userResponse = createUniqueUserAndReturnAsResponse(userRequest); // create user
        userResponseBody = userResponse.extract().body().as(UserResponse.class);
    }

    @Test
    @DisplayName("Update user info with authorization")
    public void testUpdateUserInfoWithAuthIsSuccessful() {
        ValidatableResponse response =
                baseHttpClient.patchRequestWithAuth(
                        userResponseBody.getAccessToken(),
                        USER_ENDPOINT,
                        updateUserRequestBody
                ); // update user data

        UserResponse updateUserResponseBody = response.extract().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(200); // verify status code
        assertThat(updateUserResponseBody.getUser().getEmail())
                .isEqualTo(updateUserRequestBody.getEmail()); // verify updated email
        assertThat(updateUserResponseBody.getUser().getName())
                .isEqualTo(updateUserRequestBody.getName()); // verify updated name

        // verify password update by logging in with the new password
        ValidatableResponse passwordResponse =
                baseHttpClient.postRequest(LOGIN_USER_ENDPOINT, updateUserRequestBody);
        passwordResponse.assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Update user info without authorization")
    public void testUpdateUserInfoWithoutAuthReturnsError() {
        ValidatableResponse response =
                baseHttpClient.patchRequest(USER_ENDPOINT, updateUserRequestBody);
        UserResponse updateUserResponseBody = response.extract().as(UserResponse.class);

        assertThat(response.extract().statusCode()).isEqualTo(401); // verify status code
        assertThat(updateUserResponseBody.getSuccess()).isEqualTo(false);
        assertThat(updateUserResponseBody.getMessage()).isEqualTo("You should be authorised");
    }
}
