package ru.nasa.front.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.function.Supplier;


public class Toolbar<T extends ElementsContainer> extends ElementsContainer {
    private Supplier<T> toolbarItemConstructor;

    public Toolbar(SelenideElement el, Supplier<T> toolbarItemConstructor) {
        this.toolbarItemConstructor = toolbarItemConstructor;
        setSelf(el);
    }

    public T getSectionByName(String name) {
        T toolbarItem = toolbarItemConstructor.get();
        toolbarItem.setSelf(getSelf().find(By.xpath(String.format(".//*[text()='%s']/../..", name))));
        return toolbarItem;
    }

    public SelenideElement getSelected() {
       // T toolbarItem = toolbarItemConstructor.get();
      //  toolbarItem.setSelf(getSelf().find(".currentDiv"));
        return getSelf().find(".currentDiv");
    }
}
