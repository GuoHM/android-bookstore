package books.com.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        new AsyncTask<Void, Void, List<Books>>() {
            @Override
            protected List<Books> doInBackground(Void... params) {
                return Books.ReadCategory();
            }
            @Override
            protected void onPostExecute(List<Books> result) {
                books.com.bookstore.MyAdapter adapter = new books.com.bookstore.MyAdapter(getApplicationContext(),
                        result);
                ListView list = (ListView) findViewById(R.id.listView1);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Books selected = (Books) parent.getAdapter().getItem(position);
                        Intent intent = new Intent(getApplicationContext(), CategoryBooksList.class);
                        intent.putExtra("id", selected.get("Category"));
                        startActivity(intent);
                    }
                });
            }
        }.execute();

    }
}
