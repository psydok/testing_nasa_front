package ru.nasa.front.tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.annotations.Report;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.nasa.front.BaseTest;
import ru.nasa.front.pages.MainPage;
import ru.nasa.front.steps.MainPageSteps;

import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.startsWith;

@Test
@Report
public class GenerateKeyTest extends BaseTest {

    @Test
    public void dataPage() {
        MainPage page = new MainPage().navigate().shouldBeOpened();
        page.getInfoPic().click();
        page.getTextIconElement().shouldHave(cssValue("display", "block"));
        page.getApplicationUrl().should(focused);
    }

    @DataProvider(name = "user")
    public Object[][] createUser() {
        return new Object[][]{
                {"Ivan", "Ivanov", "ivanivanov@mail.ru", startsWith("*")},
                {"Ivan1", "Ivanov2", "ivanivanov3@mail.ru", startsWith("*")},
        };
    }

    @Test(dataProvider = "user")
    public void getApiKey(String firstName, String lastName, String email, Matcher<String> mark) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark(mark)
                .checkButtonSignup();

        mainPageSteps.inputRequiredField(firstName, lastName, email)
                .clickOnSignup()
                .checkApiKey();
        mainPageSteps.checkApiKeyHighlighted();

    }

    private SelenideElement getCode(String firstName, String lastName, String email) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage();

        mainPageSteps.inputRequiredField(firstName, lastName, email)
                .clickOnSignup();

        return mainPageSteps.getPage().getCodeApiKey();
    }

    @Test(dataProvider = "user")
    public void getSameApiKey(String firstName, String lastName, String email) {
        SelenideElement apiKey = getCode(firstName, lastName, email);
        SelenideElement newApiKey = getCode(firstName, lastName, email);

        Assert.assertEquals(apiKey, newApiKey);
    }

    @Test
    public void getApiKeyWithEmptyFields() {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark(startsWith("*"))
                .checkButtonSignup();

        mainPageSteps.clickOnSignup()
                .checkError();
        mainPageSteps.getPage().getFNameField().should(focused);
    }

    @Test(dataProvider = "user")
    public void getApiKeyWithEmptyField(String firstName, String lastName, String email, Matcher<String> mark)  {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark(mark)
                .checkButtonSignup();

        mainPageSteps.inputRequiredField(firstName, "", email)
                .clickOnSignup()
                .checkError();

        mainPageSteps.getPage().getLNameField().should(focused);
    }

    @Test
    public void movingToolbar() {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage();

        mainPageSteps.getPage().getLNameField().should(focused);
    }
}
