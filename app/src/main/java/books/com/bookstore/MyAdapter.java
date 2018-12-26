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

public class MyAdapter extends ArrayAdapter<Book> {

    private List<Book> items;
    int resource;

    public MyAdapter(Context context, List<Book> items) {
        super(context, R.layout.row1, items);
        this.resource = R.layout.row1;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(resource, null);
        Book emp = items.get(position);
        if (emp != null) {
            int []dest = new int[]{R.id.textView1};
            String []src = new String[]{"Category"};
            for (int n=0; n<dest.length; n++) {
                TextView txt = v.findViewById(dest[n]);
                txt.setText(emp.get(src[n]));
            }

        }
        return v;
    }
}

