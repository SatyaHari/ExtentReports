package org.cts.hybrid.stepdefinitions;

import org.cts.hybrid.listeners.Reporter;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

public class MyStepdefs {

    @Before
    public void beforeScenario(Scenario scenario) {
        if (scenario.getName().equals("My First Scenario")) {
            Reporter.assignAuthor("Cognizant");
        }
    }

    @Given("I have (\\d+) cukes in my belly") public void I_have_cukes_in_my_belly(int cukes)
        throws IOException {
        Reporter.addStepLog("My test addStepLog message");
        Reporter.addScenarioLog("This is scenario log");
    }

    @Given("I have (\\d+) cukes in my bellies") public void I_have_cukes_in_my_bellies(int cukes) {
        System.out.format("Cukes: %n\n", cukes);
    }

    @Then("^I print$") public void iPrint() throws Throwable {

    }

    @When("^I login with credentials$") public void iLoginWithCredentials(DataTable table) {
    }
}
