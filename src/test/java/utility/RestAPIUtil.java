package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

public class RestAPIUtil {
    Logger logger = Logger.getLogger(RestAPIUtil.class.getName());
    String expectedDate;

    public Boolean isTemperatureWarmOnThursday(Response weatherResponse, int degrees) {
        boolean success = false;
        ArrayList<Float> minimumTemperatureOnThursday = new ArrayList<>();
        float floatVal;
        int i;
        JsonPath extractor = weatherResponse.jsonPath();
        //Get date and time
        List<String> test = extractor.getList("list.dt_txt");
        //Get minimum temperature
        List<String> minimumTemeratures = extractor.getList("list.main.temp_min");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (i = 0; i < test.size(); i++) {
            LocalDate localDate = LocalDate.parse(test.get(i).substring(0, 10), formatter);
            String day = localDate.getDayOfWeek().toString();
            //If it is Thursday then get the temperature and verify the minimum temperature is greater than 10 degrees
            if (day.equalsIgnoreCase("thursday")) {
                logger.info("Got the Temperature for Thursday from API Response.");
                expectedDate = test.get(i).substring(0, 10);
                floatVal = new Float(String.valueOf(minimumTemeratures.get(i)));
                minimumTemperatureOnThursday.add(floatVal);
                if (floatVal > (float) degrees) {
                    System.out.println("Temperature on " + expectedDate + " is " + floatVal + " degrees");
                    success = true;
                }
            }
        }
        return success;
    }
}
