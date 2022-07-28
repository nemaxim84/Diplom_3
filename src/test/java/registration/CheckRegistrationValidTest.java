package registration;

import User.User;
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
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
    }

    @After
    public void DeleteUser() {
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    public void CheckRegistrationValidTest() {
        mainPage.clickAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
        assertTrue(loginPage.existVlod());
    }
}
