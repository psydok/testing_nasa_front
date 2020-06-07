package ru.nasa.front.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.open;

public abstract class AbstractPage<T extends AbstractPage<T>>  {
    protected String url;

    public AbstractPage() {
    }

    public AbstractPage(String url) {
        this.url = url;
    }

    private String cleanseUri(String uri) {
        String modifiedUri = uri.split("[?]")[0];
        if ("/".equals(modifiedUri.substring(modifiedUri.length() - 1)))
            return modifiedUri.substring(0, modifiedUri.length() - 1);
        return modifiedUri;
    }

    public T shouldBeOpened() {
        waitPageLoaded();
        Assert.assertEquals(cleanseUri(WebDriverRunner.url()), cleanseUri(url));
        return (T) this;
    }

    public T navigate(Class<? extends T> pageClass) {
        return open(url, pageClass).waitPageLoaded();
    }

    public String getUrl() { return url; }

    public abstract T waitPageLoaded();
}
