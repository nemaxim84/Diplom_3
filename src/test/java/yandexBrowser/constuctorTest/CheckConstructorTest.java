package yandexBrowser.constuctorTest;

import User.User;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.MainPage;

import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class CheckConstructorTest {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";
    LoginPage loginPage;
    RegistrationPage registrationPage;

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
        mainPage.clickAccountButton();
        loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
    }

    @After
    public void DeleteUser() {
        closeWebDriver();
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void CheckClickFillingTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.existFilling();
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void CheckClickSauceTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickSauceButton();
        mainPage.existSauce();
    }

    @Test
    @DisplayName("Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void CheckClickBunsTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.existBuns();
    }
}