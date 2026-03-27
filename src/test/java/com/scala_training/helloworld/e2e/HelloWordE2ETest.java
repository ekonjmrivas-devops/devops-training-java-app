package com.scala_training.helloworld.e2e;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldE2ETest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");       // Sin interfaz gráfica
        options.addArguments("--no-sandbox");     // Necesario en Linux/CI
        options.addArguments("--disable-dev-shm-usage"); // Necesario en CI
        driver = new ChromeDriver(options);
    }

    @Test
    public void shouldDisplayHelloWorld() {
        driver.get("http://localhost:" + port + "/hello");
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
