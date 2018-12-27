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
    static String host = "172.17.194.124";
    static String baseURL;
    static String imageURL;

    static {
        baseURL = String.format("http://%s/Books/api/Books", host);
        imageURL = String.format("http://%s/Books/images", host);
    }

    public Book(HashMap<String, String> book){
        put("BookID", book.get("BookID"));
        put("BookTitle", book.get("BookTitle"));
        put("Author", book.get("Author"));
        put("ISBN", book.get("ISBN"));
        put("Category", book.get("Category"));
        put("Quantity", book.get("Quantity"));
        put("Price", book.get("Price"));
        put("Publisher", book.get("Publisher"));
    }

    public Book(String bookID, String bookTitle, String author, String ISBN, String category, String quantity, String price, String publisher) {
        put("BookID", bookID);
        put("BookTitle", bookTitle);
        put("Author", author);
        put("ISBN", ISBN);
        put("Category", category);
        put("Quantity", quantity);
        put("Price", price);
        put("Publisher", publisher);
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
            jemp.put("BookID", book.get("BookID"));
            jemp.put("BookTitle", book.get("BookTitle"));
            jemp.put("Author", book.get("Author"));
            jemp.put("ISBN", book.get("ISBN"));
            jemp.put("Category", book.get("Category"));
            jemp.put("Quantity", book.get("Quantity"));
            jemp.put("Price", book.get("Price"));
            jemp.put("Publisher", book.get("Publisher"));
        } catch (Exception e) {
            Log.e("Book.saveBook()", "Bitmap error");
        }
        JSONParser.postStream(baseURL + "/update", jemp.toString());
    }

    public static List<Book> ReadCategory() {
        ArrayList<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "/Category");
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book("",
                        "",
                        "",
                        "",
                        b.getString("Category"),
                        "",
                        "",
                        ""));
            }
        } catch (Exception e) {
            Log.e("Employee", "JSONArray error");
        }
        return (list);
    }

    public static Book DisplayBook(String id) {
        try {
            JSONObject b = JSONParser.getJSONFromUrl(baseURL + "/Details/" + id);
            //Book e = new Book(String.format("%f", a.getInt("BookID")), a.getString("BookTitle"),
            //      a.getString("Author"), a.getString("ISBN"), a.getString("Category"), String.format("%f", a.getInt("Quantity")), a.getString("Publisher"), String.format("%,.2f", a.getDouble("Price")));

            Book e = new Book(b.getString("BookID"),
                    b.getString("BookTitle"),
                    b.getString("Author"),
                    b.getString("ISBN"),
                    b.getString("Category"),
                    b.getString("Quantity"),
                    b.getString("Price"),
                    b.getString("Publisher"));
            return e;
        } catch (Exception e) {
            Log.e("Books", "JSONArray error");
        }
        return (null);
    }

    public static List<Book> ListBooksCategory(String id) {
        ArrayList<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "/" + id);
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                String bookId = "" + b.get("BookId");
                list.add(new Book(bookId,
                        b.getString("Title"),
                        "",
                        "",
                        b.getString("Category"),
                        "",
                        "",
                        ""));
            }
        } catch (Exception e) {
            Log.e("Books", "JSONArray error");
        }
        return (list);
    }
}


