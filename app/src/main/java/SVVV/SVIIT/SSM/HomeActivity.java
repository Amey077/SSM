package SVVV.SVIIT.SSM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button button,button1;
    private static final String TAG = "mytag";
    private static final String CAMERA_UNAVAILABLE_ERROR = "Camera Unavailable at the moment, Please try again.";
    private Button start_shopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // androidx.appcompat.widget.Toolbar action_bar = findViewById(R.id.action_bar);
        //setSupportActionBar(action_bar);

        start_shopping = findViewById(R.id.start_shopping);

        start_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //startActivity(new Intent(HomeActivity.this,CameraPreview.class));
                startActivity(new Intent(HomeActivity.this,Scanner.class));
            }
        });

        button1=findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,VirtualCart.class);
                startActivity(intent);
            }
        });
        button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AlertDialog dialog =  new AlertDialog.Builder(HomeActivity.this)
                        .setMessage(Html.fromHtml("<font color='#00000'>Do you want to exit</font>"))
                        .setCancelable(true)
                        .setPositiveButton(Html.fromHtml("<font color='#00000'>Yes</font>"), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HomeActivity.super.onBackPressed();
                                finish();
                            }
                        })
                        .setNegativeButton(Html.fromHtml("<font color='#00000'>No</font>"), null)
                      .show();

            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout)
        {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            finish();
        }
        if(id == R.id.history)
        {
            Log.d(TAG, "onOptionsItemSelected: History");
        }
        if(id == R.id.map)
        {
            Log.d(TAG, "onOptionsItemSelected: Map");
            startActivity(new Intent(HomeActivity.this,MapActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
