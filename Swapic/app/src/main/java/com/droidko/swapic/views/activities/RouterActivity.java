package com.droidko.swapic.views.activities;

import android.app.Activity;
import android.os.Bundle;

import com.droidko.swapic.firebase.FbAuthManager;
import com.droidko.swapic.utils.NavigationUtils;

/* *
* RouterActivity is a routing activity that acts as an entry point for the app.
*
* The main responsibility of this activity is to redirect the user to the HomeActivity or
* to the LoginActivity depending on weather there is a a logged user stored on the app or not.
*
* */

public class RouterActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FbAuthManager.isUserLoggedIn()) NavigationUtils.jumpTo(this, HomeActivity.class);
        else NavigationUtils.jumpTo(this, LoginActivity.class);
        finish();
    }
}
