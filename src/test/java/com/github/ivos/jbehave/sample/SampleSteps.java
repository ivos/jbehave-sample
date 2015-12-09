package com.github.ivos.jbehave.sample;

import com.github.ivos.jbehave.Sample;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

public class SampleSteps extends Steps {

    Sample sample;

    @BeforeScenario
    public void setup() {
        sample = new Sample();
    }

    @Given("a number $number")
    public void givenNumber(@Named("number") int number) {
        sample.setValue(number);
    }

    @When("I add $number")
    public void addNumber(@Named("number") int number) {
        sample.addValue(number);
    }

    @Then("result should be $result")
    public void result(@Named("result") int result) {
        Assert.assertEquals(result, sample.getValue());
    }

}
