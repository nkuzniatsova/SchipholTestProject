package wikiMenusItems;

import appmanager.ApplicationManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class wikiMenuItemsTest {

    private ApplicationManager app;
    private List<WebElement> toolsMenuItems = Collections.unmodifiableList(new ArrayList<WebElement>() {
        @Override
        public WebElement get(int index) {
            return null;
        }

        @Override
        public int size() {
            int nrElements = 0;
            for (WebElement element : toolsMenuItems) {
                nrElements = nrElements++;
            }
            return nrElements;
        }
    });

    @Before
    public void init() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }

    @Test
    void getWikiMenuItemsTest() {
        app.helper.goTo( "https://en.wikipedia.org/wiki/Main_Page");
        toolsMenuItems = app.helper.getElementsByLocator(By.cssSelector("id=t-."));
        Assert.assertEquals(7,toolsMenuItems.size());
    }
}
