import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;

public class LoginLogoutTest {
    private WebDriver driver;

    String nameUser = randomAlphabetic(12);
    String emailUser = randomAlphabetic(8) + "@yandex.ru";
    String passwordUser = randomAlphabetic(10);
    String accessToken;

    MainPage mainPage;
    Steps steps = new Steps();

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginUserMainPage() {
        steps.clickLoginButton(driver);
        steps.login(driver, emailUser, passwordUser);

        //Если пользователь авторизовался успешно - у него будет доступна кнопка «Оформить заказ»
        assertTrue(mainPage.findButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginUserButtonPersonalAccount() {
        steps.clickPersonalAccount(driver);
        steps.login(driver, emailUser, passwordUser);

        assertTrue(mainPage.findButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginUserOnFormRegistr() {
        steps.clickLoginButton(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();

        steps.login(driver, emailUser, passwordUser);

        assertTrue(mainPage.findButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginUserOnFormRecoveryPassword() {
        steps.clickLoginButton(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordButton();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();

        steps.login(driver, emailUser, passwordUser);

        assertTrue(mainPage.findButtonPlaceOrder());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logoutUserFromPersonalAccount() {
        steps.clickLoginButton(driver);
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.findElementHeadOrder());
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
        accessToken = steps.createUser(nameUser, emailUser, passwordUser);
    }

    @After
    public void browserClose() {
        steps.deleteUser(accessToken);
        driver.quit();
    }

}
