package com.jacobshao;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chrome {

    public static String driverPath = "lib/";

    public static void main(String []args) {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver");
//        driver = new ChromeDriver();
//        driver.navigate().to("http://google.com");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(capabilities);
        driver.get("http://www.google.com");
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        System.out.println(netData);
    }
}
