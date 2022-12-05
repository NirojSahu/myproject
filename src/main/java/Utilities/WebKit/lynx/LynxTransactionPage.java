//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.lynx;

import Utilities.WebKit.helper.BasePage;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LynxTransactionPage extends BasePage {
    public LynxTransactionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFlagAlert() {
        try {
            WebElement acceptButton = this.driver.findElement(By.id("BOTON_ACEPTAR_INSTRUCCIONES_FLAGS"));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
            }
        } catch (Exception var2) {
            System.out.println("There is no Flag Alert");
        }

    }

    public void clickOnSearchDates() {
        this.driver.findElement(By.id("sSeleccionFecha_PB0_BOTON_SELECCION_FECHAS")).click();
    }

    public HashMap<String, String> getRowDataInMap(WebElement headersRow, WebElement row) {
        HashMap<String, String> map = new HashMap();
        List<WebElement> headers = headersRow.findElements(By.tagName("th"));
        List<WebElement> rowData = row.findElements(By.tagName("td"));
        if (headers.size() == rowData.size()) {
            for(int i = 0; i < headers.size(); ++i) {
                map.put(((WebElement)headers.get(i)).getText(), ((WebElement)rowData.get(i)).getText());
            }
        }

        return map;
    }

    public boolean validateTransactionForAcc(String accNum, String amount, String transactionCode, String responseCode) {
        WebElement headersRow = this.driver.findElement(By.xpath("//tr[@id = 'PB0']"));
        WebElement firstRow = this.driver.findElement(By.id("PB00"));
        HashMap<String, String> map = this.getRowDataInMap(headersRow, firstRow);
        String amountAct = (String)map.get("Requested amount");
        String originAccNumAct = (String)map.get("Origin Account");
        String transactionCodeAct = (String)map.get("Transaction Code");
        String responseCodeAct = (String)map.get("Response Code");
        boolean accPresent = originAccNumAct.contains(accNum.replace(" ", ""));
        boolean amountPresent = amountAct.contains(amount);
        boolean transactionCodePresent = transactionCodeAct.contains(transactionCode);
        boolean responseCodePresent = responseCodeAct.contains(responseCode);
        return accPresent && amountPresent && transactionCodePresent && responseCodePresent;
    }

    public void logout() {
        this.driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    public boolean verifyTransactionForAcc(String accNum, String amount, String transactioncode, String responsecode) throws InterruptedException, MalformedURLException {
        this.verifyFlagAlert();
        this.clickOnSearchDates();
        return this.validateTransactionForAcc(accNum, amount, transactioncode, responsecode);
    }
}
