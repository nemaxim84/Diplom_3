package yandexbrowser.registration;

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
import user.User;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationNotValidTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String name;
    private String email;
    private String pass;
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
        name = user.getName();
        email=user.getEmail();
        pass=User.shortPass;
        mainPage = open(mainPage.getUrl(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void deleteUser() throws Exception {
        WebDriverRunner.getWebDriver().close();
        try {
            user.deleteUser(email, pass);
        } catch (Exception e){
            System.out.println("Пользователь не создался");
        }
    }

    @Test
    @DisplayName("YandexBrowser. Проверяем, что появляется ошибка при вводе пароля меньше 6 символов")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(name, email, pass);
        assertTrue(registrationPage.errorExist());
    }
}
