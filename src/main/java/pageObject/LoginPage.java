package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    @FindBy(xpath = ".//a[contains(@class,'Auth_link')][contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationButton;

    public void clickRegButton() {
        registrationButton.click();
    }

    @FindBy(xpath = ".//h2[contains(text(),'Вход')]")
    private SelenideElement wordVhod;

    public boolean existVlod() {
        wordVhod.shouldHave(Condition.visible);
        return wordVhod.exists();
    }

    @FindBy(xpath = ".//div[contains(@class,'AppHeader_header')]")
    private SelenideElement logoButton;

    public void clickLogoButton() {
        logoButton.click();
    }

    @FindBy(xpath = ".//label[contains(@class,'input__placeholder')][contains(text(),'Email')]/../input")
    private SelenideElement emailField;
    @FindBy(xpath = ".//label[contains(@class,'input__placeholder')][contains(text(),'Пароль')]/../input")
    private SelenideElement passField;
    @FindBy(xpath = ".//button[contains(@class,'button_button')]")
    private SelenideElement entanceButton;

    public void entrance(String email, String pass) {
        emailField.setValue(email);
        passField.setValue(pass);
        entanceButton.click();
    }

    @FindBy(xpath = ".//a[contains(@class,'Auth_link')][contains(text(),'пароль')]")
    private SelenideElement recoveryPassButton;

    public void clickRecoveryPassButton() {
        recoveryPassButton.click();
    }
}
