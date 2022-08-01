package googlecrome.entrance;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RecoveryPassPage;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class EntranceValidTest {
    private MainPage mainPage = page(MainPage.class);
    private String name;
    private String email;
    private String pass;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoveryPassPage recoveryPassPage;
    private User user;

    @Before
    public void openPage() {
        user = new User();
        loginPage = page(LoginPage.class);
        name = user.getName();
        email = user.getEmail();
        pass = user.getPassword();
        user.createUser(name, email, pass);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage = open(mainPage.getUrl(), MainPage.class);
    }

    @After
    public void deleteUser() {
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("GoogleCrome. Вход по кнопке «Войти в аккаунт» на главной")
    public void checkEnteranceLoginButtonMainPageTest() {
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку «Личный кабинет»")
    public void checkEnterancePersonalAccMainPageTest() {
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку в форме регистрации")
    public void checkEnteranceButtonRegistrationPageTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.clickEnteranceButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку восстановление пароля")
    public void checkEnterancePassRecoveryTest() {
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(email, pass);
        assertTrue(mainPage.existOrderButton());
    }
}