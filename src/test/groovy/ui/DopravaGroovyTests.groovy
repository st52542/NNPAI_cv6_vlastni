package ui

import cz.vlastni.eshop.EshopApplication
import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.entity.Doprava
import cz.vlastni.eshop.repository.DopravaRepository
import org.junit.jupiter.api.*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
class DopravaGroovyTests {
    private WebDriver driver;

    @Autowired
    DopravaRepository dopravaRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromeDriverPath = DopravaGroovyTests.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        dopravaRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userGetToDopravaList() {
        driver.get("http://localhost:8080");
        driver.findElement(By.xpath("//a[@href='/doprava']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam doprav']")).size());
    }

    @Test
    public void userGetToAddNewDoprava() {
        driver.get("http://localhost:8080/doprava");
        driver.findElement(By.xpath("//a[@href='/doprava-reg-form']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Registrace dopravy']")).size());
    }

    @Test
    public void userSaveNewDoprava() {
        driver.get("http://localhost:8080/doprava-reg-form");
        driver.findElement(By.name("popis")).sendKeys("doprava1");
        driver.findElement(By.name("cena")).sendKeys("55");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam doprav']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava1']")).size());
    }

    @Test
    public void checkMoreDoprava() {
        creator.saveEntities(
                new Doprava(popis: "doprava1"),
                new Doprava(popis: "doprava2"),
                new Doprava(popis: "doprava3"),
                new Doprava(popis: "doprava4")
        )
        driver.get("http://localhost:8080/doprava");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam doprav']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava4']")).size());
    }
    @Test
    public void userCheckValidDetails() {
        creator.saveEntities(
                new Doprava(popis: "doprava1", cena: 20),
                new Doprava(popis: "doprava2", cena: 40),
                new Doprava(popis: "doprava3", cena: 60),
                new Doprava(popis: "doprava4", cena: 80)
        )
        driver.get("http://localhost:8080/doprava");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam doprav']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='doprava4']")).size());
        driver.findElement(By.xpath("//h4[text()='doprava2']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail dopravy']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='doprava2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='40']")).size());
    }
    @Test
    public void userEditDetails() {
        creator.save(new Doprava(popis: "doprava1", cena: 20));
        driver.get("http://localhost:8080/doprava-reg-form/1");
        driver.findElement(By.name("popis")).clear();
        driver.findElement(By.name("popis")).sendKeys("dopravaZmena");
        driver.findElement(By.name("cena")).clear();
        driver.findElement(By.name("cena")).sendKeys("40");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam doprav']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='dopravaZmena']")).size());
        driver.findElement(By.xpath("//h4[text()='dopravaZmena']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail dopravy']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='dopravaZmena']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='40']")).size());
    }
}
