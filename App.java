package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class App {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        String driver_path = "E:/Selenium/driversandfiles/chromedriver_win32/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driver_path);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        driver.get("https://www.google.com/");
//
//        String pageTitle = driver.getTitle();
//        String url = driver.getCurrentUrl();
//        System.out.println("pagetitle: " +pageTitle +"\n" +"page url: " +url);
//
//        driver.findElement(By.name("q")).sendKeys("youtube");
//        driver.findElement(By.className("gNO89b")).click();
//
//         List <WebElement> table = driver.findElements(By.className("jmjoTe"));
//
//         for (WebElement element : table){
//
//             System.out.println("Table: \n  " + element.getText());
//         }

         //explicit wait
//         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//         wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("jmjo"))));

//         //fluent wait
//        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
//                .withTimeout(Duration.ofSeconds(15))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class);
//
//        fluentWait.until(new Function  () {
//            @Override
//            public Object apply(Object o) {
//                return driver.findElement(By.className("jmjo"));
//            }
//        });

        driver.get("https://www.udemy.com/course/sdet-learnit/");

        WebElement signupButton = driver.findElement(By.cssSelector("#udemy > div.ud-main-content-wrapper > div.ud-app-loader.ud-component--header-v6--header.ud-header.ud-app-loaded > div.ud-text-sm.header--header--1SLKV.header--flex-middle--2QeVR > div:nth-child(9) > a > span"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(signupButton));
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
//        javascriptExecutor.executeScript("arguments[0].click() =", signupButton);
        //casting - instance of driver to an object of javascriptexecutor
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", signupButton);

        //Setting-up timeouts
        ((JavascriptExecutor)driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);" );

        //Switching browser-tab
        ((JavascriptExecutor)driver).executeScript("window.location = 'https://www.wikipedia.org/' ");

        driver.findElement(By.id("js-link-box-en")).click();

        //scroll the window
        //((JavascriptExecutor)driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");

        WebElement wikipediaLogo = driver.findElement(By.className("mw-wiki-logo"));

        //Scroll until element is visible
        //((JavascriptExecutor)driver).executeScript("[arguments[0].scrollIntoView();", wikipediaLogo);



        //Convert web driver object to TakeScreenshot
        TakesScreenshot screenshot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File("C:\\Users\\Denny\\IdeaProjects\\LearnSelenium\\src\\main\\java\\org\\example\\screenshot.JPEG");
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        // Get screenshot of the visible part of the web page
        // Convert the screenshot into BufferedImage
        BufferedImage fullScreenshot = ImageIO.read(SrcFile);

        //Find location of the webelement logo on the page
        Point location = wikipediaLogo.getLocation();

        //Find width and height of the located element logo
        int width = wikipediaLogo.getSize().getWidth();
        int height = wikipediaLogo.getSize().getHeight();

        //cropping the full image to get only the logo screenshot
        BufferedImage logoImage = fullScreenshot.getSubimage(location.getX(), location.getY(), width, height);

        ImageIO.write(logoImage, "png", SrcFile);
        FileUtils.copyFile(SrcFile, DestFile);
        

        Thread.sleep(2000);


        driver.close();



    }
}
