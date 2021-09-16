//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.helper.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Fillo fillo = null;
    private Connection con;
    private String strQuery;

    public ExcelReader() {
        new Fillo();
    }

    public Map<String, String> getScenarioData(String excelPath, String sheetName, String scenarioField, String scenarioName) throws FilloException {
        this.strQuery = "select " + scenarioField + " ," + scenarioName + " from " + sheetName;
        this.con = this.xcelConnect(excelPath);
        Recordset recordset = this.con.executeQuery(this.strQuery);
        HashMap sCollection = new HashMap();

        while(recordset.next()) {
            sCollection.put(recordset.getField(scenarioField), recordset.getField(scenarioName));
        }

        recordset.close();
        this.con.close();
        return sCollection;
    }

    public void updateScenarioData(String excelPath, String sheetName, String scenarioField, String scenarioName, String setValue, String searchValue) throws FilloException {
        this.con = this.xcelConnect(excelPath);
        this.strQuery = "Update " + sheetName + " set " + scenarioName + " ='" + setValue + "' where " + scenarioField + "'" + searchValue + "'";
        this.con.executeUpdate(this.strQuery);
        this.con.close();
    }

    private Connection xcelConnect(String excelPath) throws FilloException {
        return this.fillo.getConnection(excelPath);
    }
}
