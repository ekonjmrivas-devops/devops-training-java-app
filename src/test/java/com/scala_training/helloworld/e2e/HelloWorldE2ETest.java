package com.scala_training.helloworld.e2e;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, 
                properties = "server.port=8085")
public class HelloWorldE2ETest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        String seleniumHost = System.getProperty("selenium.host", "selenium");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new RemoteWebDriver(
            new URL("http://" + seleniumHost + ":4444"),
            options
        );
    }

    @Test
    public void shouldDisplayHelloWorld() throws Exception {
        String dockerHost = System.getProperty("docker.host", "172.17.0.1");
        String baseUrl = "http://" + dockerHost + ":8085/hello";
        driver.get(baseUrl);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertEquals("Hello World!", bodyText);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

