package SVVV.SVIIT.SSM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
