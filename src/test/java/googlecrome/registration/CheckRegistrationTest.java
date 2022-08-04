package googlecrome.registration;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import user.UserClient;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        user = user.createUserRandom();
        mainPage = open(mainPage.URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем успешную регистрацию")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(loginPage.existVhod());
    }

}
