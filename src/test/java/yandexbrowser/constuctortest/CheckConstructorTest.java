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
import user.UserClient;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    private MainPage mainPage;
    private LoginPage loginPage;
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
        mainPage = open(mainPage.URL, MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void checkClickFillingTest() {
        mainPage.clickFillingButton();
        assertTrue(mainPage.existFilling());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void checkClickSauceTest() {
        mainPage.clickSauceButton();
        assertTrue(mainPage.existSauce());
    }

    @Test
    @DisplayName("Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void checkClickBunsTest() {
        mainPage.clickFillingButton();
        mainPage.existFilling();
        mainPage.clickBunsButton();
        assertTrue(mainPage.existBuns());
    }
}