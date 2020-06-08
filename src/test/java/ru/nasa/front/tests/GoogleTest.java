package ru.nasa.front.tests;

import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.nasa.front.BaseTest;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Test
@Report
public class GoogleTest extends BaseTest {
    @Test
    public void searchNasaApiInGoogle() {
        open("https://google.com/ncr");
        $(By.name("q")).val("nasa api").pressEnter();
        $$("#search .r").shouldHave(sizeGreaterThan(1));
        $("#search .r").shouldHave(text("NASA"));
    }

}
