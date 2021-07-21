*****WooliesX Tech Challenge*****

*****Steps to set up locally*****

*****Prerequisites*****
1. Install Java and set JAVA_HOME path
2. Install Maven and set MVN_HOME

*****How to run the script*****
1. Clone the Repo
2. Open Command line
3. Navigate to the "WooliesX" folder location
4. Execute UI Test by entering below command

   mvn clean verify -Dcucumber.filter.tags="@SmokeTest"

5. Execute API Test by entering below command

   mvn clean verify -Dcucumber.filter.tags="@APITest"
   
 *****Reports for UI/API Tests*****
Once the test execution completed, proceed to the below path for the Reports
    \WooliesX\target\cucumber-reports.html
   
 *****Tech Stack for UI/API tests*****

* Test Cases(features) - Cucumber BDD
* Test Framework - Junit
* Locators - css and xpath
* Reporting - Cucumber Reporting



 
