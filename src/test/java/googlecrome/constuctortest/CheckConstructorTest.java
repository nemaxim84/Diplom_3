package googlecrome.constuctortest;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        user = user.createUserRandom();
        loginPage = page(LoginPage.class);
        userClient.createUserRest(user.getName(), user.getEmail(), user.getPassword());
        mainPage = open(mainPage.URL, MainPage.class);
        mainPage.clickEnteranceAccountButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем Появление \"Начинки\" при нажатии на кнопку \"Начинки\"")
    public void checkClickFillingTest() {
        mainPage.clickFillingButton();
        assertTrue(mainPage.existFilling());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Соус\" при нажатии на кнопку \"Соус\"")
    public void checkClickSauceTest() {
        mainPage.clickSauceButton();
        assertTrue(mainPage.existSauce());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем появление \"Булки\" при нажатии на кнопку \"Булки\"")
    public void checkClickBunsTest() {
        mainPage.clickFillingButton();
        mainPage.existFilling();
        mainPage.clickBunsButton();
        assertTrue(mainPage.existBuns());
    }
}