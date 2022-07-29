package yandexBrowser.registration;

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
import static org.junit.Assert.assertTrue;

public class CheckRegistrationNotValidTest {
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234";

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
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void DeleteUser() {
        WebDriverRunner.getWebDriver().close();
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем, что появляется ошибка при вводе пароля меньше 6 символов")
    public void CheckRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(name, email, pass);
        assertTrue(registrationPage.errorExist());
    }
}
