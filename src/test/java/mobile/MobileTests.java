package mobile;

import org.testng.annotations.Test;

public class MobileTests extends MobileTestFlow {
    public MobileTests() {}
    public MobileTests(String mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }
    @Test
    public void androidTest() {
        testYummlyApp();
    }

}
