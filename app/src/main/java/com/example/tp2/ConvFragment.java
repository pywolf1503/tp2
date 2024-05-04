package com.example.tp2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tp2.data.DistanceUnit;
import com.example.tp2.data.EnergyUnit;
import com.example.tp2.data.MassUnit;
import com.example.tp2.data.PowerUnit;
import com.example.tp2.data.TemperatureUnit;
import com.example.tp2.data.UnitConverter;
import com.example.tp2.data.VolumeUnit;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConvFragment extends Fragment {
    static String from;
    static  String to;
    private String[] final_list;
    private String title;
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "description";
    public static ConvFragment newInstance(String title, String[] description) {
        ConvFragment fragment = new ConvFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putStringArray(ARG_PARAM2, description);
        fragment.setArguments(args);
        return fragment;
    }

    public ConvFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            final_list = getArguments().getStringArray(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conv, container, false);

        Spinner from = root.findViewById(R.id.from);
        Spinner to = root.findViewById(R.id.to);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.drop_item, final_list);
        adapter.setDropDownViewResource(R.layout.drop_item);
        from.setAdapter(adapter);
        to.setAdapter(adapter);
        TextView titleView = root.findViewById(R.id.title_conv);
        titleView.setText(title);
        Button btn = root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                handleConvert(v);
            }
        });
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ConvFragment.from = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ConvFragment.to = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return root;
    }

    public void handleConvert(View view) {
        View root = getView();
        TextView res = root.findViewById(R.id.result);
        TextInputEditText input = root.findViewById(R.id.input1);
        String val = input.getText().toString();
        double value = 0;
        if(!val.isEmpty()){
            value = Double.parseDouble(val);
        }

        if(ConvFragment.from != "" && ConvFragment.to != ""){
            if(title == "Mass"){
                for (MassUnit unit : MassUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(MassUnit unit2 : MassUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
            else if (title == "Distance"){
                for (DistanceUnit unit : DistanceUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(DistanceUnit unit2 : DistanceUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
            else if (title == "Energy"){
                for (EnergyUnit unit : EnergyUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(EnergyUnit unit2 : EnergyUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
            else if (title == "Power"){
                for (PowerUnit unit : PowerUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(PowerUnit unit2 : PowerUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
            else if (title == "Temperature"){
                for (TemperatureUnit unit : TemperatureUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(TemperatureUnit unit2 : TemperatureUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
            else if (title == "Volume"){
                for (VolumeUnit unit : VolumeUnit.values()) {
                    // Check if the enum constant name matches the desired unitName (case-insensitive)
                    if (unit.getName().equalsIgnoreCase(from)) {
                        for(VolumeUnit unit2 : VolumeUnit.values()){
                            if(unit2.getName().equalsIgnoreCase(to)){
                                res.setText(String.valueOf(UnitConverter.convert(value,unit,unit2)));
                            }
                        }
                    }
                }
            }
        }
        else{
            res.setText("Select first!");
        }
    }
}