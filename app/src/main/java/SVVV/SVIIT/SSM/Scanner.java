package SVVV.SVIIT.SSM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "mytag";
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    public static ArrayList<Item> item_list;
    public static float total;
    public static MyAdapter adapter;
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, "onCreate: "+e.getMessage() );
        }
        item_list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!haveCameraPermission())
                requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }
    }

    private boolean haveCameraPermission() {
        if (Build.VERSION.SDK_INT < 23)
            return true;
        return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // This is because the dialog was cancelled when we recreated the activity.
        if (permissions.length == 0 || grantResults.length == 0)
            return;

        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scannerView.setResultHandler(this);
                    scannerView.startCamera();
                } else {
                    finish();
                }
            }
            break;
        }
    }

    @Override
    public void handleResult(Result result) {
        scannerView.stopCamera();
        String item_id = result.getText();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/items/" + item_id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    final Item item = (Item) dataSnapshot.getValue(Item.class);
                    final Dialog dialog = new Dialog(Scanner.this);
                    dialog.setContentView(R.layout.item_add_dialogbox);
                    dialog.setTitle("Item Details");
                    TextView item_details = dialog.findViewById(R.id.item_detail);
                    String details = "Name:\t\t" + item.getName() +
                            "\nCompany:\t\t" + item.getCompany() +
                            "\nWeight:\t\t" + item.getWeight() +
                            "\nPrice:\t\t" + item.getPrice();
                    item_details.setText(details);
                    Button addItem = dialog.findViewById(R.id.additem);
                    Button cancelAddItem = dialog.findViewById(R.id.cancel_additem);
                    dialog.show();
                    addItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item_list.add(item);
                            total+=Float.parseFloat(item.getPrice());
                            Toast.makeText(getApplicationContext(), item.getName() + " is added to cart", Toast.LENGTH_SHORT).show();
//                            scannerView.startCamera();
                            dialog.dismiss();
                        }
                    });
                    cancelAddItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            scannerView.startCamera();
                            dialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    Log.d(TAG, "in Scanner:"+e.getMessage());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        scannerView.startCamera();
        scannerView.resumeCameraPreview(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(Scanner.this, LoginActivity.class));
            finish();
        }
        if (id == R.id.history) {
            Log.d(TAG, "onOptionsItemSelected: History");
        }
        if (id == R.id.map) {
            Log.d(TAG, "onOptionsItemSelected: Map");
        }
        if (id == R.id.cart) {
            //Log.d(TAG, "onOptionsItemSelected: View Cart");
            Intent intent = new Intent(Scanner.this, VirtualCart.class);
            intent.putExtra("total",total);
            intent.putParcelableArrayListExtra("item_list",item_list);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
