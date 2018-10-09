package Firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private FirebaseAuth mAuth;

    private static final FirebaseService thisInstance = new FirebaseService();
    public static FirebaseService getInstance(){return thisInstance;}

    private FirebaseService(){}


    //Pass Activity into command
    //Possibly return value

    public boolean login(String email, String password, final Activity activity) {
        final boolean[] success = {false};

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                //SUCCESS
                    Log.d("FIREBASE AUTH", "SUCCESS");
                    FirebaseUser user = mAuth.getCurrentUser();
                    success[0] = true;
                }else{
                //NOT SUCCESS
                    Log.d("FIREBASE AUTH", "ERROR");
                    Toast.makeText(activity, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    success[0] = false;
                }

            }
        });
        return success[0];
    }

    public boolean signUp(String email, String password, final Activity activity) {
        final boolean[] success = {false};
        Log.d("PRINTING", "PRINTING");
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                //SUCCESS
                    Log.d("FIREBASE AUTH", "SUCCESSFULLY added user");
                    FirebaseUser user = mAuth.getCurrentUser();
                    success[0] = true;
                }else{
                    //NOT SUCCESS
                    Log.d("FIREBASE AUTH", "ERROR - USER WAS NOT ADDED");
                    Toast.makeText(activity, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    success[0] = false;

                }
            }
        });
        return success[0];
    }
}