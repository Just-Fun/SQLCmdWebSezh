package ua.com.juja.sqlcmd.service;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by max on 07.12.2015.
 * need to run server on http://localhost:8080/sqlcmd
 */

public class WebScrapperTest {

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        baseUrl = "http://localhost:8080/sqlcmd";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testConnectPage() throws Exception {
        driver.get(baseUrl + "/connect");
        setString("dbname", "sqlcmd");
        setString("username", "postgres");
        setString("password", "postgres");
        click("connect");

        // then
        assertLinks("[help, menu, connect, tables]");
    }

    private void click(String connect) {
        driver.findElement(By.id(connect)).click();
    }

    private void setString(String id, String string) {
        driver.findElement(By.id(id)).sendKeys(string);
    }

    @Test
    public void testLoginPage() throws Exception {
        driver.get(baseUrl + "/connect");
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        List<String> names = new LinkedList<>();

        for (WebElement element : elements) {
            names.add(element.getAttribute("id"));
        }
        assertEquals("[dbname, username, password, connect]", names.toString());
    }

    @Test
    public void testMenuPage() throws Exception {
        driver.get(baseUrl + "/menu");
        assertLinks("[help, menu, connect, tables]");
    }

    private void assertLinks(String expectedNames) {
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        List<String> names = new LinkedList<>();
        List<String> urls = new LinkedList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
            urls.add(element.getAttribute("href"));
        }
        assertEquals(expectedNames, names.toString());
    }
}