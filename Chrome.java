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
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.navigate().to("http://google.com");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        WebDriver driver = new ChromeDriver(capabilities);
        driver.get("https://www5.javfinder.is/movie/watch/fhd-yellow-hero-hery-102-mr-mizukawa-kazuha-likes-bellows-licking-licking-sekkuu.html");

        List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }
}
