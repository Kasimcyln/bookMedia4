package com.kasimkartal866.bookmedia.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kasimkartal866.bookmedia.R;

public class AllListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View desing = inflater.inflate(R.layout.fragment_all_list, container, false);
        return desing;
    }
}