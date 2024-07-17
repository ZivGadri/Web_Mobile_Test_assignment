package web;

import org.testng.annotations.Test;

public class WebTests extends TestFlows {

    public WebTests() {}

    public WebTests(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Test()
    public void testWeb() {
        orkalaAsaData();
    }
}
