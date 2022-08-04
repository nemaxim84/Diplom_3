package yandexbrowser.entrance;

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
import pageobject.RecoveryPassPage;
import pageobject.RegistrationPage;
import user.UserClient;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class EntranceValidTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoveryPassPage recoveryPassPage;
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
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage = open(mainPage.URL, MainPage.class);
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("YandexBrowser. Вход по кнопке «Войти в аккаунт» на главной")
    public void checkEnteranceLoginButtonMainPageTest() {
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку «Личный кабинет»")
    public void checkEnterancePersonalAccMainPageTest() {
        mainPage.clickPersonalAccButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку в форме регистрации")
    public void checkEnteranceButtonRegistrationPageTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.clickEnteranceButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку восстановление пароля")
    public void checkEnterancePassRecoveryTest() {
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }
}