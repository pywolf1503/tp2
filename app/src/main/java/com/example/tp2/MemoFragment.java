package com.example.tp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoFragment extends Fragment {

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "description";

    private String title;
    private String[] description;

    public MemoFragment() {

    }

    public static MemoFragment newInstance(String title, String[] description) {
        MemoFragment fragment = new MemoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putStringArray(ARG_PARAM2, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            description = getArguments().getStringArray(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_swipe, container, false);
        TextView titleView = root.findViewById(R.id.titleView);
        titleView.setText(title);
        ListView descriptionView = root.findViewById(R.id.descriptionView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.item_list, description);
        descriptionView.setAdapter(adapter);
        return root;
    }
}