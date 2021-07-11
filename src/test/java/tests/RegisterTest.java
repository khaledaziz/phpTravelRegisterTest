package tests;

import helper.ExcelRead;
import helper.General;
import helper.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterPage;

import java.io.IOException;

public class RegisterTest extends TestBase {
    RegisterPage RegisterPageObj;
    General generalFun = new General();

    @BeforeClass
    public void initObj(){
        RegisterPageObj = new RegisterPage(driver);
    }

    @BeforeMethod
    public void openUrl(){
        driver.get(baseUrl + "register");
    }

    @Test(priority = 0, dataProvider = "successfulSignup")
    public void signupTest(String firstName, String lastName, String phone, String password, String confirmedPassword){
        logger = extent.createTest("Test signup with valid data");
        RegisterPageObj.setFirstName(firstName);
        RegisterPageObj.setLastName(lastName);
        RegisterPageObj.setPhoneNum(phone);
        RegisterPageObj.setEmail(generalFun.fakerEmail);
        RegisterPageObj.setPassword(password);
        RegisterPageObj.setConfirmPassword(confirmedPassword);
        RegisterPageObj.submit();
        String actualText = RegisterPageObj.getWelcomeText();
        Assert.assertTrue(actualText.contains("Hi"));
        RegisterPageObj.logout();
    }

    @DataProvider (name = "successfulSignup" )
    public Object[][] successSignupData() throws IOException, IOException {
        String path = System.getProperty("user.dir") + "/data/RegisterTest.xlsx";
        String mySheet = "Signup";
        ExcelRead testData = new ExcelRead();
        String data[][] = testData.retrieveMyData(path, mySheet);
        return data;
    }

    @Test(priority = 1, dataProvider = "passwordConfirmationTestData")
    public void passwordConfirmation(String firstName, String lastName, String phone, String password, String confirmedPassword){
        logger = extent.createTest("Verify user can't signup without entering identical passwords");
        RegisterPageObj.setFirstName(firstName);
        RegisterPageObj.setLastName(lastName);
        RegisterPageObj.setPhoneNum(phone);
        RegisterPageObj.setEmail(generalFun.fakerEmail);
        RegisterPageObj.setPassword(password);
        RegisterPageObj.setConfirmPassword(confirmedPassword);
        RegisterPageObj.submit();
        String actualText = RegisterPageObj.getWarningText();
        String expectedText = "Password not matching with confirm password.";
        Assert.assertEquals(actualText, expectedText);
    }

    @DataProvider (name = "passwordConfirmationTestData" )
    public Object[][] passwordConfirmationData() throws IOException, IOException {
        String path = System.getProperty("user.dir") + "/data/RegisterTest.xlsx";
        String mySheet = "PasswordConfirmation";
        ExcelRead testData = new ExcelRead();
        String data[][] = testData.retrieveMyData(path, mySheet);
        return data;
    }

    @Test(priority = 2, dataProvider = "successfulSignup")
    public void emailValidation(String firstName, String lastName, String phone, String password, String confirmedPassword){
        logger = extent.createTest("Verify user can't signup without entering a valid email");
        RegisterPageObj = new RegisterPage(driver);
        driver.get("https://www.phptravels.net/register");
        RegisterPageObj.setFirstName(firstName);
        RegisterPageObj.setLastName(lastName);
        RegisterPageObj.setPhoneNum(phone);
        RegisterPageObj.setEmail("test.user");
        RegisterPageObj.setPassword(password);
        RegisterPageObj.setConfirmPassword(confirmedPassword);
        RegisterPageObj.submit();
        String actualText = RegisterPageObj.getWarningText();
        String expectedText = "The Email field must contain a valid email address.";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(priority = 3, dataProvider = "successfulSignup")
    public void sameEmailValidation(String firstName, String lastName, String phone, String password, String confirmedPassword) throws InterruptedException {
        logger = extent.createTest("Verify user can't signup with email used before");
        RegisterPageObj.setFirstName(firstName);
        RegisterPageObj.setLastName(lastName);
        RegisterPageObj.setPhoneNum(phone);
        RegisterPageObj.setEmail(generalFun.fakerEmail);
        RegisterPageObj.setPassword(password);
        RegisterPageObj.setConfirmPassword(confirmedPassword);
        RegisterPageObj.submit();
        RegisterPageObj.logout();
        openUrl();
        RegisterPageObj.setFirstName(firstName);
        RegisterPageObj.setLastName(lastName);
        RegisterPageObj.setPhoneNum(phone);
        RegisterPageObj.setEmail(generalFun.usedEmail);
        RegisterPageObj.setPassword(password);
        RegisterPageObj.setConfirmPassword(confirmedPassword);
        RegisterPageObj.submit();
        String actualText = RegisterPageObj.getEmailWarningText();
        String expectedText = "Email Already Exists.";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(priority = 4)
    public void phoneNumValidation() throws InterruptedException {
        logger = extent.createTest("Verify user can't signup with invalid phone number");
        RegisterPageObj = new RegisterPage(driver);
        RegisterPageObj.setFirstName("Test");
        RegisterPageObj.setLastName("Account");
        RegisterPageObj.setPhoneNum("testPhone");
        RegisterPageObj.setEmail(generalFun.fakerEmail);
        RegisterPageObj.setPassword("P@ssw0rd");
        RegisterPageObj.setConfirmPassword("P@ssw0rd");
        RegisterPageObj.submit();
        Thread.sleep(3000);
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.phptravels.net/register";
        Assert.assertTrue(actualUrl.equals(expectedUrl));
    }




}
