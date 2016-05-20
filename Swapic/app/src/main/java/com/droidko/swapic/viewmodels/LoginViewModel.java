package com.droidko.swapic.viewmodels;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.droidko.swapic.R;
import com.droidko.swapic.firebase.FbAuthManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class LoginViewModel implements BaseViewModel {

    // Constants
    private static final String TAG = "LoginVM";

    // Vars
    private Context mContext;
    private Subscription mAuthSubscription;

    // Observable fields
    public ObservableInt mLoadingVisibility;
    public ObservableField<String> mDoneMessage;

    public LoginViewModel(Context context) {
        mContext = context;

        mLoadingVisibility = new ObservableInt(View.INVISIBLE);
        mDoneMessage = new ObservableField<>(mContext.getString(R.string.login_loading));
    }

    public void onClickStart(View view) {
        doAnonymousLogin();
    }

    private void doAnonymousLogin() {

        mLoadingVisibility.set(View.VISIBLE);

        mAuthSubscription = FbAuthManager
                .logInAnonymously()
                .subscribeOn(AndroidSchedulers.mainThread()) //Probably redundant
                .subscribe(firebaseAuthEvent -> {
                            if (firebaseAuthEvent.getException() != null) {
                                mDoneMessage.set(mContext.getString(R.string.login_message_error));
                                Log.e(TAG, "Auth fail: " + firebaseAuthEvent.getException());
                            }
                            else
                                mDoneMessage.set(mContext.getString(R.string.login_message_successful));
                    }, exception -> {
                            mDoneMessage.set(mContext.getString(R.string.login_message_error));
                            Log.e(TAG, "Auth fail: " + exception.getMessage());
                    });
    }

    @Override
    public void destroy() {
        if (mAuthSubscription != null && !mAuthSubscription.isUnsubscribed())
            mAuthSubscription.unsubscribe();
    }
}
