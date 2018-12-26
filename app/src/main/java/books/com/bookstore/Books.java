package books.com.bookstore;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Books extends HashMap<String, String> {
    static String host = "172.23.226.30";
    static String baseURL;
    static String baseURL2;

    static {
        baseURL = String.format("http://%s/Books/api/Books/Category", host);
        baseURL2 =  String.format("http://%s/Books/api/Books", host);

    }
    public Books(String id) {
        put("Category", id);
    }


    public Books(String id,String title,String authour,String price)
    {
        put("Category", id);
        put("Title", title);
        put("Author", authour);
        put("Price", price);

    }

    public static List<Books> ReadCategory() {
        ArrayList<Books> list = new ArrayList<Books>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL);
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Books(b.getString("Category")));
            }
        } catch (Exception e) {
            Log.e("Employee", "JSONArray error");
        }
        return (list);
    }

    public static List<Books> ListBooksCategory(String id) {
        ArrayList<Books> list = new ArrayList<Books>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL2 + "/" + id);
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Books(b.getString("Title")));
            }
        } catch (Exception e) {
            Log.e("Books", "JSONArray error");
        }
        return (list);
    }
}

