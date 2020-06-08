package ru.nasa.front.constants;

public class Constants {
    public static final String CONFIG_SCREEN = "screen";
    public static final String CONFIG_BROWSER = "browser";

    public static final String BROWSER_CHROME = "chrome";

    public static final String SCREEN_DESKTOP = "1200x900";

    public static final int PAGE_LOAD_TIME = 30000;

    private Constants() {
        throw new IllegalAccessError("Class can't be instanced");
    }
}
