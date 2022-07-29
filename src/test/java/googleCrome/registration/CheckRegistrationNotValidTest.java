package googleCrome.registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationNotValidTest {
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String name = "Maxim1";
    String email = "nnn1@ya.ru";
    String pass = "1234";

    @Before
    public void OpenPage() {
        mainPage = open(mainPage.getUrl(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем, что появляется ошибка при вводе пароля меньше 6 символов")
    public void CheckRegistrationValidTest() {
        mainPage.clickAccountButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegButton();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.setRegData(name, email, pass);
        assertTrue(registrationPage.errorExist());
    }
}
