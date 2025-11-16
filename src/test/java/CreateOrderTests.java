import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderTests extends BaseTest {
    String ingredientsJson;

    @Test
    @DisplayName("Create an order without authorization and without ingredients")
    public void testCreateEmptyOrderWithNoAuth() {
        orderResponse = createOrderWithNoAuth("");

        orderResponse.assertThat().statusCode(400);
        orderResponse.assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create an order without authorization but with ingredients")
    public void testCreateOrderWithNoAuth() {

        ingredientsJson = getIngredients();
        orderResponse = createOrderWithNoAuth(ingredientsJson);

        orderResponse.assertThat().statusCode(200);
        orderResponse.assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create an order with authorization and without ingredients")
    public void testCreateEmptyOrderWithAuth() {
        userResponse = createUniqueUserAndReturnAsResponse(userRequest); // create user
        userResponseBody = userResponse.extract().as(UserResponse.class); // get token
        ValidatableResponse orderResponse = createOrderWithAuth(userResponseBody.getAccessToken(), ""); // create order

        orderResponse.assertThat().statusCode(400);
        orderResponse.assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Create an order with authorization and with ingredients")
    public void testCreateOrderWithAuth() {
        userResponse = createUniqueUserAndReturnAsResponse(userRequest); // create user
        userResponseBody = userResponse.extract().as(UserResponse.class); // get token
        ValidatableResponse orderResponse = createOrderWithAuth(userResponseBody.getAccessToken(), getIngredients()); // create order

        orderResponse.assertThat().statusCode(200);
        orderResponse.assertThat().body("success", equalTo(true));
    }
}
