package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private int time_delay = 50;

    private By nameField = By.xpath("//fieldset[1]/div/div/input");
    private By emailField = By.xpath("//fieldset[2]/div/div/input");
    private By passwordField = By.xpath("//fieldset[3]/div/div/input");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginButton = By.xpath("//a[text() = 'Войти']");
    private By paswordErrorText = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение поля name")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнение поля email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля password")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнение полей регистрации пользователя")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполнение полей регистрации пользователя")
    public void fillingRegistrForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка наличия элемента «Некорректный пароль»")
    public boolean findElementPaswordErrorText() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(paswordErrorText));
        WebElement element = driver.findElement(paswordErrorText);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }
}
