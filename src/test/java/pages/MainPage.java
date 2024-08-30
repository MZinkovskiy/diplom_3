package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private int time_delay = 150;

    private By constructorHead = By.xpath("//h1[text() = 'Соберите бургер']");
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By rollsButton = By.xpath("//div/span[text()='Булки']");
    private By saucesButton = By.xpath("//div/span[text()='Соусы']");
    private By fillingsButton = By.xpath("//div/span[text()='Начинки']");
    private By activeTab = By.xpath("//div[contains(@class, 'current')]/span");
    private By placeOrderButton = By.xpath("//button[text() = 'Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на вкладку «Личный Кабинет»")
    public void clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажимаем на вкладку «Войти в аккаунт»")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажимаем на вкладку «Булки»")
    public void clickRollsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(rollsButton));
        driver.findElement(rollsButton).click();
    }

    @Step("Нажимаем на вкладку «Соусы»")
    public void clickSaucesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesButton));
        driver.findElement(saucesButton).click();
    }

    @Step("Нажимаем на вкладку «Начинки»")
    public void clickFillingsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsButton));
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка наличия кнопки «Оформить заказ»")
    // Кнопка «Оформить заказ» есть только у авторизованного пользователя
    public boolean findButtonPlaceOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        System.out.println("2");
        WebElement element = driver.findElement(placeOrderButton);
        System.out.println("3 - element: " + element);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Проверка наличия заголовка «Соберите бургер» для конструктора")
    public boolean findConstructorHead() {
        new WebDriverWait(driver, Duration.ofSeconds(time_delay))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHead));
        WebElement element = driver.findElement(constructorHead);
        if (element != null) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Получение названия активной вкладки конструктора")
    public String activeTab() {
        String element = driver.findElement(activeTab).getText();
        return element;
    }
}
