package books.com.bookstore;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import books.com.bookstore.entity.Book;

public class DisplayBookActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_book);
        final Intent i = new Intent(this, UpdateActivity.class);
        Bundle Intent = getIntent().getExtras();
        String Category = Intent.getString("id");
        new AsyncTask<String, Void, Book>() {
            @Override
            protected Book doInBackground(String... params) {
                return Book.DisplayBook(params[0]);
            }

            @Override
            protected void onPostExecute(Book result) {

                show(result);
            }
        }.execute(Category);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] dest = new int[]{R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8};
                String[] b = new String[8];
                for (int n = 0; n < dest.length; n++) {
                    TextView txt = (TextView) findViewById(dest[n]);
                    b[n] = txt.getText().toString();
                }
                Book bok = new Book(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7]);
                i.putExtra("book", bok);
                startActivity(i);
            }
        });
    }

    void show(Book book) {
        int[] dest = new int[]{R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8};
        String[] src = new String[]{"BookID", "BookTitle", "Author", "ISBN", "Category", "Quantity", "Price", "Publisher"};
        for (int n = 0; n < dest.length; n++) {
            TextView txt = (TextView) findViewById(dest[n]);
            txt.setText(book.get(src[n]));
        }


    }


}
