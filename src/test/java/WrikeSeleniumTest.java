import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class WrikeSeleniumTest {

    @Test
    public void wrikeSeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "./selenium/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.wrike.com/");

        clickOnGetStartedForFreeBtn(driver);
        typeEmailValueInEmailField(driver);
        clickOnCreateMyWrikeAccountBtn(driver);
        clickOnRandomAnswerInFirstQuestion(driver);
        clickOnRandomAnswerInSecondQuestion(driver);
        clickOnRandomAnswerInThirdQuestion(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickOnSubmitResultsBtn(driver);
        checkTwitterBtnInFooter(driver);
    }

    private void clickOnGetStartedForFreeBtn(WebDriver driver) {
        WebElement btnGetStatedForFree = driver.findElement(By.xpath("//div/div[2]/div/form/button"));
        btnGetStatedForFree.click();
    }

    private void typeEmailValueInEmailField(WebDriver driver) {
        WebElement fieldEmailValue = driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[1]/input"));

        String generatedEmailName = RandomStringUtils.randomAlphanumeric(10);
        fieldEmailValue.sendKeys(generatedEmailName + "wpt@wriketask.qaa");
    }

    private void clickOnCreateMyWrikeAccountBtn(WebDriver driver) {
        WebElement btnCreateMyWrikeAccount = driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[2]/button"));
        btnCreateMyWrikeAccount.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/h3")));

        Assert.assertNotEquals("https://www.wrike.com/", driver.getCurrentUrl());
    }

    private void clickOnRandomAnswerInFirstQuestion(WebDriver driver) {
        List<WebElement> list = driver.findElements(By.xpath("//form/div[1]/label"));
        Random r = new Random();
        int randomValue = r.nextInt(list.size());
        list.get(randomValue).click();
    }

    private void clickOnRandomAnswerInSecondQuestion(WebDriver driver) {
        List<WebElement> list = driver.findElements(By.xpath("//form/div[2]/label"));
        Random r = new Random();
        int randomValue = r.nextInt(list.size());
        list.get(randomValue).click();
    }

    private void clickOnRandomAnswerInThirdQuestion(WebDriver driver) {
        List<WebElement> list = driver.findElements(By.xpath("//form/div[3]/label"));
        Random r = new Random();
        int randomValue = r.nextInt(list.size());
        list.get(randomValue).click();
    }

    private void clickOnSubmitResultsBtn(WebDriver driver) {
        WebElement btnSubmitResults = driver.findElement(By.xpath("//div[2]/div/div[2]/div/form/button"));
        btnSubmitResults.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(btnSubmitResults));

        Assert.assertFalse(btnSubmitResults.isDisplayed());
    }

    private void checkTwitterBtnInFooter(WebDriver driver) {
        WebElement btnTwitter = driver.findElement(By.xpath("//div[3]/div/div[1]/div/ul/li[1]"));
        btnTwitter.isDisplayed();

//        String twitterLink = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a")).getText();
//        Assert.assertTrue(twitterLink.contains("https://twitter.com/wrike"));

//        WebElement twitterIcon = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a/svg"));
//        Assert.assertTrue(twitterIcon.isDisplayed());
    }
}
