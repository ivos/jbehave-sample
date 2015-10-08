package com.github.ivos.jbehave;

import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

public class SampleSteps extends Steps {

	Sample sample;

	@BeforeStory
	public void setup() {
		sample = new Sample();
	}

	@Given("that there is number $number")
	public void givenNumber(@Named("number") int number) {
		sample.setValue(number);
	}

	@When("I add number $number")
	public void addNumber(@Named("number") int number) {
		sample.addValue(number);
	}

	@Then("result should be $result")
	public void result(@Named("result") int expected) {
		Assert.assertEquals(expected, sample.getValue());
	}

}
