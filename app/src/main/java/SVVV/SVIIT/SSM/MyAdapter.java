package SVVV.SVIIT.SSM;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private static final String TAG = "mytag";
    private Context context;
    private ArrayList<Item> item_list;

    public MyAdapter(Context context, ArrayList<Item> item_list) {
        this.context = context;
        this.item_list = item_list;
    }

    @Override
    public int getCount() {
        return Scanner.item_list.size();
    }

    @Override
    public Object getItem(int i) {
        return Scanner.item_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.custom_itemlist_layout, viewGroup, false);
        }
        try {
            Item item = Scanner.item_list.get(i);
            String text = item.getName()+"................price: "+ item.getPrice();
            //Log.d(TAG, "getView: "+text);
            TextView item_details = view.findViewById(R.id.item_details);
            item_details.setText(text);
            TextView item_sno = view.findViewById(R.id.item_sno);
            int sno = i+1;
            //Log.d(TAG, "getView: "+sno);
            item_sno.setText(Integer.toString(sno));
            ImageButton delete_item = view.findViewById(R.id.item_delete);
            delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refresh(i);
                    //item_list.remove(i);
                    Scanner.item_list.remove(i);
                }
            });
        }catch (Exception e){
            Log.e(TAG, "getView: "+e.getMessage());
        }
        return view;
    }


    public void refresh(int i){
        this.notifyDataSetChanged();
        Scanner.total-=Float.parseFloat(Scanner.item_list.get(i).getPrice());
        VirtualCart.totaltv.setText("TOTAL:\t"+String.valueOf(Scanner.total));
    }
}
