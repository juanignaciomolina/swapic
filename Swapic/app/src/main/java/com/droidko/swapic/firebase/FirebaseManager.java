package com.droidko.swapic.firebase;

import com.droidko.swapic.BaseConfiguration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseManager implements FirebaseConnectable {

    // Constants
    private static final String PATH_USERS = "users";

    // References
    private static final DatabaseReference mRefRoot =
            FirebaseDatabase
                    .getInstance()
                    .getReferenceFromUrl(BaseConfiguration.FIREBASE_ROOT_URL);
    private static final DatabaseReference mRefUsers = mRefRoot.child(PATH_USERS);

    // ** References accessors **
    public static DatabaseReference getRefRoot() {
        return mRefRoot;
    }

    public static DatabaseReference getRefUsers() {
        return mRefUsers;
    }

}
