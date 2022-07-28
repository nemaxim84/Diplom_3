package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccPage {
    @FindBy(xpath = ".//a[contains(@class,'Account_link')][contains(text(),'Профиль')]")
    private SelenideElement profileButton;

    public boolean existOrderButton() {
        profileButton.shouldHave(Condition.visible);
        return profileButton.exists();
    }

    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header')][contains(text(),'Конструктор')]")
    private SelenideElement constructorButton;
    public void clickConstructorButton(){
        constructorButton.click();
    }
    @FindBy(xpath = ".//div[contains(@class,'AppHeader_header__logo')]")
    private SelenideElement logoButton;
    public void clickLogoButton(){
        logoButton.click();
    }
}
