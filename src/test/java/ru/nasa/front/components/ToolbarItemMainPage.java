package ru.nasa.front.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Selenide.$;

public class ToolbarItemMainPage extends ElementsContainer {

    public SelenideElement getItemA() {
        return getSelf().$(".usa-nav-link");
    }

    public Point getLocationItem() {
        return getSelf().$(".usa-nav-link").getLocation();
    }

    public ToolbarItemMainPage select() {
        getSelf().click();
        return this;
    }
}
