package yandexbrowser.personalaccount;

import user.User;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalAccPage;
import user.UserClient;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccPage personalAccPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver2.exe");
        options.setBinary("C:\\Users\\memax\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        user = user.createUserRandom();
        loginPage = page(LoginPage.class);
        userClient.createUserRest(user.getName(), user.getEmail(), user.getPassword());
        personalAccPage = page(PersonalAccPage.class);
        loginPage = page(LoginPage.class);
        mainPage = open(mainPage.URL, MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccButton();
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    //Проверяем переход по клику на «Личный кабинет»
    @Test
    @DisplayName("YandexBrowser. Проверяем переход по клику на «Личный кабинет»")
    public void checkClickPersonalAccTest() {
        assertTrue(personalAccPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем переход из личного кабинета в конструктор по клику \"Конструктор\"")
    public void checkClickConstructorFromPersonalAccTest() {
        personalAccPage.clickConstructorButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем переход из личного кабинета в конструктор по клику на логотип")
    public void checkClickLogoFromPersonalAccTest() {
        personalAccPage.clickLogoButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем кнопку Выход в личном кабинете")
    public void checkExitButtonFromPersonalAccTest() {
        personalAccPage.clickExitButton();
        assertTrue(loginPage.existVhod());
    }
}
