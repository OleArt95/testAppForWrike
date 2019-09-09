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
        setTextInEmailField(driver);
        clickOnCreateMyWrikeAccountBtn(driver);
        clickOnRandomAnswerInFirstQuestion(driver);
        clickOnRandomAnswerInSecondQuestion(driver);
        clickOnRandomAnswerInThirdQuestion(driver);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); //Ожидание в 6 секунд для завершения отправки результатов
        clickOnSubmitResultsBtn(driver);
        checkTwitterBtnInFooter(driver);
    }

    private void clickOnGetStartedForFreeBtn(WebDriver driver) {
        WebElement btnGetStatedForFree = driver.findElement(By.xpath("//div/div[2]/div/form/button"));
        btnGetStatedForFree.click();
    }

    private void setTextInEmailField(WebDriver driver) {
        WebElement fieldEmailValue = driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[1]/input"));

        //Генерирование строки для создания произвольного email имени
        String generatedEmailName = RandomStringUtils.randomAlphanumeric(10);
        fieldEmailValue.sendKeys(generatedEmailName + "wpt@wriketask.qaa");
    }

    private void clickOnCreateMyWrikeAccountBtn(WebDriver driver) {
        WebElement btnCreateMyWrikeAccount = driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form/label[2]/button"));
        btnCreateMyWrikeAccount.click();

        //Метод явного ожидания видимости абзаца "Help us provide you the best possible experience:"
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/h3")));

        //Проверка того, что URL сменился с https://wrike.com
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

        //Метод явного ожидания того, что кнопка "Submit Results" невидима. Он нужен для дальнейшего выполнения теста
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(btnSubmitResults));

        //Проверка того, что кнопка "Submit Results" не отображается. Нужна для дальнейшего выполнения теста
        Assert.assertFalse(btnSubmitResults.isDisplayed());
    }

    private void checkTwitterBtnInFooter(WebDriver driver) {
        WebElement btnTwitter = driver.findElement(By.xpath("//div[3]/div/div[1]/div/ul/li[1]"));
        Assert.assertTrue(btnTwitter.isDisplayed()); //Проверка того, что кнопка Twitter в Follow Us отображается

//        String twitterLink = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a")).getText();
//        Assert.assertEquals("https://twitter.com/wrike", twitterLink);
    }
}
