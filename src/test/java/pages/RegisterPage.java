package pages;

import helper.Actions;
import helper.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends TestBase {


    @FindBy(name ="firstname")
    WebElement firstNameTXT;

    @FindBy(name ="lastname")
    WebElement lastNameTXT;

    @FindBy(name ="phone")
    WebElement phoneNumTXT;

    @FindBy(name ="email")
    WebElement emailTXT;

    @FindBy(name ="password")
    WebElement passwordTXT;

    @FindBy(name ="confirmpassword")
    WebElement confirmPasswordTXT;

    @FindBy(xpath ="(//button[@type ='submit'])[1]")
    WebElement submitBTN;

    @FindBy(xpath ="//h3[@class='text-align-left']")
    WebElement welcomeTXT;

    @FindBy(xpath ="(//div[@class='alert alert-danger'])/p")
    WebElement warningTXT;

    @FindBy(xpath ="//div[@class = 'alert alert-danger']")
    WebElement redundantEmailWarningTXT;

    @FindBy(xpath ="(//a[@id='dropdownCurrency'])[2]")
    WebElement accountElement;

    @FindBy(xpath ="(//a[@href='https://www.phptravels.net/account/logout/'])")
    WebElement logoutBTN;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName){

        Actions.seleniumTypeText(firstNameTXT , firstName);
    }

    public void setLastName(String lastName){

        Actions.seleniumTypeText(lastNameTXT , lastName);
    }

    public void setPhoneNum(String phoneNum){

        Actions.seleniumTypeText(phoneNumTXT , phoneNum);
    }

    public void setEmail(String email){

        Actions.seleniumTypeText(emailTXT , email);
    }

    public void setPassword(String password){

        Actions.seleniumTypeText(passwordTXT , password);
    }

    public void setConfirmPassword(String confirmPassword){

        Actions.seleniumTypeText(confirmPasswordTXT , confirmPassword);
    }

    public void submit(){
        Actions.seleniumClick(submitBTN);
    }

    public String getWelcomeText(){
        String welcomeText = Actions.seleniumGetText(welcomeTXT);
        return welcomeText;
    }

    public String getWarningText(){
        String warningText = Actions.seleniumGetText(warningTXT);
        return warningText;
    }

    public String getEmailWarningText(){
        String warningText = Actions.seleniumGetText(redundantEmailWarningTXT);
        return warningText;
    }

    public void logout(){
        getWelcomeText();
        Actions.seleniumClick(accountElement);
        Actions.seleniumClick(logoutBTN);
    }
}
