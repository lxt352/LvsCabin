package com.wiseme.lvscabin.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.utils.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private static final int MSG_OPEN_MAIN_ACTIVITY = 1;

    private static final int SECONDS_WAITING = 2000;

    private static final int REQUEST_GOOGLE_SERVICE_AVAILABILITY = 11;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            removeMessages(MSG_OPEN_MAIN_ACTIVITY);
            openMainActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkGps();
    }

    private void checkGps() {
        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        int resultCode = availability.isGooglePlayServicesAvailable(this);
        boolean hasGps = resultCode == ConnectionResult.SUCCESS;
        Log.I("google play service available " + hasGps, hasGps);
        if (hasGps) {
            mHandler.sendEmptyMessageDelayed(MSG_OPEN_MAIN_ACTIVITY, SECONDS_WAITING);
        } else {
            Dialog dialog = availability.getErrorDialog(this, resultCode, REQUEST_GOOGLE_SERVICE_AVAILABILITY);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
            dialog.show();
        }
    }

    @OnClick({R.id.enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter:
                mHandler.removeMessages(MSG_OPEN_MAIN_ACTIVITY);
                openMainActivity();
                break;
        }
    }

    private void openMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
