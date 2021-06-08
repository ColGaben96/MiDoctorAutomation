package co.com.midoctor.test.mobile;

import co.com.midoctor.TestDAO;
import co.com.midoctor.persistence.FilesProcessor;
import co.com.midoctor.test.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Plantilla para suite de automatización <br>
 * <b>IMPORTANTE</b> <br>
 * Crear una nueva clase y copiar todo el contenido de la clase MobileTestTemplate sin el public class MobileTestTemplate.
 */
public class Test02PatientAuthentication {

    /**
     * Caso de prueba específico soportando en documento previo.
     * @param args
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        FilesProcessor report = new FilesProcessor();
        report.startTagXML();
        TestDAO tests = new TestDAO();
        tests.create(1, "Case01PatientRegistration", """
                El registro del paciente debe ser exitoso
                """, "¡Felicidades!");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "RF8N714061D");
        dc.setCapability("platformName", "android");
        dc.setCapability("appPackage", "co.com.icareweb");
        dc.setCapability("appActivity", "co.com.icareweb.MainActivity");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        Thread.sleep(5000);
        var timeStart = System.currentTimeMillis();
        var clockStart = LocalTime.now();
        MobileElement signin = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"Iniciar sesión\"]/android.widget.TextView[2]"));
        signin.click();
        Thread.sleep(500);
        MobileElement email = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.EditText"));
        email.sendKeys(Constants.PATIENTEMAIL);
        MobileElement password = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.EditText"));
        password.sendKeys(Constants.COMMONPASSWORD);
        MobileElement login = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"));
        login.click();
        /*
         * Take Screenshot and Do Report
         */
        var timeFinish = System.currentTimeMillis()-timeStart;
        var clockFinish = LocalTime.now();
        File evidence01 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(evidence01, new File("./screenEvidences/evidence-"+ LocalDate.now() + "-Suite 2"+".png"));
        report.writeXMLResult(1, tests.getArray().get(0).getName(), tests.getArray().get(0).getDescription(), timeFinish, clockStart, clockFinish, tests.getArray().get(0).getValue(), "¡Felicidades", true);
        report.finishTagXML();
    }
}
