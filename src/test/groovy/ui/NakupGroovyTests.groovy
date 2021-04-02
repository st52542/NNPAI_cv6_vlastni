package ui

import cz.vlastni.eshop.EshopApplication
import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.entity.Doprava
import cz.vlastni.eshop.entity.Nakup
import cz.vlastni.eshop.entity.Platba
import cz.vlastni.eshop.entity.Uzivatel
import cz.vlastni.eshop.repository.NakupRepository
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = EshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
class NakupGroovyTests {
    private WebDriver driver;

    @Autowired
    NakupRepository nakupRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromeDriverPath = NakupGroovyTests.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        nakupRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userGetToIndex() {
        driver.get("http://localhost:8080");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam nakupu']")).size());
    }

    @Test
    public void userGetToAddNewNakup() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.xpath("//a[@href='checkout']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Konecna Hlaska']")).size());
    }

    @Test
    public void userSaveFirstValueNewNakup() {
        creator.save(new Uzivatel(jmeno: "jmeno1"))
        creator.save(new Doprava(popis: "doprava1"));
        creator.save(new Platba(popis: "platba1"));
        driver.get("http://localhost:8080/checkout");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        List<Nakup> nakupy=nakupRepository.findAll();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam nakupu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='"+nakupy.get(0).getObjednavka()+"']")).size());
        driver.findElement(By.xpath("//h4[text()='"+nakupy.get(0).getObjednavka()+"']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='platba1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='doprava1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='jmeno1']")).size());
    }

    @Test
    public void userSaveSecondValueNewNakup() {
        creator.save(new Uzivatel(jmeno: "jmeno1"))
        creator.save(new Uzivatel(jmeno: "jmeno2"))
        creator.save(new Doprava(popis: "doprava1"));
        creator.save(new Doprava(popis: "doprava2"));
        creator.save(new Platba(popis: "platba1"));
        creator.save(new Platba(popis: "platba2"));
        driver.get("http://localhost:8080/checkout");
        driver.findElement(By.xpath("//select[@name='platba']/option[2]")).click();
        driver.findElement(By.xpath("//select[@name='doprava']/option[2]")).click();
        driver.findElement(By.xpath("//select[@name='uzivatel']/option[2]")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        List<Nakup> nakupy=nakupRepository.findAll();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam nakupu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='"+nakupy.get(0).getObjednavka()+"']")).size());
        driver.findElement(By.xpath("//h4[text()='"+nakupy.get(0).getObjednavka()+"']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='platba2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='doprava2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='jmeno2']")).size());
    }

    @Test
    public void checkMoreNakupu() {
        creator.saveEntities(
                new Nakup(objednavka: 111111),
                new Nakup(objednavka: 222222),
                new Nakup(objednavka: 333333),
                new Nakup(objednavka: 444444)
        )
        driver.get("http://localhost:8080/");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam nakupu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='111111']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='222222']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='333333']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='444444']")).size());
    }
    @Test
    public void userCheckValidDetails() {
        creator.saveEntities(
                new Nakup(objednavka: 111111),
                new Nakup(objednavka: 222222),
                new Nakup(objednavka: 333333),
                new Nakup(objednavka: 444444)
        )
        driver.get("http://localhost:8080/");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam nakupu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='111111']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='222222']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='333333']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='444444']")).size());
        driver.findElement(By.xpath("//h4[text()='222222']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail nakupu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h3[text()='datum']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='"+nakupRepository.findByObjednavka(222222).getDatumVytvoreni()+"']")).size());
    }
}
