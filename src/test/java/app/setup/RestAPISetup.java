package app.setup;

import io.restassured.response.Response;
import utility.App;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RestAPISetup {
    Logger logger = Logger.getLogger(RestAPISetup.class.getName());
    public Response response;
    public String url = "";

    public String getUrl() {
        url = App.getProperty("base.uri") + App.getProperty("resource") + "id=" + App.getProperty("sydneyCityId") + "&appid=" + App.getProperty("accessKey") + "&units=metric";
        logger.info("Url : " + url);
        return url;
    }

    public Response getResponse(String uri) {
        response = given().when().get(uri);
        logger.info("Response is : " + response.asString());
        return response;
    }
}
