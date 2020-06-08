package ru.nasa.front.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import ru.nasa.front.components.Toolbar;
import ru.nasa.front.components.ToolbarItemMainPage;

import static com.codeborne.selenide.Condition.have;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.nasa.front.constants.Constants.PAGE_LOAD_TIME;

public class MainPage extends AbstractPage<MainPage> {
    private Toolbar<ToolbarItemMainPage> toolbar = new Toolbar<>($("#headerContent.usa-nav-primary"), ToolbarItemMainPage::new);

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

    public SelenideElement getHighlightedElement() {
        return $(".usa-section");
    }

    public SelenideElement getTextError() {
        return $("li.parsley-required");
    }

    public SelenideElement getCodeApiKey() {
        return $("code.signup-key");
    }

    public ToolbarItemMainPage getToolbarSelectedItem() {
        return toolbar.getSelected();
    }

    public ToolbarItemMainPage findLinkToolbar(String toolbarName) {
        return toolbar.getSectionByName(toolbarName);
    }

    public Point findPointH2(String name) {
        SelenideElement h2Title = $$("h2").find(have(text(name)));
        return h2Title.toWebElement().getLocation();
    }

    @Override
    public MainPage waitPageLoaded() {
        $("div#apidatagov_signup").waitWhile(text("Loading signup form..."), PAGE_LOAD_TIME);
        return this;
    }
}
