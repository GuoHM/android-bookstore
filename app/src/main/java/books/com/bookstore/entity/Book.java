package books.com.bookstore.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import books.com.bookstore.util.JSONParser;

public class Book extends HashMap<String, String> implements Serializable {
    static String host = "172.23.226.30";
    static String baseURL;
    static String imageURL;

    static {
        baseURL = String.format("http://%s/Books/api/Books", host);
        imageURL = String.format("http://%s/Books/images", host);
    }

    public Book(String bookID, String bookTitle, String author, String iSBN, String category, String quantity, String price, String publisher) {
        put("bookID", bookID);
        put("bookTitle", bookTitle);
        put("author", author);
        put("iSBN", iSBN);
        put("category", category);
        put("quantity", quantity);
        put("price", price);
        put("publisher", publisher);
    }
    
    public static Bitmap getPhoto(String id, boolean thumbnail) {
        try {
            URL url = new URL(thumbnail ?
                    String.format("%s/%s-s.jpg", imageURL, id) :
                    String.format("%s/%s.jpg", imageURL, id));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return (null);
    }

    public static void saveBook(Book book) {
        JSONObject jemp = new JSONObject();
        try {
            jemp.put("bookID", book.get("bookID"));
            jemp.put("bookTitle", book.get("bookTitle"));
            jemp.put("author", book.get("author"));
            jemp.put("iSBN", book.get("iSBN"));
            jemp.put("category", book.get("category"));
            jemp.put("quantity", book.get("quantity"));
            jemp.put("price", book.get("price"));
            jemp.put("publisher", book.get("publisher"));
        } catch (Exception e) {
            Log.e("Book.saveBook()", "Bitmap error");
        }
        JSONParser.postStream(baseURL + "/update", jemp.toString());
    }

    public static List<Book> ReadCategory() {
        ArrayList<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL+"/Category");
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("bookID"),
                        b.getString("bookTitle"),
                        b.getString("author"),
                        b.getString("iSBN"),
                        b.getString("category"),
                        b.getString("quantity"),
                        b.getString("price"),
                        b.getString("publisher")));
            }
        } catch (Exception e) {
            Log.e("Employee", "JSONArray error");
        }
        return (list);
    }

    public static List<Book> ListBooksCategory(String id) {
        ArrayList<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "/" + id);
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("bookID"),
                        b.getString("bookTitle"),
                        b.getString("author"),
                        b.getString("iSBN"),
                        b.getString("category"),
                        b.getString("quantity"),
                        b.getString("price"),
                        b.getString("publisher")));
            }
        } catch (Exception e) {
            Log.e("Books", "JSONArray error");
        }
        return (list);
    }
}


