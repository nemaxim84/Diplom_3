package googlecrome.registration;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationValidTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String name;
    private String email;
    private String pass;
    private User user;

    @Before
    public void openPage() {
        user = new User();
        name = user.getName();
        email=user.getEmail();
        pass=user.getPassword();
        mainPage = open(mainPage.getUrl(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void deleteUser() {
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем успешную регистрацию")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(name, email, pass);
        assertTrue(loginPage.existVlod());
    }
}
