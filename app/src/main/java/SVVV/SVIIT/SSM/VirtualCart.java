package SVVV.SVIIT.SSM;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class VirtualCart extends AppCompatActivity {

    private static final String TAG = "mytag";
    private static ListView myCartView;
   // private static MyAdapter adapter;
    private Button done;
    public static TextView totaltv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_cart);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, "onCreate: "+e.getMessage() );
        }
        totaltv= findViewById(R.id.total);
        done = findViewById(R.id.confirm);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VirtualCart.this,FinalActivity.class));
                finish();
            }
        });
        try {
            //item_list = getIntent().getParcelableArrayListExtra("item_list");
            //this.total = getIntent().getFloatExtra("total",0f);
            totaltv.setText("TOTAL:\t"+String.valueOf(Scanner.total));
            if(Scanner.item_list!=null){
                //
            }
            else{
                Log.d(TAG, "onCreate: item_list is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "onCreate: "+e.getMessage());
        }
        if(myCartView == null)
            myCartView = findViewById(R.id.mycart);

        //if Scanner.adapter is null code missing
        Scanner.adapter = new MyAdapter(VirtualCart.this,Scanner.item_list);
        myCartView.setAdapter(Scanner.adapter);


        myCartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDetails(i);
            }
        });
    }

    private void showDetails(int i) {
        Item item = Scanner.item_list.get(i);
        final Dialog dialog = new Dialog(VirtualCart.this);
        dialog.setContentView(R.layout.item_add_dialogbox);
        dialog.setTitle(item.getName());
        TextView item_detail = dialog.findViewById(R.id.item_detail);
        String detail = "Name:\t\t" + item.getName() +
                "\nCompany:\t\t" + item.getCompany() +
                "\nWeight:\t\t" + item.getWeight() +
                "\nPrice:\t\t" + item.getPrice();
        item_detail.setText(detail);
        Button okay = dialog.findViewById(R.id.additem);
        Button cancel = dialog.findViewById(R.id.cancel_additem);
        okay.setText("OKAY");
        cancel.setVisibility(View.GONE);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(VirtualCart.this, LoginActivity.class));
            finish();
        }
        if (id == R.id.history) {
            Log.d(TAG, "onOptionsItemSelected: History");
        }
        if (id == R.id.map) {
            Log.d(TAG, "onOptionsItemSelected: Map");
        }
        if (id == R.id.cart) {
         // Toast.makeText()
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.removeItem(R.id.cart);
        return super.onCreateOptionsMenu(menu);
    }
}
