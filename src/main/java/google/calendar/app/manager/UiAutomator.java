package google.calendar.app.manager;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class UiAutomator {

    public JSONArray componentJSONArray;

    public JSONArray screenDump(String pageSource) {
        componentJSONArray = new JSONArray();
        try {
            JSONObject json = XML.toJSONObject(pageSource).getJSONObject("hierarchy");
            JSONArray child = childObjectCase(json);

            for (int i = 0; i < child.length(); i++) {
                componentJSONArray.put(child.getJSONObject(i));
            }

        } catch (JSONException exception) {
            System.out.println("JSONException : " + exception.getMessage());
        }
        return componentJSONArray;
    }

    /**
     * JSONObject 에서 JSONObject 와 JSONArray 구분하여 반환.
     * **/
    private JSONArray childObjectCase(JSONObject target) {
        JSONArray result = new JSONArray();

        if (!check(target.keys(), "android.", "androidx.").isEmpty()){
            while(!check(target.keys(), "android.", "androidx.").isEmpty()) {
                String key = check(target.keys(), "android.", "androidx.");

                if (target.get(key) instanceof JSONObject) {
                    JSONArray childs = childObjectCase(target.getJSONObject(key));

                    for (int j = 0 ; j < childs.length() ; j++) {
                        result.put(childs.getJSONObject(j));
                    }
                    target.remove(key);
                } else {
                    JSONArray childs = childArrayCase(target.getJSONArray(key));
                    for (int i = 0 ; i < childs.length() ; i++) {
                        result.put(childs.getJSONObject(i));
                    }
                    target.remove(key);
                }
            }
        } else {
            result.put(target);
        }

        return result;
    }

    /**
     * JSONArray 에서 JSONObject 와 JSONArray 구분하여 반환.
     * **/
    private JSONArray childArrayCase(JSONArray target) {
        JSONArray result = new JSONArray();

        for (int i = 0 ; i < target.length() ; i++) {
            if (target.get(i) instanceof JSONObject) {
                JSONArray childs = childObjectCase(target.getJSONObject(i));

                for (int j = 0 ; j < childs.length() ; j++) {
                    result.put(childs.getJSONObject(j));
                }
            } else {
                JSONArray childs = childArrayCase(target.getJSONArray(i));

                for (int j = 0 ; j < childs.length() ; j++) {
                    result.put(childs.getJSONObject(i));
                }
            }
        }

        return result;
    }

    private String check(Iterator keys, String key1, String key2) {
        while (keys.hasNext()) {
            String str = keys.next().toString();
            if (str.contains(key1) || str.contains(key2)) {
                return str;
            }
        }
        return "";
    }

}
