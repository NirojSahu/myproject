$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/Accounts/UJ1_Accounts.feature");
formatter.feature({
  "name": "UJ Accounts Single Account",
  "description": "  Environments : 3A(Dev), 5A(Pre), 4A(UAT)\n\n  Token_1 -\u003e Application Access Token\n  accountaccess-consents_1 -\u003e  Consent ID Generation\n  Token_2 -\u003e Authorization URL\n  Token_3 -\u003e Customer Access Token\n  accounts_2 -\u003e GET /accounts/{AccountId}\n\n  UserJourney:\"ReadAccountsDetail Permissions\"\n  ApplicationAcessToken --\u003e ConsentID Gen --\u003e AUTH URL --\u003e ClientAccessToken  --\u003e Hit AIS Resources",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@QACATERALL-2215"
    },
    {
      "name": "@UJAccounts1"
    },
    {
      "name": "@UJAccounts"
    },
    {
      "name": "@AISUJ"
    }
  ]
});
formatter.scenario({
  "name": "Token_1.16 : Verify application access consent token is generated successfully",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@QACATERALL-2215"
    },
    {
      "name": "@UJAccounts1"
    },
    {
      "name": "@UJAccounts"
    },
    {
      "name": "@AISUJ"
    },
    {
      "name": "@QACATERALL-2320"
    },
    {
      "name": "@id:QACATERALL-2215_1"
    },
    {
      "name": "@Prateek"
    },
    {
      "name": "@4AAccUJ111"
    }
  ]
});
formatter.write("SessionID : c24a4ebd309e81d7606507286f2a28cd");
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I have the data available to create \"POST\" request for application access consent token with JWT",
  "keyword": "Given "
});
formatter.match({
  "location": "TokenStepDef.iHaveTheDataAvailableToCreateRequestForApplicationAccessConsentTokenWithJWT(String)"
});
formatter.write("Directory not created successfully");
formatter.write("JWT TOken : eyJhbGciOiJQUzI1NiIsImtpZCI6IjgzOkFCOjc0OjBFOjJEOkIyOkJGOjgzOjA5OkYxOjc4OjRCOjA3OjkwOkFGOjA2OjdDOjVCOjQwOjM3OkQ5OkI1OjcwOjZFOkUyOjUxOjBDOjFFOjM5OkVBOjI4OjUwIiwidHlwIjoiand0In0.eyJpc3MiOiI1RTZzNDJXMXpoX2FXVUVXTzZmRGh1RERXTGdhIiwic3ViIjoiNUU2czQyVzF6aF9hV1VFV082ZkRodUREV0xnYSIsImV4cCI6MTY2MjkwODExMCwiaWF0IjoxNjQ3MDEwNTEwLCJhdWQiOiJodHRwczovL3BwdWF0ZGV2ZWxvcGVyLmludGVsbGVjdG9ubGluZWJhbmtpbmcuY29tL3Rva2VuIiwianRpIjoiUE9JWEowNzE4NCJ9.g41wv5UU1LrXUDWg5xHp4lfLBnksgv6qqhRMrQroio90lTK5GuYJjQJg6Ecch3vuif9sssh3JJpPoICu_VXASeES6OZKHEVbSHmX3EyWc5JRq1Cq7Z52el28Zvh1xD60_tGkQRgkB4w2oiUQp3dVF2CKS3kekiQYLVE3Fm_hWlTCJlKhWNMQnGUXMvnjdurxcdhD8-nPWdPUGikNGEDH1kSuKV03q7PbhXesHgFSRpelnoGTIV6gAuq3L_zxf1Ia2LX7HKseKOyE5Fc3ntVnzailwOW6z7fifrEbNAY4xFIn7A61JKlbBO134YR2zCuMy5tVM1cjU0NVU9vEXeKo2Q");
formatter.write(" Request as raw-text : client_id\u003d5E6s42W1zh_aWUEWO6fDhuDDWLga\u0026grant_type\u003dclient_credentials\u0026scope\u003dam_application_scope accounts\u0026client_assertion_type\u003durn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer\u0026client_assertion\u003deyJhbGciOiJQUzI1NiIsImtpZCI6IjgzOkFCOjc0OjBFOjJEOkIyOkJGOjgzOjA5OkYxOjc4OjRCOjA3OjkwOkFGOjA2OjdDOjVCOjQwOjM3OkQ5OkI1OjcwOjZFOkUyOjUxOjBDOjFFOjM5OkVBOjI4OjUwIiwidHlwIjoiand0In0.eyJpc3MiOiI1RTZzNDJXMXpoX2FXVUVXTzZmRGh1RERXTGdhIiwic3ViIjoiNUU2czQyVzF6aF9hV1VFV082ZkRodUREV0xnYSIsImV4cCI6MTY2MjkwODExMCwiaWF0IjoxNjQ3MDEwNTEwLCJhdWQiOiJodHRwczovL3BwdWF0ZGV2ZWxvcGVyLmludGVsbGVjdG9ubGluZWJhbmtpbmcuY29tL3Rva2VuIiwianRpIjoiUE9JWEowNzE4NCJ9.g41wv5UU1LrXUDWg5xHp4lfLBnksgv6qqhRMrQroio90lTK5GuYJjQJg6Ecch3vuif9sssh3JJpPoICu_VXASeES6OZKHEVbSHmX3EyWc5JRq1Cq7Z52el28Zvh1xD60_tGkQRgkB4w2oiUQp3dVF2CKS3kekiQYLVE3Fm_hWlTCJlKhWNMQnGUXMvnjdurxcdhD8-nPWdPUGikNGEDH1kSuKV03q7PbhXesHgFSRpelnoGTIV6gAuq3L_zxf1Ia2LX7HKseKOyE5Fc3ntVnzailwOW6z7fifrEbNAY4xFIn7A61JKlbBO134YR2zCuMy5tVM1cjU0NVU9vEXeKo2Q\u0026redirect_uri\u003dhttps://www.google.com/");
formatter.write("Header     : {alg\u003dPS256, kid\u003d83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50, typ\u003djwt}");
formatter.write("Body       : {iss\u003d5E6s42W1zh_aWUEWO6fDhuDDWLga, sub\u003d5E6s42W1zh_aWUEWO6fDhuDDWLga, exp\u003d1662908110, iat\u003d1647010510, aud\u003dhttps://ppuatdeveloper.intellectonlinebanking.com/token, jti\u003dPOIXJ07184}");
formatter.write("Signature  : g41wv5UU1LrXUDWg5xHp4lfLBnksgv6qqhRMrQroio90lTK5GuYJjQJg6Ecch3vuif9sssh3JJpPoICu_VXASeES6OZKHEVbSHmX3EyWc5JRq1Cq7Z52el28Zvh1xD60_tGkQRgkB4w2oiUQp3dVF2CKS3kekiQYLVE3Fm_hWlTCJlKhWNMQnGUXMvnjdurxcdhD8-nPWdPUGikNGEDH1kSuKV03q7PbhXesHgFSRpelnoGTIV6gAuq3L_zxf1Ia2LX7HKseKOyE5Fc3ntVnzailwOW6z7fifrEbNAY4xFIn7A61JKlbBO134YR2zCuMy5tVM1cjU0NVU9vEXeKo2Q");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to make a call to applicationaccesstoken_service to create token",
  "keyword": "When "
});
formatter.match({
  "location": "TokenStepDef.i_want_to_make_a_call_to_applicationaccesstoken_service_to_create_token()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException: Cannot invoke method getHostAddress() on null object\r\n\tat org.codehaus.groovy.runtime.NullObject.invokeMethod(NullObject.java:91)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.NullCallSite.call(NullCallSite.java:35)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:117)\r\n\tat io.restassured.internal.RequestSpecificationImpl.applyProxySettings(RequestSpecificationImpl.groovy:2223)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1213)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1022)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:52)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:154)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:166)\r\n\tat io.restassured.internal.RequestSpecificationImpl.sendRequest(RequestSpecificationImpl.groovy:1232)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1213)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1022)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:149)\r\n\tat io.restassured.internal.filter.SendRequestFilter.filter(SendRequestFilter.groovy:30)\r\n\tat io.restassured.filter.Filter$filter$0.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.time.TimingFilter.filter(TimingFilter.java:56)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.log.RequestLoggingFilter.filter(RequestLoggingFilter.java:124)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:141)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.FilterContext$next.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:133)\r\n\tat io.restassured.internal.RequestSpecificationImpl.applyPathParamsAndSendRequest(RequestSpecificationImpl.groovy:1732)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1213)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1022)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:52)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:154)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:182)\r\n\tat io.restassured.internal.RequestSpecificationImpl.applyPathParamsAndSendRequest(RequestSpecificationImpl.groovy:1738)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1213)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1022)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:52)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:154)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:182)\r\n\tat io.restassured.internal.RequestSpecificationImpl.post(RequestSpecificationImpl.groovy:174)\r\n\tat io.restassured.internal.RequestSpecificationImpl.post(RequestSpecificationImpl.groovy)\r\n\tat Utilities.APIMethods.method_post_raw(APIMethods.java:248)\r\n\tat com.test.APIFunctions.TokenService.hitService_post(TokenService.java:149)\r\n\tat com.test.stepDefs.TokenStepDef.i_want_to_make_a_call_to_applicationaccesstoken_service_to_create_token(TokenStepDef.java:66)\r\n\tat ✽.I want to make a call to applicationaccesstoken_service to create token(features/Accounts/UJ1_Accounts.feature:18)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I want to validate application access token service Response and store the token with Status code \"200\"",
  "keyword": "Then "
});
formatter.match({
  "location": "TokenStepDef.i_want_to_validate_application_access_token_service_Response_and_store_the_token_with_Status_code(String)"
});
formatter.result({
  "status": "skipped"
});
});