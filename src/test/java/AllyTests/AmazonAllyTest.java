package AllyTests;


import com.deque.axe.AXE;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AmazonAllyTest {
    WebDriver driver;
    private static final URL scriptUrl = AmazonAllyTest.class.getResource("/axe.min.js");

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    // @Test
    public void amazonAllyTest() {
        driver.get("https://www.amazon.com");
        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");
        if (violations.length() == 0) {
            System.out.println("========NO VIOLATIONS=========");
        } else {
            AXE.writeResults("amazonAllyTest", responseJson);
            Assert.assertTrue(false, AXE.report(violations));

        }
    }

    //  @Test
    public void eaSportsAllyTest() {

        driver.get("https://www.ea.com/sports");
        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");
        if (violations.length() == 0) {
            System.out.println("========NO VIOLATIONS=========");
        } else {
            AXE.writeResults("eaSportsAllyTest", responseJson);
            Assert.assertTrue(false, AXE.report(violations));

        }
    }

    @Test
    public void visionAcademyCharterAllyTest() {
        driver.get("https://www.vacharter.org/");
        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");
        if (violations.length() == 0) {
            System.out.println("========NO VIOLATIONS=========");
        } else {
            AXE.writeResults("vaCharterAllyTest", responseJson);
            Assert.assertTrue(false, AXE.report(violations));
        }
    }

    @Test
    public void testAccesibilityForShadowDomElement() {
        driver.get("https://www.vacharter.org/");
        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");

        JSONArray nodes=((JSONObject)violations.get(0)).getJSONArray("nodes");
        JSONArray target=((JSONObject)nodes.get(0)).getJSONArray("target");
        if(violations.length()==1){
       //     Assert.assertTrue(AXE.report(violations),true);
        Assert.assertEquals(String.valueOf(target),"location of node");
        }
        else {
            AXE.writeResults("testAccesibilityForShadowDomElement",responseJson);
            Assert.assertTrue(false,AXE.report(violations));
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
