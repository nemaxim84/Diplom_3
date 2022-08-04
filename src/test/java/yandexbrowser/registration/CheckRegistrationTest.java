package yandexbrowser.registration;

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
import user.UserClient;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
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
        mainPage = open(mainPage.URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void deleteUser() {
        WebDriverRunner.getWebDriver().close();
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем успешную регистрацию")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(loginPage.existVhod());
    }
}
