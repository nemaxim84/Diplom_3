package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //Кнопка "Личный кабинет"
    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header__linkText')][contains(text(),'Личный')]")
    private SelenideElement accountButton;
    @FindBy(xpath = ".//button[contains(@class,'button_button')]")
    private SelenideElement enteranceAccountButton;
    @FindBy(xpath = ".//button[contains(@class,'button_button')][contains(text(),'Оформить')]")
    private SelenideElement orderButton;
    @FindBy(xpath = ".//p[contains(@class,'AppHeader_header')][contains(text(),'Личный')]")
    private SelenideElement personalAccButton;
    @FindBy(xpath = ".//h1[contains(@class,'text text')][contains(text(),'бургер')]")
    private SelenideElement textBurger;
    @FindBy(xpath = ".//span[contains(@class,'text text')][contains(text(),'Начинки')]")
    private SelenideElement fillingButton;
    @FindBy(xpath = ".//div[contains(@class,'tab_tab_type')]/span[contains(text(),'Начинки')]")
    private SelenideElement fillingText;
    @FindBy(xpath = ".//span[contains(@class,'text text')][contains(text(),'Соус')]")
    private SelenideElement sauceButton;
    @FindBy(xpath = ".//div[contains(@class,'tab_tab_type')]/span[contains(text(),'Соус')]")
    private SelenideElement sauceText;
    @FindBy(xpath = ".//span[contains(@class,'text text')][contains(text(),'Булки')]")
    private SelenideElement bunsButton;
    @FindBy(xpath = ".//div[contains(@class,'tab_tab_type')]/span[contains(text(),'Булки')]")
    private SelenideElement bunsText;

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickEnteranceAccountButton() {
        enteranceAccountButton.shouldHave(Condition.visible);
        enteranceAccountButton.click();
    }

    public boolean existOrderButton() {
        orderButton.shouldHave(Condition.visible);
        return orderButton.exists();
    }

    public void clickPersonalAccButton() {
        personalAccButton.click();
    }

    public boolean existTextBurger() {
        textBurger.shouldHave(Condition.visible);
        return textBurger.exists();
    }

    public void clickFillingButton() {
        fillingButton.click();
    }

    public boolean existFilling() {
        fillingText.shouldHave(Condition.visible);
        return fillingText.exists();
    }

    public void clickSauceButton() {
        sauceButton.click();
    }

    public boolean existSauce() {
        sauceText.shouldHave(Condition.visible);
        return sauceText.exists();
    }

    public void clickBunsButton() {
        bunsButton.click();
    }

    public boolean existBuns() {
        bunsText.shouldHave(Condition.visible);
        return bunsText.exists();
    }
}

