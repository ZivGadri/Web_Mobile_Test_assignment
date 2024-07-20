import org.testng.annotations.Factory;
import manager.Constants;

public class MobilePlatformFactory {
    @Factory
    public Object[] SetupMobilePlatforms() {

        Object[] result = new Object[] {
                new MobileTest(Constants.ANDROID_PLATFORM)
                //,new MobileTests(Constants.IOS_PLATFORM)  *** Not yet implemented
        };

        return result;
    }
}
