package yandexBrowser.registration;

import User.User;
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

public class CheckRegistrationValidTest {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";

    @Before
    public void OpenPage() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver2.exe");
        options.setBinary("C:\\Users\\memax\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        mainPage = open(mainPage.getUrl(), MainPage.class);
    }

    @After
    public void DeleteUser() {
        closeWebDriver();
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем успешную регистрацию")
    public void CheckRegistrationValidTest() {
        mainPage.clickAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
        assertTrue(loginPage.existVlod());
    }
}
