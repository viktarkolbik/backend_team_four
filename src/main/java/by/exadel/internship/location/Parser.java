package by.exadel.internship.location;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
public class Parser {
     public static void parseTo(){
         try {
             JSONParser jsonParser = new JSONParser();
             JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("by/exadel/internship/location/world-cities_json.json"));
             JSONArray countryList = (JSONArray) jsonObject.get("country");
             Iterator<JSONObject> iterator = countryList.iterator();
             while (iterator.hasNext()){
                 System.out.println(iterator.next());
             }

         } catch (ParseException | IOException e) {
             log.error(e.getMessage());
         }
     }
}
