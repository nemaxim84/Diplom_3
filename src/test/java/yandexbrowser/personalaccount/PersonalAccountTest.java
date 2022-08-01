package yandexbrowser.personalaccount;

import user.User;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalAccPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    private MainPage mainPage = page(MainPage.class);
    private String name;
    private String email;
    private String pass;
    private LoginPage loginPage;
    private PersonalAccPage personalAccPage;
    private User user;

    @Before
    public void openPage() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver2.exe");
        options.setBinary("C:\\Users\\memax\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        user = new User();
        loginPage = page(LoginPage.class);
        name = user.getName();
        email = user.getEmail();
        pass = user.getPassword();
        user.createUser(name, email, pass);
        personalAccPage = page(PersonalAccPage.class);
        loginPage=page(LoginPage.class);
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        user.deleteUser(email, pass);
    }

    //Проверяем переход по клику на «Личный кабинет»
    @Test
    @DisplayName("YandexBrowser. Проверяем переход по клику на «Личный кабинет»")
    public void checkClickPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        assertTrue(personalAccPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем переход из личного кабинета в конструктор по клику \"Конструктор\"")
    public void checkClickConstructorFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickConstructorButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем переход из личного кабинета в конструктор по клику на логотип")
    public void checkClickLogoFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickLogoButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем кнопку Выход в личном кабинете")
    public void checkExitButtonFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickExitButton();
        assertTrue(loginPage.existVlod());
    }
}
