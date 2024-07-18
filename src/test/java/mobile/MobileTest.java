package mobile;

import org.testng.annotations.Test;

public class MobileTest extends TestFlowMobile {

    public MobileTest() {}

    public MobileTest(String mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }
    @Test
    public void androidTest() {
        testYummlyApp();
    }

}
