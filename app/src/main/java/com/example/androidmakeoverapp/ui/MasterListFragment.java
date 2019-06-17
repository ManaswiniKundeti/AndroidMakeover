package com.example.androidmakeoverapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.androidmakeoverapp.R;
import com.example.androidmakeoverapp.data.ImageAssets;

public class MasterListFragment extends Fragment {

    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = rootView.findViewById(R.id.images_grid_view);

        MasterListAdapter adapter = new MasterListAdapter(getContext(), ImageAssets.getAll());

        gridView.setAdapter(adapter);

        return rootView;
    }
}

