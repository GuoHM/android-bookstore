package books.com.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import books.com.bookstore.entity.Book;

public class ListBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books2);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
    }
}
