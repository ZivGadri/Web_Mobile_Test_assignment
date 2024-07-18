package web;

import org.testng.annotations.Test;

public class WebTest extends TestFlowWeb {

    public WebTest() {}

    public WebTest(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Test()
    public void testWeb() {
        orkalaAsaData();
    }
}