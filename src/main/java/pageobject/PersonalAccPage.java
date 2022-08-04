package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccPage {
    @FindBy(xpath = ".//a[contains(@class,'Account_link')][contains(text(),'Профиль')]")
    private SelenideElement profileButton;
    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header')][contains(text(),'Конструктор')]")
    private SelenideElement constructorButton;
    @FindBy(xpath = ".//div[contains(@class,'AppHeader_header__logo')]")
    private SelenideElement logoButton;
    @FindBy(xpath = ".//button[contains(@class,'button')][contains(text(),'Выход')]")
    private SelenideElement exitButton;

    public boolean existOrderButton() {
        profileButton.shouldHave(Condition.visible);
        return profileButton.exists();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public void clickLogoButton() {
        logoButton.click();
    }

    public void clickExitButton() {
        exitButton.click();
    }
}
