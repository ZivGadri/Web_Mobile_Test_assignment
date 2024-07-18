package web;

import org.testng.annotations.Test;

public class WebTestsWeb extends WebTestFlow {

    public WebTestsWeb() {}

    public WebTestsWeb(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Test()
    public void testWeb() {
        orkalaAsaData();
    }
}