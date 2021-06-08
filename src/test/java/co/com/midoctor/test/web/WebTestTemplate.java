package co.com.midoctor.test.web;

import co.com.midoctor.CredentialsDAO;
import co.com.midoctor.TestDAO;
import co.com.midoctor.persistence.FilesProcessor;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Plantilla para suite de automatización <br>
 * <b>IMPORTANTE</b> <br>
 * Crear una nueva clase y copiar todo el contenido de la clase WebTestTemplate sin el public class WebTestTemplate.
 */
public class WebTestTemplate {
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
     * Caso de prueba específico soportando en documento previo.
     */
    @Test
    public void case01CaseName() throws InterruptedException, IOException {
        timestart.add(LocalTime.now());
        var timeStart = System.currentTimeMillis();
        //TODO: Llenar aquí el guión
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
