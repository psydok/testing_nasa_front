package ru.nasa.front.tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.nasa.front.BaseTest;
import ru.nasa.front.pages.MainPage;
import ru.nasa.front.steps.MainPageSteps;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Test
@Report
public class GenerateKeyTest extends BaseTest {

    @Test
    public void getTextIcon() {
        MainPage page = new MainPage().navigate().shouldBeOpened();
        page.getInfoPic().click();
        page.getTextIconElement().shouldHave(not(cssValue("display", "none")));
        page.getApplicationUrl().shouldBe(focused);
    }

    @DataProvider(name = "newUser")
    public Object[][] createNewUser() {
        Random random = new Random();
        return new Object[][]{
                {
                        "Ivan" + random.nextInt(999),
                        "Ivanov" + random.nextInt(999),
                        "inewtesting" + random.nextInt(999) + "@mail.ru"
                },
        };
    }

    @DataProvider(name = "user")
    public Object[][] createUser() {
        Random random = new Random();
        int digit = random.nextInt(999);
        return new Object[][]{
                {
                        "Ivan" + digit,
                        "Ivanov" + digit,
                        "inotsametesting" + digit + "@mail.ru"
                },
        };
    }

    @Test(dataProvider = "newUser")
    public void getApiKey(String firstName, String lastName, String email) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark()
                .checkButtonSignup();

        mainPageSteps.inputRequiredField(firstName, lastName, email)
                .clickOnSignup()
                .shouldBeExistCode()
                .checkExistApiKey();
        mainPageSteps
                .checkApiKeyHighlighted();
    }

    private String getCode(String firstName, String lastName, String email) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage();

        mainPageSteps.inputRequiredField(firstName, lastName, email)
                .clickOnSignup()
                .shouldBeExistCode();
        SelenideElement apiKey = mainPageSteps.getPage().getCodeApiKey();
        return apiKey.text();
    }

    @Test(dataProvider = "user")
    public void getNotSameApiKey(String firstName, String lastName, String email) {
        String apiKey = getCode(firstName, lastName, email);
        String newApiKey = getCode(firstName, lastName, email);
        assertThat(apiKey, not(newApiKey));
    }

    @Test
    public void getApiKeyWithEmptyFields() {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark()
                .checkButtonSignup();

        mainPageSteps.clickOnSignup()
                .checkError();
        mainPageSteps.getPage().getFNameField().shouldBe(focused);
    }

    @Test(dataProvider = "user")
    public void getApiKeyWithEmptyField(String firstName, String lastName, String email) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkFieldsSignup()
                .checkRequiredMark()
                .checkButtonSignup();

        mainPageSteps.inputRequiredField(firstName, "", email)
                .clickOnSignup()
                .checkError();

        mainPageSteps.getPage()
                .getLNameField()
                .shouldBe(focused);
    }
}
