package ru.nasa.front.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.hamcrest.Matcher;
import ru.nasa.front.pages.MainPage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;

import static com.codeborne.selenide.Selenide.$;

public class MainPageSteps extends BaseSteps<MainPage> {
    public MainPageSteps() {
        page = new MainPage();
    }

    public MainPageSteps openMainPage() {
        page.navigate().shouldBeOpened();
        return this;
    }

    public MainPageSteps inputRequiredField(String firstName, String lastName, String email) {
        page.getFNameField().setValue(firstName);
        page.getLNameField().setValue(lastName);
        page.getEmailField().setValue(email);
        return this;
    }

    public MainPageSteps clickOnSignup() {
        page.getSignupButton().click();
        return this;
    }

    public MainPageSteps checkApiKey() {
        MainPage mainPage = new MainPage();
        mainPage.shouldBeOpened();
        $("code.signup-key").shouldHave(Condition.not(Condition.empty));
        return this;
    }

    public MainPageSteps checkApiKeyHighlighted() {
        page.getHighlightedElement().should(Condition.not(Condition.cssValue("background", "#fff")));
        return this;
    }

    public MainPageSteps checkButtonSignup() {
        SelenideElement btnSignup = page.getSignupButton();
        btnSignup.should(Condition.visible);
        btnSignup.shouldHave(Condition.text("Signup"));
        return this;
    }

    public MainPageSteps checkError() {
        SelenideElement error = page.getTextError();
        error.should(Condition.visible);
        $("ul.parsley-errors-list").should(Condition.cssValue("color", "rgba(255, 0, 0, 1)"));
        error.shouldHave(Condition.text("This value is required"));
        return this;
    }

    public MainPageSteps checkFieldsSignup() {
        SelenideElement fNameField = page.getFNameField();
        SelenideElement lNameField = page.getLNameField();
        SelenideElement emailField = page.getEmailField();
        fNameField.should(Condition.visible);
        lNameField.should(Condition.visible);
        emailField.should(Condition.visible);
        return this;
    }

    public MainPageSteps checkRequiredMark(Matcher<String> mark) {
        List<String> fields = Arrays.asList(
                $("label[for=user_first_name]").innerText(),
                $("label[for=user_last_name]").innerText(),
                $("label[for=user_email]").innerText());

        assertThat(fields, everyItem(mark));
        return this;
    }
}
