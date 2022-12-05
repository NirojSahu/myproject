#!/usr/bin/env groovy
def createEmailSendingList() {
    if("true"=="${send_emailtoAll}") {
        emailfinalList=emailmultipleList
    } else {
        emailfinalList=emailsingleList
    }
    echo "$emailfinalList"
}

def printTargetFolder() {
    sh 'ls -la target'
}

pipeline {

    agent { label 'maven' }
    parameters {
        string(name: 'Host', defaultValue: 'https://jenkins-automationqatesting-dev.appls-ukdev01a.paas.santanderuk.dev.corp/job/IntenSFI/job/Set5/', description: 'Server & Job Details')
        string(name: 'EnvironmentToExecute', defaultValue: 'https://webapiuk-taassel-pre.appls-ukpre03a.paas.santanderuk.pre.corp', description: 'Selenium Hub where to execute')
        string(name: 'ExecutingTag', defaultValue: '@SET5', description: 'Tag through which execution need to be done')
        string(name: 'Browser', defaultValue: 'chrome', description: 'Browser on which execution is required')
        booleanParam(defaultValue: false, description: 'if unticked - it will not send any notification to JIRA or Kafka. No emails, No rerun too', name: 'send_notification')
        booleanParam(defaultValue: false, description: 'if unticked- it will send email/s to one pre-configured member only. Emails will not be sent to all recipients', name: 'send_emailtoAll')
    }

    options { buildDiscarder(logRotator(numToKeepStr: '5')) }

    environment {
        check_rerun = false
////        emailsingleList = 'pau.baezaroman@santander.co.uk;Bhavyashree.VenkateshMurthy@santander.co.uk;Mohmed.Yaseen@santander.co.uk;Sridhar.Subbarayan@santander.co.uk;shailesh.rajjoshi@santander.co.uk'
        emailsingleList = 'shivangi.nigam@santander.co.uk;letty.thomas@santander.co.uk;vinitha.baby@santander.co.uk;Puneeth.VelloreLokanathan@santander.co.uk;Prateek.Gupta@santander.co.uk;ugesh.kadiyala@santander.co.uk'
////        emailmultipleList = 'Vignesh.PathrerHaleshappa@santander.co.uk;Sridhar.Subbarayan@santander.co.uk;Mohammed.Ahmed5@santander.co.uk'
//        emailmultipleList = 'shailesh.rajjoshi@santander.co.uk'
        emailfinalList = ' '
    }


    stages {


        stage('Clean & Build ') {
            steps {
                createEmailSendingList()
                //bat returnStatus: true, script: 'call C:\\Users\\E0607001\\.jenkins\\workspace\\Experimental\\copy_buildretial_pipeliner\\Automation_Framework_Web\\Curlcall.bat'
                echo "${Host}"
                echo "${BUILD_NUMBER}"
                echo 'check rerun'
                echo "${check_rerun}"
                echo 'send notification flag'
                echo "${send_notification}"
                echo "${EnvironmentToExecute}"
                sh "ls -ltr"
//                zip zipFile: 'features.zip', dir: "${WORKSPACE}/src/test/resources/features"
//                sh 'curl -k -H "Content-Type: multipart/form-data" -u s0811959:r64jKe9P3u4H -F "file=@features.zip" https://uk-jira.almuk.santanderuk.corp/rest/raven/1.0/import/feature?projectKey=CQAF'
                //checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'f42faece-a800-40a4-a1f0-c71377ddfdd1', url: 'https://uk-gitlab.almuk.santanderuk.corp/Lynx-Transformation/Automation_Framework.git']]])
                sh 'mvn clean compile -U -Dmaven.test.skip=true -X'
            }
        }

        stage(' Test Execution and Report Generation') {
            steps {
                script {
                    try {
                        echo "${Host}"
                        echo "$EnvironmentToExecute"
                        echo 'Test Execution and Report Generation'
                        sh 'mvn test -f pom.xml -Dgrid.run=true -Dgrid.webservice=${EnvironmentToExecute} -Dtoken=taasToken-wckgsixspc -Dbrowser="${Browser}" -Dapplicationtype=intranet -Dbrowserversion="78.0.3904.87" -Dcucumber.options=". --tags ${ExecutingTag}"'
                        sh 'sleep 5'
                        cucumber fileIncludePattern: '**/cucumber-report.json', jsonReportDirectory: 'target', sortingMethod: 'ALPHABETICAL'
//                        dir("${WORKSPACE}/src/test/resources/TestData/MortgageDataSheets/") {
//                            fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: 'ReportandChange_Decision.xlsx', targetLocation: "C:/Users/Default/Desktop")])
//                        }
                    }

                    catch (e) {
                        echo 'in catching block'
                        sh 'sleep 5'
                        cucumber fileIncludePattern: '**/cucumber-report.json', jsonReportDirectory: 'target', sortingMethod: 'ALPHABETICAL'
//                        sh("mv ${WORKSPACE}/target/rerun.txt ${WORKSPACE}/target/rerunnew.txt")
                        printTargetFolder()
                        echo 'some issue in running Test or generating cucumber Report' + e.toString()
                        echo "${check_rerun}"
                    }
                }


            }
        }
        stage('Send To Kafka') {

            steps {

                script {

                    try {

                        echo 'Sending report to Kafka'
                        def KAFKASERVER = "kafka.loganalytics.santanderuk.corp:6667"
                        def KAFKATOPIC = "PRO_TAAS_FUNC_LOG"
                        def PROJECTNAME = "IntenSFI"
                        //def ENVIRONMENT = "PRE"
                        def ENVIRONMENT = "${EnvironmentToExecute}"
                        def PLATFORM = "RETAIL"
                        def RELEASE = "Release"
                        def SPRINTNAME = "Sprint"
                        def WORKAREA = "portal"
                        def PROGRAM = "Program"

                        //def JENKINSBUILDURL ="${Host}"+"job/"+"${JOB_NAME}"+"/"+"${BUILD_NUMBER}"

                        def JENKINSBUILDURL = "${Host}${BUILD_NUMBER}"

                        def ORIGIN = "Remote"

                        def UTILPATH = "target/kafkautil.jar"

                        def REPORTPATH = "/target/cucumber-report.json"
                        def GITURL = "https://uk-gitlab.almuk.santanderuk.corp/C0261406/safi-automation"

                        sh "java -jar target/kafkautil.jar ${KAFKASERVER} ${KAFKATOPIC} ${PROJECTNAME} ${ENVIRONMENT} ${PLATFORM} ${RELEASE} ${SPRINTNAME} ${WORKAREA} ${PROGRAM} ${JENKINSBUILDURL} ${ORIGIN} ${REPORTPATH} ${GITURL}"
                    }
                    catch (e) {
                        error 'some issue with sending reports to kafka' + e.toString()
                    }
                }
            }
        }

//        stage('Capture Screenshot & Move to Workspace') {
//            when {
//                expression {
////                    echo "${check_rerun}"
//                    return (params.send_notification)
//                }
//
//            }
//            steps {
//                script {
//                    try {
//                        echo 'Capture screenshot and move to workspace STAGE STARTING'
//
////                        sh 'curl -k -u s0811959:r64jKe9P3u4H -F info=@JIRAXRAYInfoFiles/bdpInfo.json -F result=@target/cucumber-report.json https://uk-jira.almuk.santanderuk.corp/rest/raven/1.0/import/execution/cucumber/multipart'
////                        sh 'mvn test -f pom.xml -Dgrid.run=true -Dgrid.webservice=${EnvironmentToExecute} -Dtoken=taasToken-mvkdkdadki -Dapplicationtype=internet -Dbrowser="firefox" -Dbrowserversion="67.0.1" -DBuildNo=${BUILD_NUMBER} -DHost=${Host} -Dcucumber.options=". --tags @customised_screenshot"'
//                        sh 'mvn test -f pom.xml -Dgrid.run=true -Dgrid.webservice=${EnvironmentToExecute} -Dtoken=taasToken-wckgsixspc -Dapplicationtype=internet -Dbrowser="chrome" -Dbrowserversion="76.0.3809.87" -DBuildNo=${BUILD_NUMBER} -DHost=${Host} -Dcucumber.options=". --tags @customised_screenshot"'
//                        sh("mv '${WORKSPACE}/target/Screenshots/FeatureReport.png' '${WORKSPACE}'")
//                        sh("mv '${WORKSPACE}/target/Screenshots/TagsReport.png' '${WORKSPACE}'")
//
//                    } catch (e) {
//                        error 'some error to Capture Screenshot & Move to Workspace' + e.toString()
//                    }
//                }
//
//
//            }
//        }
//
        stage('Send Email to intended recipients') {
            when {
                expression {
                    //echo "${check_rerun}"
                    return (params.send_notification)
                }

            }

            steps {

                script {
                    try {
                        echo 'normal execution in second stage written like script'
                        String ReportLink = "/cucumber-html-reports/overview-features.html"
                        String jenkinsLink = "${Host}${BUILD_NUMBER}${ReportLink}"
                        echo "$jenkinsLink"
                        echo "${EnvironmentToExecute}"
                        echo "${WORKSPACE}"
                        String emailBody = "Job Name - ${JOB_NAME}         <br>\n" +
                                "Build Number - ${BUILD_NUMBER} <br>\n" +
                                "Test Executed on - ${EnvironmentToExecute} <br>\n " +
                                "to watch the detailed report, click on Jenkins Url - <br>\n" + "$jenkinsLink"
                        emailext attachmentsPattern: 'ReportandChange_Decision.xlsx',
                                body: emailBody,
                                subject: 'JOBNAME- ${JOB_NAME} BUILDNO-${BUILD_NUMBER}', to: "$emailfinalList"
                    } catch (e) {
                        echo 'some issue in Send Email but Invoke Rerun successful. Rerun will be executed unless stopped intentionally'

                    }
                }
            }
        }
    }
}