package googlecrome.constuctortest;

import user.RequestSpecification;
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

public class CheckConstructorTest {
    private MainPage mainPage = page(MainPage.class);
    private String name;
    private String email;
    private String pass;
    private LoginPage loginPage;
    private User user;

    @Before
    public void openPage() {
        user = new User();
        loginPage = page(LoginPage.class);
        name = user.getName();
        email = user.getEmail();
        pass = user.getPassword();
        user.createUser(name, email, pass);
        mainPage = open(mainPage.getUrl(), MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(email, pass);
    }

    @After
    public void deleteUser() {
        user.deleteUser(email, pass);
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void checkClickFillingTest() {
        mainPage.clickFillingButton();
        mainPage.existFilling();
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void checkClickSauceTest() {
        mainPage.clickSauceButton();
        mainPage.existSauce();
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void checkClickBunsTest() {
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.existBuns();
    }
}