package yandexBrowser.entrance;

import User.User;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RecoveryPassPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class EntranceValidTest {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPassPage recoveryPassPage;

    @Before
    public void OpenPage() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver2.exe");
        options.setBinary("C:\\Users\\memax\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        mainPage = open(mainPage.getUrl(), MainPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage.clickAccountButton();
        loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
    }

    @After
    public void DeleteUser() {
        WebDriverRunner.getWebDriver().close();
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("YandexBrowser. Вход по кнопке «Войти в аккаунт» на главной")
    public void CheckEnteranceLoginButtonMainPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку «Личный кабинет»")
    public void CheckEnterancePersonalAccMainPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку в форме регистрации")
    public void CheckEnteranceButtonRegistrationPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.ClickEnteranceButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("YandexBrowser. Вход через кнопку восстановление пароля")
    public void CheckEnterancePassRecoveryTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }
}