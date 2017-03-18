package edu.ucsb.cs.cs185.bryannaphan.fundguy;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by bryannaphan on 2/25/17.
 * image adapter turns data (bitmap) into an image view
 */

public class ItemAdapter extends BaseAdapter {
    private Context context;

    public ItemAdapter(Context context, Boolean isList) {
        super();
        this.context = context;
        //this.isList = isList;
        ItemManager.getInstance().setListener(new ItemManager.ItemManagerListener() {
            @Override
            public void onUpdate() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.single_item_title);
        TextView amount  = (TextView) convertView.findViewById(R.id.single_item_amount);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView category = (TextView) convertView.findViewById(R.id.single_item_category);
        //ImageView iv = (ImageView) convertView.findViewById(R.id.test_view);

        //iv.setImageBitmap(ItemManager.getInstance().get(position).getBitmap());
        date.setText(ItemManager.getInstance().get(position).getDate());
        title.setText(ItemManager.getInstance().get(position).getTitle());
        amount.setText(String.format("%.2f", ItemManager.getInstance().get(position).getAmount()));
        category.setText(ItemManager.getInstance().get(position).getCategory());
        return convertView;
    }

    // Returns the size of the list
    @Override
    public int getCount() {
        return ItemManager.getInstance().getSize();
    }

    // Returns an item from the list that is at the passed in position
    @Override
    public Item getItem(int position) {
        // Get it to refer to the BitmapManager
        return ItemManager.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Item item, Uri uri) {
        // get it to refer to the BitmapManager
        ItemManager.getInstance().add(item);
    }

}
