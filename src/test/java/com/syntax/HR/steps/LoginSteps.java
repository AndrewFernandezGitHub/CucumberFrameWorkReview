package com.syntax.HR.steps;

import com.syntax.HR.utils.CommonMethods;
import com.syntax.HR.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void navigate(){
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid admin credentials")
    public void user_enters_valid_admin_credentials() {
       sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
       sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
       click(login.loginBtn);
    }
    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        Assert.assertTrue("Welcome message is not displayed",dash.welcomeMessage.isDisplayed());
    }
    @When("user enters {string} and {string}")
    public void user_enters_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user see {string} on the screen")
    public void user_see_on_the_screen(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
