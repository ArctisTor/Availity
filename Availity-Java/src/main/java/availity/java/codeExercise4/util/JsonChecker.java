package availity.java.codeExercise4.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonChecker {

    /*Unfortunately this is not my own design. I found an example of this on StackOverFlow
    //Source URL:
    //https://stackoverflow.com/questions/10174898/how-to-check-whether-a-given-string-is-valid-json-in-java
     */
    public static boolean isValidJsonObject(String potential) {
        try {
            new JSONObject(potential);
        } catch (Exception e) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(potential);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
