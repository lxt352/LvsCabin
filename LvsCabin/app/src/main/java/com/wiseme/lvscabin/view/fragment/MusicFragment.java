package com.wiseme.lvscabin.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wiseme.lvscabin.R;
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

    private View mView;

    private OnCompleteListener mOnCompleteListener = new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull Task task) {
            if (task.isSuccessful()) {
                ToastUtils.toastShortly(getContext(),"success");
            } else {
                ToastUtils.toastShortly(getContext(), getString(R.string.error_login_in));
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
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

    @OnClick({R.id.button_done})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.button_done:
                Task<AuthResult> task = mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString());
                task.addOnCompleteListener(mOnCompleteListener);
                break;
        }
    }
}
