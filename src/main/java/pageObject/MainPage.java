package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    public String getUrl() {
        return URL;
    }

    //Кнопка "Личный кабинет"
    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header__linkText')][contains(text(),'Личный')]")
    private SelenideElement accountButton;

    public void clickAccountButton() {
        accountButton.click();
    }

    @FindBy(xpath = ".//button[contains(@class,'button_button')]")
    private SelenideElement enteranceAccountButton;

    public void clickEnteranceAccountButton() {
        enteranceAccountButton.shouldHave(Condition.visible);
        enteranceAccountButton.click();
    }

    @FindBy(xpath = ".//button[contains(@class,'button_button')][contains(text(),'Оформить')]")
    private SelenideElement orderButton;

    public boolean existOrderButton() {
        orderButton.shouldHave(Condition.visible);
        return orderButton.exists();
    }

    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header')][contains(text(),'Личный')]")
    private SelenideElement personalAccButton;

    public void clickPersonalAccButton() {
        personalAccButton.click();
    }

    @FindBy(xpath = ".//h1[contains(@class,'text text')][contains(text(),'бургер')]")
    private SelenideElement textBurger;
    public boolean existTextBurger() {
        textBurger.shouldHave(Condition.visible);
        return textBurger.exists();
    }
}

