package web.manager.models;

public enum BrowserTypes {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private final String browserType;

    BrowserTypes(String browserType) {
        this.browserType = browserType;
    }

    public String getBrowserType() {
        return this.browserType;
    }
}

