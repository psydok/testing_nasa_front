package ru.nasa.front;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.TextReport;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.nasa.front.constants.Constants;

@Listeners({ TextReport.class })
public abstract class BaseTest {
    public BaseTest() {
        Configuration.screenshots = true;
        Configuration.timeout = 10000;

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({ Constants.CONFIG_BROWSER, Constants.CONFIG_SCREEN })
    protected void setBrowser(@Optional(Constants.BROWSER_CHROME) String browser, @Optional(Constants.SCREEN_DESKTOP) String screen) {
        Configuration.browser = browser;
        Configuration.browserSize = screen;
    }
}
