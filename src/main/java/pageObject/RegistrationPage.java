package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {
    @FindBy(xpath = ".//label[contains(@class,'input__placeholder')][contains(text(),'Имя')]/../input")
    private SelenideElement nameSet;

    @FindBy(xpath = ".//label[contains(@class,'input__placeholder')][contains(text(),'Email')]/../input")
    private SelenideElement emailSet;
    @FindBy(xpath = ".//label[contains(@class,'input__placeholder')][contains(text(),'Пароль')]/../input")
    private SelenideElement passSet;
    @FindBy(xpath = ".//button[contains(@class,'utton_button')][contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrButton;

    public void setRegData(String name, String email, String pass) {
        nameSet.setValue(name);
        emailSet.setValue(email);
        passSet.setValue(pass);
        registrButton.click();
    }

    @FindBy(xpath = ".//p[contains(@class,'input__error')]")
    private SelenideElement errorText;

    public boolean errorExist() {
        errorText.shouldHave(Condition.visible);
        return errorText.exists();
    }

    @FindBy(xpath = ".//a[contains(@class,'Auth_link')]")
    private SelenideElement enteranceButton;

    public void ClickEnteranceButton() {
        enteranceButton.click();
    }
}
