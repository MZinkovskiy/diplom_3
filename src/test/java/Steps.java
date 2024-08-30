import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Steps {
    public final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    MainPage mainPage;

    @Step("Создание пользователя и получение accessToken")
    public String createUser(String nameUser, String emailUser, String passwordUser) {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body("{\n" +
                        "    \"name\": \"" + nameUser + "\",\n" +
                        "    \"email\": \"" + emailUser + "\",\n" +
                        "    \"password\": \"" + passwordUser + "\"\n" +
                        "}")
                .when()
                .post("/api/auth/register")
                .path("accessToken");
        accessToken = accessToken.substring(7, accessToken.length());
        return accessToken;
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .auth()
                .oauth2(accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .body("success", is(true));
    }

    @Step("Жмем «Войти в аккаунт» на главной странице")
    public void clickLoginButton(WebDriver driver) {
        mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
    }

    @Step("Жмем «Личный кабинет» на главной странице")
    public void clickPersonalAccount(WebDriver driver) {
        mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
    }

    @Step("Заполняем поля и жмем «Войти» на странице авторизации")
    public void login(WebDriver driver, String emailUser, String passwordUser) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillingLoginForm(emailUser, passwordUser);
    }

    @Step("Жмем «Зарегистрироваться» на странице «Личный кабинет»")
    public void clickRegistr(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrButton();
    }

}
