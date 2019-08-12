package bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Created by User on 06/08/2019.
 */

@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "html:build/cucumber-report"})
public class SearchSteps extends AbstractTestNGCucumberTests {
}
