package helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions extends TestBase{
    public static WebDriverWait wait = new WebDriverWait(driver, 15);

    public static void seleniumClick(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public static void seleniumTypeText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public static String seleniumGetText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }


}
