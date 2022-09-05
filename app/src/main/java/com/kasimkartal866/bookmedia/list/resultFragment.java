package com.kasimkartal866.bookmedia.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kasimkartal866.bookmedia.R;

public class resultFragment extends Fragment {
    private TextView tvSelect;
    private Button btnAddBook;
    private Button btnMyList;
    private Button btnAllList;
    private Button btnLogOut;
    View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView= inflater.inflate(R.layout.fragment_result, container, false);

        btnAddBook = mainView.findViewById(R.id.btnAddBook);
        btnMyList = mainView.findViewById(R.id.btnMyList);
        btnAllList = mainView.findViewById(R.id.btnAllList);
        btnLogOut = mainView.findViewById(R.id.btnLoOut);
        tvSelect = mainView.findViewById(R.id.tvSelect);

        btnAddBook.setOnClickListener(v -> Navigation
                .findNavController(mainView).navigate(R.id.toAddBook));
        btnMyList.setOnClickListener(v -> Navigation
                .findNavController(mainView).navigate(R.id.toMyList));
        btnAllList.setOnClickListener(v -> Navigation
                .findNavController(mainView).navigate(R.id.toAllList));
        btnLogOut.setOnClickListener(v -> Navigation
                .findNavController(mainView).navigate(R.id.backLogin));

        return mainView;
    }

    public static class registerOrLoginFragment extends Fragment {
        private Button btnRegister;
        private Button btnLogin;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View design = inflater.inflate(R.layout.fragment_register_or_login, container, false);

            btnLogin = design.findViewById(R.id.btnLogin);
            btnRegister = design.findViewById(R.id.btnRegister);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.toLoginFragment);
                }
            });
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.toRegisterFragment);
                }
            });
            return design;
        }
    }
}