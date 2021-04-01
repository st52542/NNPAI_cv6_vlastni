package ui

import cz.vlastni.eshop.EshopApplication
import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.entity.Doprava
import cz.vlastni.eshop.entity.Platba
import cz.vlastni.eshop.repository.DopravaRepository
import cz.vlastni.eshop.repository.PlatbaRepository
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
class PlatbaGroovyTests {
    private WebDriver driver;

    @Autowired
    PlatbaRepository platbaRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromeDriverPath = PlatbaGroovyTests.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        platbaRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userGetToPlatbaList() {
        driver.get("http://localhost:8080");
        driver.findElement(By.xpath("//a[@href='/platba']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam plateb']")).size());
    }

    @Test
    public void userGetToAddNewPlatba() {
        driver.get("http://localhost:8080/platba");
        driver.findElement(By.xpath("//a[@href='/platba-reg-form']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Registrace platby']")).size());
    }

    @Test
    public void userSaveNewPlatba() {
        driver.get("http://localhost:8080/platba-reg-form");
        driver.findElement(By.name("popis")).sendKeys("platba1");
        driver.findElement(By.name("prevod")).sendKeys("55");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam plateb']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba1']")).size());
    }

    @Test
    public void checkMorePlatba() {
        creator.saveEntities(
                new Platba(popis: "platba1"),
                new Platba(popis: "platba2"),
                new Platba(popis: "platba3"),
                new Platba(popis: "platba4")
        )
        driver.get("http://localhost:8080/platba");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam plateb']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba4']")).size());
    }
    @Test
    public void userCheckValidDetails() {
        creator.saveEntities(
                new Platba(popis: "platba1", prevod: 55),
                new Platba(popis: "platba2", prevod: 5.5),
                new Platba(popis: "platba3", prevod: 1.2),
                new Platba(popis: "platba4", prevod: 100)
        )
        driver.get("http://localhost:8080/platba");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam plateb']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platba4']")).size());
        driver.findElement(By.xpath("//h4[text()='platba2']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail platby']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='platba2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='5.5']")).size());
    }
    @Test
    public void userEditDetails() {
        creator.save(new Platba(popis: "platba1", prevod: 55));
        driver.get("http://localhost:8080/platba-reg-form/1");
        driver.findElement(By.name("popis")).clear();
        driver.findElement(By.name("popis")).sendKeys("platbaZmena");
        driver.findElement(By.name("prevod")).clear();
        driver.findElement(By.name("prevod")).sendKeys("100");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam plateb']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='platbaZmena']")).size());
        driver.findElement(By.xpath("//h4[text()='platbaZmena']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail platby']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='platbaZmena']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='100.0']")).size());
    }
}
