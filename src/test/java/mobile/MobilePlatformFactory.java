package mobile;

import mobile.manager.Constants;
import org.testng.annotations.Factory;

public class MobilePlatformFactory {
    @Factory
    public Object[] SetupMobilePlatforms() {

        Object[] result = new Object[] {
                new MobileTests(Constants.ANDROID_PLATFORM)
                //,new MobileTests(Constants.IOS_PLATFORM)  *** Not yet implemented
        };

        return result;
    }
}
