import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ProfilePage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;

public class TransitionsTest {
    private WebDriver driver;

    String nameUser = randomAlphabetic(12);
    String emailUser = randomAlphabetic(8) + "@yandex.ru";
    String passwordUser = randomAlphabetic(10);
    String accessToken;

    MainPage mainPage;
    Steps steps = new Steps();

    @Test
    @DisplayName("Переход в личный кабинет")
    public void transitionToPersonalAccount() {
        steps.clickLoginButton(driver);
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.findElementHeadOrder());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalAccountToConstructor() {
        steps.clickLoginButton(driver);
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();

        assertTrue(mainPage.findConstructorHead());
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void transitionFromPersonalAccountOnLogo() {
        steps.clickLoginButton(driver);
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogo();

        assertTrue(mainPage.findConstructorHead());
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void transitionToRolls() {
        mainPage = new MainPage(driver);
        // Поскольку вкладка «Булки» активна по дефолту, перейдем сначала на другую вкладку, а потому вернемся к Булкам
        mainPage.clickSaucesButton();
        mainPage.clickRollsButton();
        assertTrue(mainPage.activeTab().equals("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void transitionToSauces() {
        mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        assertTrue(mainPage.activeTab().equals("Соусы"));
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void transitionToFillings() {
        mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        assertTrue(mainPage.activeTab().equals("Начинки"));
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
