package com.droidko.swapic.firebase;

import com.droidko.swapic.firebase.events.FirebaseAuthEvent;
import com.google.firebase.auth.FirebaseAuth;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class FbAuthManager extends FirebaseManager {

    /**
     * Detects synchronously if there is a logged user
     *
     * @return A boolean indicating if there is a logged user
     */
    public static boolean isUserLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }


    public static Observable<FirebaseAuthEvent> logInAnonymously() {

        // TODO: 20/5/16 TRY TO REFACTOR THIS WITH LAMBDA
        return Observable.create(new Observable.OnSubscribe<FirebaseAuthEvent>() {
            @Override
            public void call(final Subscriber<? super FirebaseAuthEvent> subscriber) {
                try {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                    firebaseAuth
                            .signInAnonymously()
                            .addOnCompleteListener(task -> {
                                if (!subscriber.isUnsubscribed()) {
                                    if(task.isSuccessful())
                                        subscriber.onNext(new FirebaseAuthEvent(firebaseAuth.getCurrentUser(), null));
                                    else
                                        subscriber.onNext(new FirebaseAuthEvent(null, task.getException()));
                                    subscriber.onCompleted();
                                }
                            });

                } catch (Exception e) {
                    if (!subscriber.isUnsubscribed()) subscriber.onError(e);
                }
            }
        }).observeOn(Schedulers.io());

    }


    @Override
    public void disconnect() {
        // This manager doesn't keep any connection open to Firebase
    }
}
