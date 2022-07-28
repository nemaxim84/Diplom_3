package constuctorTest;

import User.User;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;

import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class CheckConstructorTest {
    MainPage mainPage = page(MainPage.class);
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234qwe";
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
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

    //Проверяем Появление "Начинки" при нажатии на кнопку "Начинки"
    @Test
    public void CheckClickFillingTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.existFilling();
    }

    //Проверяем появление "Соус" при нажатии на кнопку "Соус"
    @Test
    public void CheckClickSauceTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickSauceButton();
        mainPage.existSauce();
    }

    //Проверяем появление "Булки" при нажатии на кнопку "Булки"
    @Test
    public void CheckClickBunsTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.existBuns();
    }
}