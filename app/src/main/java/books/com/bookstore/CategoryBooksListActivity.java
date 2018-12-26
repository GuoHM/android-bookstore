package books.com.bookstore;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import books.com.bookstore.entity.Book;

public class CategoryBooksListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_books_list);
        Bundle Intent = getIntent().getExtras();
        String Category = Intent.getString("id");

        new AsyncTask<String, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(String... params) {
                return Book.ListBooksCategory(params[0]);
            }

            @Override
            protected void onPostExecute(List<Book> result) {
                books.com.bookstore.MyAdapter adapter = new books.com.bookstore.MyAdapter(getApplicationContext(), result);
                ListView list = (ListView) findViewById(R.id.listView1);
                list.setAdapter(adapter);
            }
        }.execute(Category);

    }
}
