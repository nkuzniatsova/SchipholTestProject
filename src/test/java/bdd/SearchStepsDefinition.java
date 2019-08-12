package bdd;

import appmanager.ApplicationManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

/**
 * Created by User on 06/08/2019.
 */
public class SearchStepsDefinition {
    private ApplicationManager app;


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

    @Given("^Google page 'https://google.nl'$")
    public void goToGooglePage() {
        app.helper.goTo("https://google.nl");
    }

    @When("^I search for 'Royal Schiphol Group'$")
    public void searchForSchiphol() {
        app.helper.type(By.name("q"),"Royal Schiphol Group");
        app.helper.click(By.name("btnK"));
    }

    @Then("^Open the first link and verify I am on the RSG website 'https://www.schiphol.nl/nl/schiphol-group/'$")
    public void verifyFirstUrl() {
        if(app.helper.isElementPresent(By.xpath("//div[@class=\"r\"]/a/h3"))) {
            app.helper.click(By.xpath("//div[@class=\"r\"]/a/h3"));
            Assert.assertEquals("https://www.schiphol.nl/nl/schiphol-group/", app.helper.getCurPageURL());
        }
    }

}
