package Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private FirebaseAuth mAuth;

    private static final FirebaseService thisInstance = new FirebaseService();
    public static FirebaseService getInstance(){return thisInstance;}

    private FirebaseService(){}


    public void login(){

    }


}
