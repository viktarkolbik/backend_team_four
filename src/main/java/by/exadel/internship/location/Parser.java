//package by.exadel.internship.location;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.text.StringEscapeUtils;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//@Slf4j
//public class Parser {
//
//    public static Map<Object, Set<Object>> getCountriesAndCitiesFromJSON() {
//        Map<Object, Set<Object>> locationMap = new TreeMap<>();
//        try {
//            Object obj = new JSONParser().parse(new FileReader("src/main/java/by/exadel/internship/location/world-cities_json.json"));
//            JSONArray jsonObjectList = (JSONArray) obj;
//            for (Object o : jsonObjectList) {
//                JSONObject jsonObject = (JSONObject) o;
//                Object country = jsonObject.get("country");
//                Object city = jsonObject.get("name");
//                if (!locationMap.containsKey(country)) {
//                    locationMap.put(country, new TreeSet<>());
//                }
//                locationMap.get(country).add(city);
//            }
//        } catch (ParseException | IOException e) {
//            log.error(e.getMessage());
//        }
//        return locationMap;
//    }
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/internship";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "mama071991";
//
//
//    public static void executeUpdate(String sql) throws SQLException {
//        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        if (conn == null)
//            throw new SQLException("Connection null!");
//
//        Statement statement = conn.createStatement();
//        statement.executeUpdate(sql);
//        statement.close();
//        conn.close();
//    }
//
//    public static void putDataToDatabase() {
//
//        try {
//            for (Map.Entry<Object, Set<Object>> entry : getCountriesAndCitiesFromJSON().entrySet()) {
//                executeUpdate("INSERT INTO country (cntr_id, cntr_name) VALUES(uuid_generate_v4(),'" + entry.getKey() + "'); ");
//                for (Object city : entry.getValue()) {
//
//                    executeUpdate("INSERT INTO city (city_id, city_name, city_cntr_id) " +
//                            "VALUES(uuid_generate_v4(),'" + city.toString().replaceAll("'","\\'") + "'," + "(SELECT cntr_id FROM country WHERE cntr_name = '"+entry.getKey()+ "')); ");
//                }
//            }
//        } catch (SQLException e) {
//            log.error(e.getMessage());
//        }
//    }
//}

