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
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Test
@Report
public class GenerateKeyTest extends BaseTest {

    @Test
    public void getTextIcon() {
        MainPage page = new MainPage().navigate().shouldBeOpened();
        page.getInfoPic().click();
        //page.getTextIconElement().shouldHave(cssValue("display", "block"));
        page.getTextIconElement().isDisplayed();
        page.getApplicationUrl().shouldBe(focused);
    }

    @DataProvider(name = "user")
    public Object[][] createUser() {
        return new Object[][]{
                {"Ivan1", "Ivanov2", "ivanivanov@mail.ru", startsWith("*")},
                //  {"Ivan1", "Ivanov2", "ivanivanov3@mail.ru", startsWith("*")},
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
                .checkExistApiKey();
        mainPageSteps.checkApiKeyHighlighted();
    }

    private String getCode(String firstName, String lastName, String email) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage();

        mainPageSteps.inputRequiredField(firstName, lastName, email)
                .clickOnSignup();
        sleep(5000);
        SelenideElement apiKey = mainPageSteps.getPage().getCodeApiKey();
        sleep(15000);
        return apiKey.text();
    }

    @Test(dataProvider = "user")
    public void getSameApiKey(String firstName, String lastName, String email, Matcher<String> mark) {
        String apiKey = getCode(firstName, lastName, email);
        sleep(5000);
        String newApiKey = getCode(firstName, lastName, email);
        sleep(5000);

        assertThat(apiKey, not(newApiKey));
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
        mainPageSteps.getPage().getFNameField().shouldBe(focused);
    }

    @Test(dataProvider = "user")
    public void getApiKeyWithEmptyField(String firstName, String lastName, String email, Matcher<String> mark) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark(mark)
                .checkButtonSignup();

        mainPageSteps.inputRequiredField(firstName, "", email)
                .clickOnSignup()
                .checkError();

        mainPageSteps.getPage()
                .getLNameField()
                .shouldBe(focused);
    }
}
