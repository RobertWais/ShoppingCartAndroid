package Firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.robertwais.shoppingcart.BrowseActivity;
import com.example.robertwais.shoppingcart.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference database;
    private FirebaseUser currUser;

    private static final FirebaseService thisInstance = new FirebaseService();
    public static FirebaseService getInstance(){return thisInstance;}

    private FirebaseService(){
    }


    //Pass Activity into command
    //Possibly return value

    public void signOut(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            Log.d("SIGN OUT","MAUTH WASNT NULL");
            mAuth.signOut();
        }else{
            Log.d("SIGN OUT","MAUTH WAS NULL");
            mAuth.signOut();
        }


    }


    public void login(String email, String password, final Activity activity) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                //SUCCESS
                    Log.d("FIREBASE AUTH", "SUCCESS");
                    FirebaseUser user = mAuth.getCurrentUser();
                    successLogin(user, activity);
                }else{
                //NOT SUCCESS
                    Log.d("FIREBASE AUTH", "ERROR");
                    Toast.makeText(activity, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    successLogin(null, activity);
                }

            }
        });
    }

    public void signUp(String email, String password, final Activity activity) {
        Log.d("PRINTING", "PRINTING");
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                //SUCCESS
                    Log.d("FIREBASE AUTH", "SUCCESSFULLY added user");
                    FirebaseUser user = mAuth.getCurrentUser();
                    successSignUp(user, activity);


                    //Save user in database
                    db = FirebaseDatabase.getInstance();
                    database = db.getReference();
                    currUser = FirebaseAuth.getInstance().getCurrentUser();
                    database.child("Users").child(user.getUid()).setValue(user.getEmail());

                }else{
                    //NOT SUCCESS
                    Log.d("FIREBASE AUTH", "ERROR - USER WAS NOT ADDED");
                    Toast.makeText(activity, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    successSignUp(null, activity);

                }
            }
        });
    }

    public void successLogin(FirebaseUser user, final Activity activity) {
        if (user != null) {
            activity.startActivity(new Intent(activity, BrowseActivity.class));
        } else {

        }
    }

    public void successSignUp(FirebaseUser user, final Activity activity) {
        if (user != null) {
            activity.startActivity(new Intent(activity, LogInActivity.class));
        } else {

        }
    }

    public String isUser(){
        if(mAuth!=null) {
            if (mAuth.getCurrentUser() != null) {
                if (mAuth.getCurrentUser().getEmail() != null) {
                    return mAuth.getCurrentUser().getEmail();
                }
            }
        }
        return "Guest";
    }

}