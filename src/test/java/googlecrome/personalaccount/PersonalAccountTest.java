package googlecrome.personalaccount;

import user.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalAccPage;
import user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccPage personalAccPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        user = user.createUserRandom();
        loginPage = page(LoginPage.class);
        userClient.createUserRest(user.getName(), user.getEmail(), user.getPassword());
        personalAccPage = page(PersonalAccPage.class);
        loginPage = page(LoginPage.class);
        mainPage = open(mainPage.URL, MainPage.class);
        mainPage.clickPersonalAccButton();
        loginPage.entrance(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccButton();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход по клику на «Личный кабинет»")
    public void checkClickPersonalAccTest() {
        assertTrue(personalAccPage.existOrderButton());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход из личного кабинета в конструктор по клику \"Конструктор\"")
    public void checkClickConstructorFromPersonalAccTest() {
        personalAccPage.clickConstructorButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем переход из личного кабинета в конструктор по клику на логотип")
    public void checkClickLogoFromPersonalAccTest() {
        personalAccPage.clickLogoButton();
        assertTrue(mainPage.existTextBurger());
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем кнопку Выход в личном кабинете")
    public void checkExitButtonFromPersonalAccTest() {
        personalAccPage.clickExitButton();
        assertTrue(loginPage.existVhod());
    }
}
