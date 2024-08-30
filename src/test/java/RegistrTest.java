import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;

public class RegistrTest {
    private WebDriver driver;

    Steps steps = new Steps();

    @Before
    public void setup() {
        driver = WebDriverFactory.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void browserClose() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void newUserTrue() {
        String nameUser = randomAlphabetic(12);
        String emailUser = randomAlphabetic(8) + "@yandex.ru";
        String passwordUser = randomAlphabetic(10);

        steps.clickPersonalAccount(driver);
        steps.clickRegistr(driver);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillingRegistrForm(nameUser, emailUser, passwordUser);
    }

    @Test
    @DisplayName("Попытка регистрации пользователя с некорректным паролем")
    public void newUserPasswordErrorFalse() {
        String nameUser = randomAlphabetic(12);
        String emailUser = randomAlphabetic(8) + "@yandex.ru";
        String passwordUser = randomAlphabetic(5);

        steps.clickPersonalAccount(driver);
        steps.clickRegistr(driver);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillingRegistrForm(nameUser, emailUser, passwordUser);

        assertTrue(registerPage.findElementPaswordErrorText());
    }

}
