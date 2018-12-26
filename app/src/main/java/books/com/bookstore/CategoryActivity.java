package books.com.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import books.com.bookstore.entity.Book;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... params) {
                return Book.ReadCategory();
            }
            @Override
            protected void onPostExecute(List<Book> result) {
                books.com.bookstore.MyAdapter adapter = new books.com.bookstore.MyAdapter(getApplicationContext(),
                        result);
                ListView list = (ListView) findViewById(R.id.listView1);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Book selected = (Book) parent.getAdapter().getItem(position);
                        Intent intent = new Intent(getApplicationContext(), CategoryBooksListActivity.class);
                        intent.putExtra("id", selected.get("Category"));
                        startActivity(intent);
                    }
                });
            }
        }.execute();

    }
}
