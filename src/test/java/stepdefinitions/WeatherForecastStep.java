package stepdefinitions;

import app.setup.RestAPISetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utility.RestAPIUtil;

import java.util.logging.Logger;

public class WeatherForecastStep {
    Logger logger = Logger.getLogger(WeatherForecastStep.class.getName());

    public String url = "";
    public Response weatherResponse;
    RestAPISetup restAPISetup = new RestAPISetup();
    RestAPIUtil restAPIUtil = new RestAPIUtil();

    @Given("I like to holiday in {string}")
    public void i_like_to_holiday_in(String city) {
        logger.info("Holiday planned in : " + city);
        url = restAPISetup.getUrl();
        weatherResponse = restAPISetup.getResponse(url);
        String actualCity = weatherResponse.jsonPath().getString("city.name").toString();
        Assert.assertEquals("Assertion Failed: Incorrect City name", city, actualCity);
    }

    @Given("I only like to holiday on {string}")
    public void i_only_like_to_holiday_on(String day) {
        logger.info("Let's plan holiday on " + day);
    }

    @When("I look up the weather forecast")
    public void i_look_up_the_weather_forecast() {
        logger.info("Verify status of the request ");
        int code = weatherResponse.statusCode();
        Assert.assertEquals("Assertion Failed: Response code failed to display 200", 200, code);
    }

    @Then("I receive the weather forecast")
    public void i_receive_the_weather_forecast() {
        url = restAPISetup.getUrl();
        weatherResponse = restAPISetup.getResponse(url);
        logger.info("Response is : " + weatherResponse.asString());
    }

    @Then("the temperature is warmer than {int} degrees")
    public void the_temperature_is_warmer_than_degrees(int degrees) {
        boolean isTemperatureWarm = restAPIUtil.isTemperatureWarmOnThursday(weatherResponse, degrees);
        Assert.assertTrue("Assertion Failed: It's cold on Thursday", isTemperatureWarm);

    }

}

