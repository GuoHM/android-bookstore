package books.com.bookstore;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import books.com.bookstore.entity.Book;

public class CategoryBooksListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_books_list);

        final Bundle Intent = getIntent().getExtras();
        String Category = Intent.getString("id");

        new AsyncTask<String, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(String... params) {
                return Book.ListBooksCategory(params[0]);
            }
            @Override
            protected void onPostExecute(List<Book> result) {
                books.com.bookstore.MyAdapter2 adapter = new books.com.bookstore.MyAdapter2(getApplicationContext(),
                        result);
                //adapter.resource = R.layout.row2;

                //SimpleAdapter adapt = new SimpleAdapter(this,result,new String[]{"Title","BookId"},
                // new int[]{R.id.textView2,R.id.textView3});
                ListView list = (ListView) findViewById(R.id.listView2);
                // ListView t =(ListView) findViewById(R.id.listView1);
                // t.setAdapter(new SimpleAdapter(this,result,R.layout.row2,new String[]{"BookId", "BookTitle"},new int[]{R.id.textView2,R.id.textView3}));

                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Book selected = (Book) parent.getAdapter().getItem(position);
                        Intent intent = new Intent(getApplicationContext(), DisplayBookActivity.class);
                        intent.putExtra("id", selected.get("BookID"));
                        startActivity(intent);
                    }
                });
            }
        }.execute(Category);

    }
}
