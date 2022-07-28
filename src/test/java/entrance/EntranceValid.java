package entrance;

import User.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RecoveryPassPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class EntranceValid {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPassPage recoveryPassPage;

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        recoveryPassPage = page(RecoveryPassPage.class);
        mainPage.clickAccountButton();
        loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
    }

    @After
    public void DeleteUser() {
        User user = new User();
        user.deleteUser(email, pass);
    }

    //Вход по кнопке «Войти в аккаунт» на главной
    @Test
    public void CheckEnteranceLoginButtonMainPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    //Вход через кнопку «Личный кабинет»
    @Test
    public void CheckEnterancePersonalAccMainPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    //Вход через кнопку в форме регистрации
    @Test
    public void CheckEnteranceButtonRegistrationPageTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.ClickEnteranceButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }

    //Вход через кнопку восстановление пароля
    @Test
    public void CheckEnterancePassRecoveryTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickAccountButton();
        loginPage.clickRecoveryPassButton();
        recoveryPassPage.clickRecoveryPassButton();
        loginPage.entrance(email, pass);
        Assert.assertTrue(mainPage.existOrderButton());
    }
}