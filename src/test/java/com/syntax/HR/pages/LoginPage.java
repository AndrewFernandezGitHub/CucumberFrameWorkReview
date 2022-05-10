package com.syntax.HR.pages;

import com.syntax.HR.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {
    //object repository
    @FindBy(id="txtUsername")
    public WebElement usernameBox;

    @FindBy(id="txtPassword")
    public WebElement passwordBox;

    @FindBy(id="btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;

    @FindBy(xpath = "//span[text()='Username cannot be empty']")
    public WebElement userNameEmptyMsg;

    @FindBy(xpath = "//span[text()='Password cannot be empty']")
    public WebElement passwordEmptyMsg;


    //constructor
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

}
