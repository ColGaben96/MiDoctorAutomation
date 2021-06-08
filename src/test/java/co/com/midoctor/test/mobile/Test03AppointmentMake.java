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
public class Test03AppointmentMake {

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
        Thread.sleep(3500);
        MobileElement more = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"Más\"]/android.view.View/android.view.View"));
        more.click();
        Thread.sleep(1000);
        MobileElement finder = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\"Buscador\"]"));
        finder.click();
        Thread.sleep(7500);
        MobileElement acceptDisclaimer = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[5]/android.view.View/android.widget.Button[2]"));
        acceptDisclaimer.click();
        Thread.sleep(500);
        MobileElement givePermissions = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]"));
        givePermissions.click();
        Thread.sleep(500);
        givePermissions.click();
        Thread.sleep(500);
        MobileElement doctorSearch = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText"));
        doctorSearch.sendKeys(Constants.DOCTORSEARCH);
        Thread.sleep(1500);
        MobileElement searchDoctor = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View"));
        searchDoctor.click();
        Thread.sleep(500);
        MobileElement appointments = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[4]/android.widget.Button[3]"));
        appointments.click();
        Thread.sleep(2500);
        MobileElement hour = (MobileElement) driver.findElement(By.xpath("//android.view.View[@content-desc=\" 01:00 am\"]"));
        hour.click();
        Thread.sleep(6500);
        MobileElement healthProvider = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[6]/android.view.View[2]"));
        healthProvider.click();
        Thread.sleep(1000);
        MobileElement colsubsidio = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"));
        colsubsidio.click();
        MobileElement linkingType = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[7]/android.view.View[2]"));
        linkingType.click();
        Thread.sleep(1000);
        MobileElement buyer = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"));
        buyer.click();
        MobileElement appointmentType = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[8]/android.view.View[2]"));
        appointmentType.click();
        Thread.sleep(1000);
        MobileElement control = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"));
        control.click();
        MobileElement motive = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[9]/android.widget.EditText"));
        motive.sendKeys("Esta es una prueba. Favor Descartar");
        MobileElement accompaniment = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[10]/android.widget.EditText"));
        accompaniment.sendKeys("Prueba Tecnica");
        MobileElement makeAppointment = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[11]/android.widget.Button"));
        makeAppointment.click();
        /*
         * Take Screenshot and Do Report
         */
        var timeFinish = System.currentTimeMillis()-timeStart;
        var clockFinish = LocalTime.now();
        File evidence01 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(evidence01, new File("./screenEvidences/evidence-"+ LocalDate.now() + "-Suite 3"+".png"));
        report.writeXMLResult(1, tests.getArray().get(0).getName(), tests.getArray().get(0).getDescription(), timeFinish, clockStart, clockFinish, tests.getArray().get(0).getValue(), "¡Felicidades", true);
        report.finishTagXML();
    }
}
