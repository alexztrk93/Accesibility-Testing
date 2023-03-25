# Accesibility-Testing
508 Compliance for Particular Apps


==================================================================
INSTRUCTIONS FOR AUTOMATED ACCESIBILITY TESTING
==================================================================

USEFULL LINKS
https://www.youtube.com/watch?v=x8ST9a-eSFg
https://www.youtube.com/watch?v=93QDeIgSlqg&ab_channel=QAFox
https://deque.com/
https://nvaccess.org/download/
add axe plugin to chrome

==================================================================
SETUP
1)Set Selenium Automated Framework
2)add axe-selenium dependency to pom.xml
3)Visit https://github.com/dequelabs/axe-selenium-java
		-To run example
4)add axe.min.js to src/test/resources of the framework


Follow The Script

 @Test
    public void amazonAllyTest() {

        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();//analyze is overloaded we can check particular items .analyze(findbyThe Weblement)
        JSONArray violations = responseJson.getJSONArray("violations");
        if (violations.length() == 0) {
            System.out.println("========NO VIOLATIONS=========");
        } else {
            AXE.writeResults("amazonAllyTest", responseJson);
            Assert.assertTrue(false, AXE.report(violations));

        }
