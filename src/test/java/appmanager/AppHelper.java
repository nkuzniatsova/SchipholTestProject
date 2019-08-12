package appmanager;

import org.openqa.selenium.*;

import java.io.File;
import java.util.List;

public class AppHelper {
    protected WebDriver wd;

    public AppHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goTo(String url) {
        wd.get(url);
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public List<WebElement> getElementsByLocator(By locator) {
        return wd.findElements(locator);
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public String getCurPageURL() {
        return wd.getCurrentUrl();
    }
}
