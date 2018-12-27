package books.com.bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import books.com.bookstore.entity.Book;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        HashMap<String, String> book = (HashMap<String, String>) intent.getSerializableExtra("book");
        final EditText txt1 = findViewById(R.id.upeditText);
        final EditText txt2 = findViewById(R.id.upeditText2);
        final EditText txt3 = findViewById(R.id.upeditText3);
        final EditText txt4 = findViewById(R.id.upeditText4);
        final EditText txt5 = findViewById(R.id.upeditText5);
        final EditText txt6 = findViewById(R.id.upeditText6);
        final EditText txt7 = findViewById(R.id.upeditText7);
        final EditText txt8 = findViewById(R.id.upeditText8);
        txt1.setText(book.get("BookID"));
        txt2.setText(book.get("BookTitle"));
        txt3.setText(book.get("Author"));
        txt4.setText(book.get("ISBN"));
        txt5.setText(book.get("Category"));
        txt6.setText(book.get("Quantity"));
        txt7.setText(book.get("Price"));
        txt8.setText(book.get("Publisher"));
        Button btn = findViewById(R.id.upbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEdit();
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setMessage("Update successfully").setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });


    }

    void saveEdit() {
        int[] src = new int[]{R.id.upeditText, R.id.upeditText2, R.id.upeditText3, R.id.upeditText4, R.id.upeditText5, R.id.upeditText6, R.id.upeditText7, R.id.upeditText8};
        String[] dest = new String[8];
        for (int n = 0; n < dest.length; n++) {
            EditText txt = findViewById(src[n]);
            dest[n] = txt.getText().toString();
        }
        Book book = new Book(dest[0], dest[1], dest[2], dest[3], dest[4], dest[5], dest[6], dest[7]);
        new AsyncTask<Book, Void, Void>() {
            @Override
            protected Void doInBackground(Book... params) {
                Book.saveBook(params[0]);
                return null;
            }
        }.execute(book);
    }
}
