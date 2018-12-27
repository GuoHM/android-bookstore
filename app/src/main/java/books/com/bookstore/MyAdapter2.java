package books.com.bookstore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import books.com.bookstore.entity.Book;

public class MyAdapter2 extends ArrayAdapter<Book> {

    int resource;
    private List<Book> items;


    public MyAdapter2(Context context, List<Book> items) {
        super(context, R.layout.row2, items);
        this.resource = R.layout.row2;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(resource, null);
        Book emp = items.get(position);

        if (emp != null) {
            int[] dest = new int[]{R.id.textView2, R.id.textView3};
            String[] src = new String[]{"BookTitle", "BookId"};
            for (int n = 0; n < dest.length; n++) {
                TextView txt = v.findViewById(dest[n]);
                txt.setText(emp.get(src[n]));
            }

        }
        return v;
    }
}
