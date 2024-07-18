package web;

import org.testng.annotations.Factory;

import java.awt.*;

public class ResolutionFactory {

    @Factory
    public Object[] createScreenResolutions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) screenSize.getWidth();
        int maxHeight = (int) screenSize.getHeight();

        Object[] result = new Object[] {
                new WebTestsWeb(maxWidth, maxHeight),
                new WebTestsWeb(1024, 768),
                new WebTestsWeb(800, 600)
        };

        return result;
    }
}
