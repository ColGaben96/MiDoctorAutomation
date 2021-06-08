package co.com.midoctor.test.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Gabriel Blanco
 * @version 1.0
 * Plantilla para suite de automatización <br>
 * <b>IMPORTANTE</b> <br>
 * Crear una nueva clase y copiar todo el contenido de la clase MobileTestTemplate sin el public class MobileTestTemplate.
 */
public class MobileTestTemplate {

    /**
     * Caso de prueba específico soportando en documento previo.
     * @param args
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "RF8N714061D");
        dc.setCapability("platformName", "android");
        dc.setCapability("appPackage", "co.com.icareweb");
        dc.setCapability("appActivity", "co.com.icareweb.MainActivity");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        Thread.sleep(5000);
        //TODO: Llenar el guión aquí
    }
}
