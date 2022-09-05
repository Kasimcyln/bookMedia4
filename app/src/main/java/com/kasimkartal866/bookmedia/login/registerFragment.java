package com.kasimkartal866.bookmedia.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kasimkartal866.bookmedia.R;


public class registerFragment extends Fragment {

    private EditText etMail;
    private EditText etPhone;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnSave;
    View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_register, container, false);
        etMail = mainView.findViewById(R.id.etMail);
        etPhone = mainView.findViewById(R.id.etPhone);
        etPassword = mainView.findViewById(R.id.etPassword);
        etMail = mainView.findViewById(R.id.etMail);
        etPassword2 = mainView.findViewById(R.id.etPassword2);
        btnSave = mainView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean state = false;
                if (TextUtils.isEmpty(etMail.getText().toString())) {
                    etMail.setError("enter e-mail");
                    state = true;
                }

                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    etPhone.setError("enter phone");
                    state = true;
                }

                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    etPassword.setError("enter password");
                    state = true;
                }

                if (TextUtils.isEmpty(etPassword2.getText().toString())) {
                    etPassword2.setError("try enter password");
                    state = true;

                } else if (!etPassword2.getText().toString().contentEquals(etPassword.getText().toString())) {
                    etPassword2.setError("passwords are incompatible");
                    state = true;
                }

                if (state){
                    return;}
                SharedPreferences preferences = getContext().getSharedPreferences("validation", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", etMail.getText().toString());
                editor.putString("phone", etPhone.getText().toString());
                editor.putString("password", etPassword.getText().toString());
                editor.commit();

                Navigation.findNavController(mainView).navigate(R.id.toLoginFragment);
            }
        });


        return mainView;
    }
}