package googleCrome.constuctorTest;

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

    @Test
    @DisplayName("GoogleCrome. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void CheckClickFillingTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.existFilling();
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void CheckClickSauceTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickSauceButton();
        mainPage.existSauce();
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void CheckClickBunsTest() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.existBuns();
    }
}