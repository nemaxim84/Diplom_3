package yandexbrowser.constuctortest;

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

import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class CheckConstructorTest {
    private MainPage mainPage = page(MainPage.class);
    private String name;
    private String email;
    private String pass;
    private LoginPage loginPage;
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
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void checkClickFillingTest() {
        mainPage.clickFillingButton();
        mainPage.existFilling();
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void checkClickSauceTest() {
        mainPage.clickSauceButton();
        mainPage.existSauce();
    }

    @Test
    @DisplayName("Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void checkClickBunsTest() {
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.existBuns();
    }
}