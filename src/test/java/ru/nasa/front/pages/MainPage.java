package ru.nasa.front.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.nasa.front.constants.Constants.PAGE_LOAD_TIME;

public class MainPage extends AbstractPage<MainPage> {

    public MainPage() {
        super();
        this.url = "https://api.nasa.gov/";
    }

    public SelenideElement getFNameField() {
        return $("input#user_first_name");
    }

    public SelenideElement getLNameField() {
        return $("input#user_last_name");
    }

    public SelenideElement getEmailField() {
        return $("input#user_email");
    }

    public SelenideElement getApplicationUrl() {
        return $("input#user_use_description");
    }

    public SelenideElement getSignupButton() {
        return $("button.btn.btn-lg.btn-primary");
    }

    public MainPage navigate() {
        return super.navigate(this.getClass());
    }

    public SelenideElement getInfoPic() {
        return $("img#infoPic.infoDiv");
    }

    public SelenideElement getTextIconElement() {
        return $("label#infoTab");
    }

    @Override
    public MainPage waitPageLoaded() {
        $("div#apidatagov_signup").waitWhile(text("Loading signup form..."), PAGE_LOAD_TIME);
        return this;
    }

    public SelenideElement getHighlightedElement() {
        return $(".usa-section");
    }

    public SelenideElement getTextError() {
        return $("li.parsley-required");
    }

    public SelenideElement getCodeApiKey() {
        return $("code.signup-key");
    }

    public SelenideElement getListToolbar() {
        return $("ul.usa-nav-primary");//компонент?
    }

    public SelenideElement getNavigation() {
        return $(".usa-nav");
    }

    public SelenideElement getLogoNavigation() {
        return $(".usa-navbar");
    }
}
