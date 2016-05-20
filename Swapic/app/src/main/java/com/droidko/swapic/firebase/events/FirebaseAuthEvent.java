package com.droidko.swapic.firebase.events;

import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthEvent {

    private FirebaseUser mUser;
    private Exception mException;

    public FirebaseAuthEvent(FirebaseUser user, Exception exception) {
        mUser = user;
        mException = exception;
    }

    public FirebaseUser getUser() {
        return mUser;
    }

    public Exception getException() {
        return mException;
    }
}
