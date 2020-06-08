package ru.nasa.front.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.nasa.front.BaseTest;
import ru.nasa.front.components.ToolbarItemMainPage;
import ru.nasa.front.steps.MainPageSteps;

import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ToolbarTest extends BaseTest {
    @DataProvider(name = "toolbar")
    public Object[][] createListToolbar() {
        return new Object[][]{
                //элемент toolbar, заголовок, ссылка
                {"Browse APIs", "Browse APIs", "#browseAPI"},
                {"Authentication", "Authentication", "#authentication"},
                {"Generate API Key", "Generate API Key", "#signUp"},
                {"Overview", "{ NASA APIs }", "#main-content"},
                {"Recover API Key", "API Key Recovery", "#recovery"},
        };
    }

    @Test(dataProvider = "toolbar")
    public void movingToolbar(String itemToolbar, String title, String href) {
        MainPageSteps mainPageSteps = new MainPageSteps();
        mainPageSteps.openMainPage()
                .checkNavigationToRight();

        ToolbarItemMainPage item = mainPageSteps.getPage()
                .findLinkToolbar(itemToolbar)
                .select();
        mainPageSteps.checkHighlightedSelectedItem(item);

        int yTitle = mainPageSteps.getPage().findPointH2(title).y;
        sleep(2000);
        int yItem = item.getLocationItem().y;
        Integer abs = Math.abs(yItem - yTitle);
        //расстояние между итемом с тулбара и заголовком не отличается больше чем на 100
        assertThat(abs.compareTo(100), is(-1));
    }
}
