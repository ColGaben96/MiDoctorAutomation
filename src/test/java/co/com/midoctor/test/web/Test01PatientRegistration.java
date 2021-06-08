package co.com.midoctor.test.web;

import co.com.midoctor.CredentialsDAO;
import co.com.midoctor.TestDAO;
import co.com.midoctor.persistence.FilesProcessor;
import co.com.midoctor.test.Constants;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Plantilla para suite de automatización <br>
 * <b>IMPORTANTE</b> <br>
 * Crear una nueva clase y copiar todo el contenido de la clase WebTestTemplate sin el public class WebTestTemplate.
 */
public class Test01PatientRegistration {
    private WebDriver driver;
    private final TestDAO tests = new TestDAO();
    private final CredentialsDAO credentials = new CredentialsDAO();
    private final ArrayList<Long> times = new ArrayList<>();
    private final ArrayList<LocalTime> timestart = new ArrayList<>();
    private final ArrayList<LocalTime> timefinish = new ArrayList<>();
    private final ArrayList<Object> currentVals = new ArrayList<>();
    private final ArrayList<Boolean> results = new ArrayList<>();

    /**
     * Crea un archivo nuevo al momento de iniciar una suite de pruebas.
     */
    @BeforeAll
    static void startReport() {
        try {
            new FilesProcessor().startTagXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un nuevo ambiente en cada caso de prueba.
     */
    @BeforeEach
    public void envSetup() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://midoctor.com.co");
    }

    /**
     * Registro de paciente con cédula de ciudadanía
     */
    @Test
    public void case01Registration() throws InterruptedException, IOException {
        timestart.add(LocalTime.now());
        var timeStart = System.currentTimeMillis();
        tests.create(1, "Test01PatientRegistration", """
                El paciente se debe registrarse exitosamente.
                """, "¡Felicitaciones!");
        var registro = driver.findElement(By.xpath("/html/body/div[1]/include-after[1]/div/nav/div/nav/ul[2]/li[1]/a/span"));
        registro.click();
        Thread.sleep(500);
        var paciente = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div[2]/div[2]/div/a/div"));
        paciente.click();
        Thread.sleep(500);
        var fname = driver.findElement(By.xpath("//*[@id=\"firstname\"]"));
        fname.sendKeys(Constants.PATIENTNAME);
        var lname = driver.findElement(By.xpath("//*[@id=\"lastname\"]"));
        lname.sendKeys(Constants.PATIENTLASTNAME);
        var email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys(Constants.PATIENTEMAIL);
        var password = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        password.sendKeys(Constants.COMMONPASSWORD);
        var endRegistration = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div/form/div[3]/div/div/button"));
        endRegistration.click();
        Thread.sleep(2500);
        var city = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/form/div[1]/div[2]/div/div/div[1]/span"));
        city.click();
        Thread.sleep(500);
        var citySearch = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/form/div[1]/div[2]/div/div/input[1]"));
        citySearch.sendKeys(Constants.PATIENTCITY);
        Thread.sleep(500);
        var selCity = driver.findElement(By.xpath("//*[@id=\"ui-select-choices-row-3-0\"]/span/div"));
        selCity.click();
        var number = driver.findElement(By.xpath("//*[@id=\"mobile\"]"));
        number.sendKeys(Constants.PATIENTPHONE);
        var agree = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/form/div[3]/div/div[1]/div/label/input"));
        agree.click();
        var finishRegistration = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/form/div[4]/div/button"));
        finishRegistration.click();
        Thread.sleep(2500);
        var finishMessage = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div/div[1]/div[1]/div/h3[1]"));
        File evidence01 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(evidence01, new File("./screenEvidences/evidence-"+ LocalDate.now() + "-case 1"+".png"));
        currentVals.add(finishMessage.getText());
        results.add(tests.passed(1, finishMessage.getText()));
        var timeFinish = System.currentTimeMillis() - timeStart;
        times.add(timeFinish);
        timefinish.add(LocalTime.now());

    }

    /**
     * Destruye la sesión del caso y crea un reporte en la fecha que se haya corrido la regresión.
     */
    @AfterEach
    public void reportNDestroy() {
        driver.close();
        for (int i = 0; i < tests.getArray().size(); i++) {
            try {
                new FilesProcessor().writeXMLResult(tests.getArray().get(i).getId(),
                        tests.getArray().get(i).getName(),
                        tests.getArray().get(i).getDescription(),
                        times.get(i), timestart.get(i), timefinish.get(i),
                        tests.getArray().get(i).getValue(), currentVals.get(i),
                        results.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Finaliza el reporte
     */
    @AfterAll
    static void finishReport() {
        try {
            new FilesProcessor().finishTagXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
