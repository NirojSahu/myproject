package com.test;

import Utilities.BasePage;
//import com.test.helper.BasePage;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.SeleniumHelper;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.test.CustomHooks.GlobalHooks;



@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber.html",
                "junit:target/cucumber.xml",
                "json:target/cucumber-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        // tags = {"@3A,@3B,@3C,@3D,@3E,@3F,@3F1,@3G,@3I,@3J,@3H,@3L,@3M"},
        //tags = {"@deleteaccountconsent"},
        // tags = {"@transactions,@deleteaccountconsent,@statements,@Accountconsent,@balances,@beneficiaries,@Products,@accounts,@DirectDebits,@standingOrders,@SchedulePayments"},
        //tags = {"@UJTransactions5"},
        //tags = {"@UJBeneficiaries5"},
        tags = {"@AISUJ111"},
        //tags = {"@token"},
        //tags = {"@UJAccounts4,@UJBalances4,@UJBeneficiaries4,@UJDirectdebits4,@UJSchedulePayment4,@UJStandingOrder4,@UJStatement4,@UJTransactions4"},
        //tags={"@token,@transactions,@deleteaccountconsent,@statements,@Accountconsent,@balances,@beneficiaries,@Products,@accounts,@DirectDebits,@standingOrders,@SchedulePayments"},
        glue = { "com.test.stepDefs","com.test.CustomHooks","src.main.java.Utilities.WebKit.hooksnoteardown","classpath:com/test/hooks"},//},
//        glue = { "classpath:com/test/stepDefs","classpath:com/test/screenshothook","classpath:com/test/hooksnoteardown","classpath:com/test/CustomHooks"},
        //glue = { "classpath:com/test/stepDefs","classpath:com/test/screenshothook","classpath:com/test/hooks"},
        junit = "--step-notifications",
        features = "classpath:features")

public class TestRunner extends BasePage {
//    public void setData(){
//    GlobalHooks.BeforeScenario();
//}

    @AfterClass
    public static void teardown()
    {
       SeleniumHelper.getdriver().close();
    }
}



