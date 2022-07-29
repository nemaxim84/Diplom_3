package googleCrome.registration;

import User.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationValidTest {
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void DeleteUser() {
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем успешную регистрацию")
    public void CheckRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(name, email, pass);
        assertTrue(loginPage.existVlod());
    }
}
