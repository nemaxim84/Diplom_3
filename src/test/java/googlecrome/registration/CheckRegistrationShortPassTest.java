package googlecrome.registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import user.User;
import user.UserClient;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationShortPassTest {
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private User user;
    private UserClient userClient = new UserClient();

    @Before
    public void openPage() {
        user = user.createUserRandom();
        mainPage = open(mainPage.URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
    }

    @After
    public void deleteUser() {
        try {
            userClient.deleteUser(user.getEmail(), User.SHORT_PASS);
        } catch (Exception e){
            logger.log(Level.WARNING,"Пользователь не создавался. Пароль меньше 6 символов");
        }
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем, что появляется ошибка при вводе пароля меньше 6 символов")
    public void checkRegistrationNotValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(user.getName(), user.getEmail(), User.SHORT_PASS);
        assertTrue(registrationPage.errorExist());
    }
}
