package pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class RecoveryPassPage {
    @FindBy(xpath = " .//a[contains(@class,'Auth_link')]")
    private SelenideElement entranceButton;

    public void clickRecoveryPassButton() {
        entranceButton.click();
    }
}
