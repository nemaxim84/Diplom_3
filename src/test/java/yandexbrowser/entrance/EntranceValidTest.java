package yandexbrowser.entrance;

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
import pageobject.RecoveryPassPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class EntranceValidTest {
    private MainPage mainPage = page(MainPage.class);
    private String name;
    private String email;
    private String pass;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoveryPassPage recoveryPassPage;
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
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage = open(mainPage.getUrl(), MainPage.class);
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("YandexBrowser. Вход по кнопке «Войти в аккаунт» на главной")
    public void checkEnteranceLoginButtonMainPageTest() {
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку «Личный кабинет»")
    public void checkEnterancePersonalAccMainPageTest() {
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку в форме регистрации")
    public void checkEnteranceButtonRegistrationPageTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.clickEnteranceButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку восстановление пароля")
    public void checkEnterancePassRecoveryTest() {
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }
}