package googlecrome.registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import user.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class CheckRegistrationNotValidTest {
    private MainPage mainPage = page(MainPage.class);
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private String name;
    private String email;
    private String pass;
    private User user;

    @Before
    public void openPage() {
        user = new User();
        name = user.getName();
        email=user.getEmail();
        pass=User.shortPass;
        mainPage = open(mainPage.getUrl(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);

    }
    @After
    public void tearDown() throws Exception {
        try {
            user.deleteUser(email, pass);
        } catch (Exception e){
            System.out.println("Пользователь не создался");
        }
    }

    @Test
    @DisplayName("GoogleCrome. Проверяем, что появляется ошибка при вводе пароля меньше 6 символов")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountButton();
        loginPage.clickRegButton();
        registrationPage.setRegData(name, email, pass);
        assertTrue(registrationPage.errorExist());
    }
}
