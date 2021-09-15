package com.test.stepDefs;

import com.google.inject.Inject;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.configuration.PageFactory;
import com.test.exceptions.StopTestException;
import com.test.helper.BasePage;
import com.test.pages.HomePage;
import com.test.pages.MyAccounts;
import com.test.pages.Navigate;
import com.test.pages.ResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SampleStepDef extends BasePage {

    @Inject private Navigate navigate;
   @Inject private HomePage homePage;
    @Inject private ResultsPage resultsPage;

    @Inject private App_genericFunction general;
    @Inject private MyAccounts account_details;

    @Given("^I navigate to google home page$")
    public void navigateToGoogleHomePage() {
        navigate.toGoogleHomePage();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String keyWord) throws Throwable {
        homePage.enterKeyword(keyWord);
       homePage.clickOnSearch();

    }

    @Then("^Google return some results$")
    public void googleReturnSomeResults() throws Throwable {
        assertThat(resultsPage.isResultsDisplayed(), is(equalTo(true)));
    }

    @Given("^I navigate to santander home page$")
    public void navigateToSantanderPage() {
        navigate.toSanHomePage();
    }

    @When("^I select \"([^\"]*)\"$")
    public void selectAccount(String accountType) {
        homePage.selectedAccount(accountType);
    }

    @When("^I succesfully navigate to \"([^\"]*)\" page$")
    public void verifyPageTitle(String title) {
        assertThat(resultsPage.isTitleDisplayed(title), is(equalTo(true)));
    }


    @Then("I want to close driver")
    public void iWantToCloseDriver() throws StopTestException {
        PageFactory.tearDown();
    }
    @Given("test API in Jenkins")
    public void testAPIInJenkins() throws org.json.JSONException, InterruptedException {
        /*LinkedHashMap maps = new LinkedHashMap();
        maps.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkludGVybmV0X1JTMjU2In0.eyJpc3MiOiJJbnRlcm5ldCIsInN1YiI6IjRjOGYzMzY2LTRhNzMtNDY4Ni1iZjg4LTQzODFkN2YwODZjOSIsImF1ZCI6ImFjY291bnRzIiwibmJmIjoxNTg3NzM5ODU0LCJleHAiOjE1ODc3NDM0NTQsImlhdCI6MTU4NzczOTg1NCwianRpIjoiODEyNTFjMzQtNDZkNS00M2MzLTgwZDYtZTAzZTNiZDlhYzZmIiwibWlzIjoiW3I6Z2F0ZXdheV1hOnxpbnRlbnRJZD0wNDliNTdkMy1kZDlkLTRhNTgtODg3OC1hMjQ4MmVlY2U5Y2IiLCJlbnQiOiJzYW51ayIsImVudiI6ImludGVybmV0In0.vJin5WZ9dxEX0OkO7tC_tgAAb3nKrzf7bRm2LoHxmckP_Vg5B7GP9SqReI7IVlfIdbZtMhicOL_yWy6j1okGhqYZGNlzfr0eVykW_kD1wLvTWNC6vjdZSzl53NHatBafSTEcdeizLzjs21WzjYTjQMLBFnJu6VgSyCDu44VCEr0abCIOCkaruNy3drbnA8BF2iZh9wbFhJqddtwsPTsiWHe8r9KNyk8i6yUxFxxDm_u8gEDQVmPtlrZPucWQjRNvZaTG7VH2JDpYXeAYaNHG5Ltsuj2BTNvU85cxVfLa0y-34c6xiLA-fsk3O4_iRO9CFrE5NkIygZS1iRatxM7MzQ");
        maps.put("x-fapi-financial-id","TEST");
        maps.put("Content-Type","application/json");
        Response response =  APIMethods.method_get_withoutbody(GlobalHooks.BaseURI,maps,GlobalHooks.ResourceURI);*/
       // Response response1 = App_genericFunction.getDatafromDatabase("600007", "48894458");
        //Response response =  APIMethods.method_get_withoutheader(GlobalHooks.BaseURI,GlobalHooks.ResourceURI);
        //App_genericFunction.putcommentinStep("AAT token : "+ jwt.generateJwtToken_AAT(GlobalHooks.privatekey,GlobalHooks.publickey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa"));
        //App_genericFunction.putcommentinStep("AuthURL token : "+generateJwtToken_forauthURL(GlobalHooks.privatekey,GlobalHooks.publickey,"9cd4b78b-7d65-4efc-a565-f3205d04b6b1","https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa","https://www.google.com/","account"));
        //App_genericFunction.putcommentinStep("CAT token : "+generateJwtToken_CAT(GlobalHooks.privatekey,GlobalHooks.publickey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa"));
        //System.out.println(response.getStatusCode());
        ///System.out.println(response1.getStatusCode());
     /*   general.getintoCAInternetBanking("", "700017701", "112233", "Welcome@01");
        account_details.clickOnMyAccountsServiceLink("Account details");
        account_details.selctPersonalAccountType("GBP","90010553");
        //account_details.getAccountCompleteDetails();

        System.out.println(account_details.getAccountCompleteDetails());*/
        //  SeleniumHelper.getdriver().navigate().to("https://10.6.184.26:8243/authorize/?response_type=code id_token&scope=am_application_scope accounts openid&client_id=XjqOowR0kXZe_iYqGWVfVlQC9esa&request=eyJhbGciOiJSUzI1NiJ9.eyJtYXhfYWdlIjo4NjQwMCwiYXVkIjoiaHR0cHM6Ly8xMC42LjE4NC4yNjo4MjQzL3Rva2VuIiwic2NvcGUiOiJhY2NvdW50IG9wZW5pZCIsImlzcyI6IlhqcU9vd1Iwa1haZV9pWXFHV1ZmVmxRQzllc2EiLCJjbGFpbXMiOnsidXNlcmluZm8iOnsib3BlbmJhbmtpbmdfaW50ZW50X2lkIjp7InZhbHVlIjoiM2ZhMjBhM2MtN2ViNy00NGFlLWEzZjEtNDM3MjNmM2Q3ZjU2IiwiZXNzZW50aWFsIjp0cnVlfX0sImlkX3Rva2VuIjp7Im9wZW5iYW5raW5nX2ludGVudF9pZCI6eyJ2YWx1ZSI6IjNmYTIwYTNjLTdlYjctNDRhZS1hM2YxLTQzNzIzZjNkN2Y1NiIsImVzc2VudGlhbCI6dHJ1ZX0sImFjciI6eyJlc3NlbnRpYWwiOnRydWUsInZhbHVlcyI6WyJ1cm46b3BlbmJhbmtpbmc6cHNkMjpzY2EiLCJ1cm46b3BlbmJhbmtpbmc6cHNkMjpjYSJdfX19LCJyZXNwb25zZV90eXBlIjoiY29kZSBpZF90b2tlbiIsInJlZGlyZWN0X3VyaSI6Imh0dHBzOi8vd3d3Lmdvb2dsZS5jb20vIiwiZXhwIjoxNTk0Mzg0Mjg4LCJub25jZSI6Im4tMFM2X1d6QTJNIiwiY2xpZW50X2lkIjoiWGpxT293UjBrWFplX2lZcUdXVmZWbFFDOWVzYSJ9.TJKtMfVcdyTzxDi6a6s_jjwiOfjHqth7_eDaDkRf31RG2WQPf_9g3mm_RG2IyhHVWSV7XaRovx9ROwJXa0OCvS6hNtKQJlh9sv7CAZe2YKICYLDGiv35B8DkSumIrBrPJCi07ikzi1eDoI4A-MYG24kVssz64vD68kjsmsMeSQDr5kKrrlStHWJ1QlHEq6cF9Qn4yvgM_an6tsmj0fRVLPwThL_Qq3VMgKnX84yPJi8_1SQ42CgDGEoMc6lzLeQQj5Ps0B7WUlR-qgnvrVr3iCZuiKcauRSBQXBR3_8Cx2rC_kQqCqkAjGhUBi4ovlDz0HNpD686f02DCfsBiU21Hw&prompt=login&redirect_uri=https://www.google.com/");
        //  seleniumHelper.waitForJSandJQueryToLoad();
        //  System.out.println("URL : " + SeleniumHelper.getdriver().getCurrentUrl());

    }
}
