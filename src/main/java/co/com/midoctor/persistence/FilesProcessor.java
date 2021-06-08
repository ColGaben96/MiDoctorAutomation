package co.com.midoctor.persistence;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class FilesProcessor {

    private File f;
    private static int CASEEXCECUTED = 1;

    public void startTagXML() throws IOException {
        f = new File("./reports/report-"+LocalDate.now()+"-suite"+ CASEEXCECUTED +".xml");
        while (f.exists()) {
            CASEEXCECUTED++;
            f = new File("./reports/report-"+LocalDate.now()+"-suite"+ CASEEXCECUTED +".xml");
        }
        PrintWriter pw = new PrintWriter(new FileWriter(f, true));
        pw.println("<report>\n");
        pw.close();

    }

    public void writeXMLResult(int caseID,
                               String caseName,
                               String description,
                               long millsElapsed,
                               LocalTime timeStarted,
                               LocalTime timeFinished,
                               Object expectedValue,
                               Object currentValue,
                               boolean passed) throws IOException {
        f = new File("./reports/report-"+LocalDate.now()+"-suite"+ CASEEXCECUTED +".xml");
        PrintWriter pw = new PrintWriter(new FileWriter(f, true));
        BufferedReader br = new BufferedReader(new FileReader(f));
        pw.println("<caseID>"+caseID+"</caseID>");
        pw.println("<caseName>"+caseName+"</caseName>");
        pw.println("<description>\n"+description+"\n</description>");
        pw.println("<timeElapsed>"+millsElapsed+" ms</timeElapsed>");
        pw.println("<timeStarted>"+timeStarted+"</timeStarted>");
        pw.println("<timeFinished>"+timeFinished+"</timeFinished>");
        pw.println("<expectedValue>"+expectedValue+"</expectedValue>");
        pw.println("<currentValue>"+currentValue+"</currentValue>");
        pw.println("<passed>"+passed+"</passed>");
        pw.close();
    }

    public void finishTagXML() throws IOException {
        f = new File("./reports/report-"+LocalDate.now()+"-suite"+ CASEEXCECUTED +".xml");
        PrintWriter pw = new PrintWriter(new FileWriter(f, true));
        pw.println("\n</report>");
        pw.close();
    }

    public void writeLog(Object content) throws IOException {
        f = new File("./logs/log-"+LocalDate.now()+".log");
        FileWriter fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        fw.close();
    }

    public ArrayList<String> readCredentials(String credName) throws IOException {
        ArrayList<String> obj = new ArrayList<>();
        Properties p = new Properties();
        f = new File("./lib/"+credName+".properties");
        p.load(new FileInputStream(f));
        obj.add(p.getProperty("name"));
        obj.add(p.getProperty("username"));
        obj.add(p.getProperty("password"));
        return obj;
    }

    public void writeCredentials(String credName, String username, String password) throws IOException {
        Properties p = new Properties();
        f = new File("./lib/"+credName+".properties");
        p.setProperty("name", credName);
        p.setProperty("username", username);
        p.setProperty("password", password);
        p.store(new FileOutputStream(f), "");
    }
}
