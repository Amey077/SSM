package SVVV.SVIIT.SSM;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RealTimeDBHandler {

    private static final String TAG = "mytag";
    private final Context context;

    public RealTimeDBHandler(Context context)
    {
        this.context = context;
    }

    public <T> void onCreateReference(T obj){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        try{
            if(obj instanceof User) {
                ref = ref.child("USERS");
                User user = (User) obj;
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                user.setUserId(id);
                ref=ref.child(id);
                ref.setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }catch (Exception e)
        {
            Log.d(TAG, "onCreateReference: "+e.getMessage());
        }
    }
}
