package Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.*;

/**
 *
 * @author salee
 */
public class JSONUtils {
    /**
     * Converts HashMap to JSON string with key-value pairs
     * @param dataMap is a List of HashMap objects
     * @return String JSON
     */
    public static String hashMapToJson(List<HashMap<String, Object>> dataMap){
        List<JSONObject> jsonArray  = new ArrayList<>();
        dataMap.stream().map((data) -> new JSONObject(data)).forEach((jsonObject) -> {
            jsonArray.add(jsonObject);
        });
        return jsonArray.toString();
    }
}