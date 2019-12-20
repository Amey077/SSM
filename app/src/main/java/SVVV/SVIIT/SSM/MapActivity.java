package SVVV.SVIIT.SSM;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    EditText searchItem;
    TextView rack;
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        searchButton = findViewById(R.id.searchButton);
        searchItem = findViewById(R.id.searchItem);
        rack = findViewById(R.id.rack);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = searchItem.getText().toString().trim();
                if(itemName.equals("tea")){
                    rack.setText("RACK 1");
                }
                else
                    if(itemName.equals("apple")){
                        rack.setText("RACK 5");
                    }
                    else
                    if(itemName.equals("bread")){
                        rack.setText("RACK 2");
                    }
                    else
                    if(itemName.equals("zandu balm")){
                        rack.setText("RACK 9");
                    }
                    else{
                        rack.setText("Item not available");
                    }
            }
        });
    }
}
