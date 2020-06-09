package ru.nasa.front.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.nasa.front.components.ToolbarItemMainPage;
import ru.nasa.front.pages.MainPage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.Matchers.startsWith;

public class MainPageSteps extends BaseSteps<MainPage> {
    public MainPageSteps() {
        page = new MainPage();
    }

    public MainPageSteps openMainPage() {
        page.navigate().shouldBeOpened();
        return this;
    }

    public MainPageSteps shouldBeScrolled() {
        page.waitPageScrolled();
        return this;
    }

    public MainPageSteps shouldBeExistCode() {
        page.waitPageLoadingSignup();
        return this;
    }

    public MainPageSteps inputRequiredField(String firstName, String lastName, String email) {
        page.getFNameField().setValue(firstName);
        page.getLNameField().setValue(lastName);
        page.getEmailField().setValue(email);
        return this;
    }

    public MainPageSteps clickOnSignup() {
        page.getSignupButton()
                .click();
        return this;
    }

    /**
     * Проверка, что ключ появился
     *
     * @return
     */
    public MainPageSteps checkExistApiKey() {
        page.getCodeApiKey().shouldBe(Condition.not(Condition.empty));
        return this;
    }

    /**
     * Проверка, что полученный ключ подсвечивается
     *
     * @return
     */
    public MainPageSteps checkApiKeyHighlighted() {
        page.getHighlightedElement()
                .shouldBe(Condition.not(Condition.cssValue("background", "#fff")));
        return this;
    }

    /**
     * Видимость кнопки отправки формы
     *
     * @return
     */
    public MainPageSteps checkButtonSignup() {
        SelenideElement btnSignup = page.getSignupButton();
        btnSignup.shouldBe(Condition.visible);
        btnSignup.isEnabled();
        btnSignup.shouldHave(Condition.text("Signup"));
        return this;
    }

    /**
     * Вывод ошибки при отправке пустых полей
     *
     * @return
     */
    public MainPageSteps checkError() {
        SelenideElement error = page.getTextError();
        error.should(Condition.visible);
        page.getListErrors().shouldHave(Condition.cssValue("color", "rgba(255, 0, 0, 1)"));
        error.shouldHave(Condition.text("This value is required"));
        return this;
    }

    /**
     * Проверка видимости полей для регистрации
     *
     * @return
     */
    public MainPageSteps checkFieldsSignup() {
        page.getFNameField().shouldBe(Condition.visible);
        page.getLNameField().shouldBe(Condition.visible);
        page.getEmailField().shouldBe(Condition.visible);
        return this;
    }

    /**
     * Обязательные поля в регистрации имеют метку
     *
     * @return
     */
    public MainPageSteps checkRequiredMark() {
        List<String> fields = Arrays.asList(//
                page.getLabelField("user_first_name").innerText(),
                page.getLabelField("user_last_name").innerText(),
                page.getLabelField("user_email").innerText()
        );

        assertThat(fields, everyItem(startsWith("*")));
        return this;
    }

    /**
     * Проверка, что таргет веделения на выбранном элементе в toolbar-е
     *
     * @param item Элемент toolbar-а
     * @return
     */
    public MainPageSteps checkTargetSelectedItem(ToolbarItemMainPage item) {
        SelenideElement selenideElement = item.getItemA();
        selenideElement.shouldHave(Condition.cssClass("currentDiv"));

        return this;
    }

    /**
     * Проверить, что навигация находится правее
     *
     * @return
     */
    public MainPageSteps checkNavigationToRight() {
        $("#headerNav.usa-nav")
                .shouldBe(Condition.cssValue("float", "right"));
        return this;
    }
}
