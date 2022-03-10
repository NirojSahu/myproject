package com.test.Utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Report_Jenkin {
    public static void main(String[] args) {

        File reportOutputDirectory = new File(System.getProperty("user.dir")+"\\target");
        List<String> jsonFiles = new ArrayList<>();

        jsonFiles.add(System.getProperty("user.dir")+"\\target\\cucumber-report.json");
        String buildNumber = "1";
        String projectName = "Open-Banking-CA";
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
 //optional configuration
 //configuration.setParallelTesting(parallelTesting);
//configuration.setRunWithJenkins(runWithJenkins);
//configuration.setBuildNumber(buildNumber);
 //addidtional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Environment", "3A");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "Develop1");

 //optionally add metadata presented on main page via properties file
      /*  List<String> classificationFiles = new ArrayList<>();
        classificationFiles.add("D:\\new_cukes\\FlexCube\\properties-1.properties");
classificationFiles.add("properties-2.properties");
        configuration.addClassificationFiles(classificationFiles);
*/
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
    }

}
