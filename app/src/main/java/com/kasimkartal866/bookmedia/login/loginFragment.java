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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kasimkartal866.bookmedia.R;

public class loginFragment extends Fragment {
    private EditText email;
    private EditText pass;
    private Button btnLogin;
    View mainView;
    public SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_login, container, false);
        email = mainView.findViewById(R.id.email);
        pass = mainView.findViewById(R.id.pass);
        btnLogin = mainView.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            Boolean state = false;
            if (TextUtils.isEmpty(email.getText().toString())) {
                email.setError("enter e-mail");
                state = true;
            }
            if (TextUtils.isEmpty(pass.getText().toString())) {
                pass.setError("enter password");
                state = true;
            }
             preferences = getContext().getSharedPreferences("validation", Context.MODE_PRIVATE);

            String emailDisk = preferences.getString("email", "empty");
            String passwordDisk = preferences.getString("password", "empty");

            if (email.getText().toString().contentEquals(emailDisk) && pass.getText().toString().contentEquals(passwordDisk) ) {
                Navigation.findNavController(mainView).navigate(R.id.loginToResult);
            } else {
                Toast.makeText(getActivity(), "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
            }
        });

        return mainView;
    }
}
