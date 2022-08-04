package googlecrome.entrance;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RecoveryPassPage;
import pageobject.RegistrationPage;
import user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class EntranceValidTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoveryPassPage recoveryPassPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        user = user.createUserRandom();
        loginPage = page(LoginPage.class);
        userClient.createUserRest(user.getName(), user.getEmail(), user.getPassword());
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage = open(mainPage.URL, MainPage.class);
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("GoogleCrome. Вход по кнопке «Войти в аккаунт» на главной")
    public void checkEnteranceLoginButtonMainPageTest() {
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку «Личный кабинет»")
    public void checkEnterancePersonalAccMainPageTest() {
        mainPage.clickPersonalAccButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку в форме регистрации")
    public void checkEnteranceButtonRegistrationPageTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.clickEnteranceButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Вход через кнопку восстановление пароля")
    public void checkEnterancePassRecoveryTest() {
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        assertTrue(mainPage.existOrderButton());
    }
}