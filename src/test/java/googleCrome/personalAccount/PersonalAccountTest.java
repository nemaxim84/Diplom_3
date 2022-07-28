package googleCrome.personalAccount;

import User.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PersonalAccPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PersonalAccountTest {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PersonalAccPage personalAccPage;

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickAccountButton();
        loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
        personalAccPage = page(PersonalAccPage.class);
    }

    @After
    public void DeleteUser() {
        User user = new User();
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход по клику на «Личный кабинет»")
    public void CheckClickPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        Assert.assertTrue(personalAccPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход из личного кабинета в конструктор по клику \"Конструктор\"")
    public void CheckClickConstructorFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickConstructorButton();
        Assert.assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход из личного кабинета в конструктор по клику на логотип")
    public void CheckClickLogoFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickLogoButton();
        Assert.assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем кнопку Выход в личном кабинете")
    public void CheckExitButtonFromPersonalAccTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(email, pass);
        mainPage.clickPersonalAccButton();
        personalAccPage.clickExitButton();
        Assert.assertTrue(loginPage.existVlod());
    }
}
