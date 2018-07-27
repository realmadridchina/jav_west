import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class javScraper {

    public static String driverPath = "lib/";

    public static void main(String []args) {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver");
//        driver = new ChromeDriver();
//        driver.navigate().to("http://google.com");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        WebDriver driver = new ChromeDriver(capabilities);
        driver.get("https://www.google.com");

        List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
        System.out.println("-------------------------------------------------------------------");
        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        final String messageFirst = entries.get(0).getMessage();
        System.out.println(messageFirst);
        JSONObject json = new JSONObject(messageFirst);
        System.out.println(json.get("webview"));
    }
}
