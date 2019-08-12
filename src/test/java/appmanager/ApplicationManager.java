package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private String browser;
    public AppHelper helper;
    WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "C:\\Tools\\SeleniumDrivers\\geckodriver-v0.16.0-win64\\geckodriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            wd = new FirefoxDriver(capabilities);
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            wd = new InternetExplorerDriver(capabilities);
        } else if (Objects.equals(browser, BrowserType.SAFARI)) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            wd = new SafariDriver(capabilities);
        }

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl", "http://google.nl"));
        helper = new AppHelper(wd);
    }

    public void goTo(String url) {
        wd.get(url);
    }
    public void stop() {
        wd.quit();
    }

}
