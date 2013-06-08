package fr.utbm.carpooling.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {

    private ArrayList<Comment> items = null;

    public CommentAdapter(Context context, int layoutResourceId, ArrayList<Comment> data) {
        super(context, layoutResourceId, data);
        this.items = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_comment_item, parent, false);
        }

        Comment item = items.get(position);

        if (item != null) {

            ((TextView) v.findViewById(R.id.comment_item_textview_name)).setText(item.getName());
            ((TextView) v.findViewById(R.id.comment_item_textview_comment)).setText(item.getComment());

        }

        return v;
    }
}
