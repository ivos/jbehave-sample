package com.github.ivos.jbehave.other;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

public class OtherSteps extends Steps {

    StringBuilder sb;

    @BeforeScenario
    public void setup() {
        sb = new StringBuilder();
    }

    @Given("text $text")
    public void givenText(@Named("text") String text) {
        sb.append(text);
    }

    @When("I append $text")
    public void appendText(@Named("text") String text) {
        sb.append(text);
    }

    @Then("result should be $result")
    public void result(@Named("result") String result) {
        Assert.assertEquals(result, sb.toString());
    }

}
