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
import java.util.ArrayList;

public class Test03AppointmentMake {
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
        try {
            authentication();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void authentication() throws InterruptedException, IOException {
        var login = driver.findElement(By.xpath("/html/body/div[1]/include-after[1]/div/nav/div/nav/ul[2]/li[2]/a"));
        login.click();
        Thread.sleep(500);
        var email = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div[2]/div/div/div/div[1]/div/div/form/div[1]/input"));
        email.sendKeys(Constants.PATIENTEMAIL);
        var password = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div[2]/div/div/div/div[1]/div/div/form/div[2]/input"));
        password.sendKeys(Constants.COMMONPASSWORD);
        var button = driver.findElement(By.xpath("/html/body/div[1]/ng-view/div/div/div[2]/div/div/div/div[1]/div/div/form/button"));
        button.click();
        Thread.sleep(8000);
    }

    /**
     * Caso de prueba específico soportando en documento previo.
     */
    @Test
    public void case01DoctorSearch() throws InterruptedException, IOException {
        tests.create(1, "case01DoctorSearch", """
                Debe crear la cita correctamente
                """, "×\n" +
                "La cita ha sido agendada correctamente");
        timestart.add(LocalTime.now());
        var timeStart = System.currentTimeMillis();
        var doctorSearch = driver.findElement(By.xpath("/html/body/div[1]/header/ng-include[1]/div/nav/ul/li[5]/a"));
        doctorSearch.click();
        Thread.sleep(1000);
        var findDoctor = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[1]/div[2]/div/input"));
        findDoctor.sendKeys(Constants.DOCTORSEARCH);
        Thread.sleep(3500);
        var goSearch = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[1]/div[3]/button"));
        goSearch.click();
        Thread.sleep(500);
        var doAppointment = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[4]/div/div[2]/div/div[3]/a"));
        doAppointment.click();
        Thread.sleep(4500);
        var selectHours = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div[1]/a/div"));
        selectHours.click();
        Thread.sleep(500);
        var selectLocation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[5]/div[1]/div/div/div/div[1]/span"));
        selectLocation.click();
        Thread.sleep(500);
        var location = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[5]/div[1]/div/div/div/ul/li/div[3]/span"));
        location.click();
        Thread.sleep(500);
        var selectVinculation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[5]/div[2]/div/div/div/div[1]/span"));
        selectVinculation.click();
        Thread.sleep(500);
        var vinculation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[5]/div[2]/div/div/div/ul/li/div[3]/span"));
        vinculation.click();
        Thread.sleep(500);
        var appointmentTSelector = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[6]/div[1]/div/div/div/div[1]/span"));
        appointmentTSelector.click();
        Thread.sleep(500);
        var appointmentType = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[6]/div[1]/div/div/div/ul/li/div[3]/span"));
        appointmentType.click();
        Thread.sleep(500);
        var company = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[6]/div[2]/div/input"));
        company.sendKeys("Prueba Tecnica de Automatización");
        var motive = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[1]/div[7]/div/textarea"));
        motive.sendKeys("Esta es una prueba. Favor descartar");
        var goAppointment = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[2]/div/div/input"));
        goAppointment.click();
        Thread.sleep(2500);
        var createdSuccessfully = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[3]/div/div/div"));
        currentVals.add(createdSuccessfully.getText());
        results.add(tests.passed(1, createdSuccessfully.getText()));
        File evidence01 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(evidence01, new File("./screenEvidences/evidence-"+ LocalDate.now() + "-Suite3-Case1"+".png"));
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
