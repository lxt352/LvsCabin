package com.wiseme.lvscabin.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.utils.Log;
import com.wiseme.lvscabin.utils.ToastUtils;
import com.wiseme.lvscabin.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class MusicFragment extends BaseFragment {

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    private FirebaseAuth mAuth;

    private FirebaseUser mUser;

    private View mView;

    private FirebaseAuth.AuthStateListener mAuthStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            mUser = firebaseAuth.getCurrentUser();
            if (mUser != null) {
                Log.I("user is not null", true);
            } else {
                Log.I("user is null", false);
            }
//            if (mUser != null && !mUser.isEmailVerified()) {
//                mUser.sendEmailVerification();
//                ToastUtils.toastShortly(getContext(), "email of verification is sent");
//            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(mAuthStateListener);

        boolean useravailable = mUser != null;
        Log.I("firebase user is available " + useravailable, useravailable);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_sign_in, container, false);
            ButterKnife.bind(this, mView);
            bindToolbar(mView);
            setToolbarTitle("Music", Gravity.CENTER);
        }
        return mView;
    }

    @OnClick({R.id.submit})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.submit:
//                createUser();
                signIn();
                break;
        }
    }

    private void createUser() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            return;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ToastUtils.toastShortly(getContext(), "success");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ToastUtils.toastShortly(getContext(), e.getMessage());
                        Log.I("create user failed " + e.toString(), false);
                    }
                });
    }

    private void signIn() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ToastUtils.toastShortly(getContext(), getString(R.string.action_sign_in_success));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidUserException) {
                            ToastUtils.toastShortly(getContext(), getString(R.string.error_user_inavailable));
                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            ToastUtils.toastShortly(getContext(), getString(R.string.error_username_password));
                        }
//                        ToastUtils.toastShortly(getContext(), getString(R.string.error_sign_in));
                        Log.I("failed to sign in " + e.toString(), false);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
