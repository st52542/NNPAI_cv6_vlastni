package ui

import cz.vlastni.eshop.EshopApplication
import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.entity.Doprava
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
    public void userGetToAddNewUzivatel() {
        driver.get("http://localhost:8080");
        driver.findElement(By.xpath("//a[@href='/checkout']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Konecna Hlaska']")).size());
    }

    @Test
    public void userSaveNewUzivatel() {
        creator.save(new Uzivatel(jmeno: "jmeno1"))
        creator.save(new Doprava(popis: "doprava1"));
        creator.save(new Platba(popis: "platba1"));
        driver.get("http://localhost:8080/checkout");
        driver.findElement(By.name("jmeno")).sendKeys("jmeno1");
        driver.findElement(By.name("prijmeni")).sendKeys("prijmeni1");
        driver.findElement(By.name("heslo")).sendKeys("heslo1");
        driver.findElement(By.name("adresa")).sendKeys("adresa1");
        driver.findElement(By.name("email")).sendKeys("email@email.cz");
        driver.findElement(By.name("admin")).sendKeys("true");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam uzivatelu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno1']")).size());
    }

    @Test
    public void checkMoreUzivatel() {
        creator.saveEntities(
                new Uzivatel(jmeno: "jmeno1"),
                new Uzivatel(jmeno: "jmeno2"),
                new Uzivatel(jmeno: "jmeno3"),
                new Uzivatel(jmeno: "jmeno4")
        )
        driver.get("http://localhost:8080/uzivatel");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam uzivatelu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno4']")).size());
    }
    @Test
    public void userCheckValidDetails() {
        creator.saveEntities(
                new Uzivatel(jmeno: "jmeno1", prijmeni: "prijmeni1"),
                new Uzivatel(jmeno: "jmeno2", prijmeni: "prijmeni2"),
                new Uzivatel(jmeno: "jmeno3", prijmeni: "prijmeni3"),
                new Uzivatel(jmeno: "jmeno4", prijmeni: "prijmeni4")
        )
        driver.get("http://localhost:8080/uzivatel");
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam uzivatelu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno2']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno3']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmeno4']")).size());
        driver.findElement(By.xpath("//h4[text()='jmeno1']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail uzivatele']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='jmeno1']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='prijmeni1']")).size());
    }
    @Test
    public void userEditDetails() {
        creator.save(new Uzivatel(jmeno: "jmeno1", prijmeni: "prijmeni1"));
        driver.get("http://localhost:8080/uzivatel-reg-form/1");
        driver.findElement(By.name("jmeno")).clear();
        driver.findElement(By.name("jmeno")).sendKeys("jmenoZmena");
        driver.findElement(By.name("prijmeni")).clear();
        driver.findElement(By.name("prijmeni")).sendKeys("prijmeniZmena");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Seznam uzivatelu']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h4[text()='jmenoZmena']")).size());
        driver.findElement(By.xpath("//h4[text()='jmenoZmena']")).click();
        Assertions.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Detail uzivatele']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='jmenoZmena']")).size());
        Assertions.assertEquals(1, driver.findElements(By.xpath("//p[text()='prijmeniZmena']")).size());
    }
}
