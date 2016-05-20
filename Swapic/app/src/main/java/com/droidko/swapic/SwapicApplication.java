package com.droidko.swapic;

import android.app.Application;

public class SwapicApplication extends Application {

    private static SwapicApplication sSwapicApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        sSwapicApplication = this;

        setUpFirebase();
    }

    /**
     * Gets and instance of the current running application
     *
     * @return An instance of a running SwapicApplication
     */
    public static SwapicApplication getInstance() {
        return sSwapicApplication;
    }

    private void setUpFirebase() {
        // Do something...
    }

}
