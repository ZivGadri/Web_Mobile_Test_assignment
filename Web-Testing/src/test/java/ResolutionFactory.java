import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Factory;

import java.awt.*;

public class ResolutionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ResolutionFactory.class);

    @Factory
    public Object[] createScreenResolutions() {
        LOGGER.info("Instantiating Test Factory object");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) screenSize.getWidth();
        int maxHeight = (int) screenSize.getHeight();

        Object[] result = new Object[] {
                new WebTest(maxWidth, maxHeight),
                new WebTest(1024, 768),
                new WebTest(800, 600)
        };

        return result;
    }
}