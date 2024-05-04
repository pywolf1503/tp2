package com.example.tp2;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.health.connect.datatypes.units.Power;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tp2.data.DistanceUnit;
import com.example.tp2.data.EnergyUnit;
import com.example.tp2.data.MassUnit;
import com.example.tp2.data.PowerUnit;
import com.example.tp2.data.TemperatureUnit;
import com.example.tp2.data.UnitConverter;
import com.example.tp2.data.VolumeUnit;
import com.example.tp2.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private List<Fragment> fragmentList2;
    private List<dataObject> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initData();
        initMemo();
        initConv();
        ViewPager2 viewPager2 = findViewById(R.id.vp1);
        PagerAdapter adapter = new PagerAdapter(this,fragmentList,fragmentList2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                PagerAdapter.currentPos = position;
                Log.i(TAG,"Position = " + position);
            }
        });
        viewPager2.setAdapter(adapter);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.Theme_PopupMenu);
                PopupMenu popupMenu = new PopupMenu(wrapper,v);
                popupMenu.inflate(R.menu.mode_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.menu_memo){
                            PagerAdapter.mode = "Memo";
                            adapter.notifyDataSetChanged();
                            viewPager2.setAdapter(adapter);
                            viewPager2.setCurrentItem(PagerAdapter.currentPos, false);
                        }
                        else if (item.getItemId() == R.id.menu_conv){
                            PagerAdapter.mode = "Conv";
                            adapter.notifyDataSetChanged();
                            viewPager2.setAdapter(adapter);
                            viewPager2.setCurrentItem(PagerAdapter.currentPos, false);
                        }
                        else{
                            return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                Log.d(TAG,"Clicked Menu!");
            }
        });

    }

    private void initData(){
        this.dataList = new ArrayList<>();
        // MASS
        dataList.add(new dataObject("Mass",new String[]{
                        "1 gram (g) = 0.001 kilograms (kg)",
                        "1 milligram (mg) = 0.000001 kilograms (kg)",
                        "1 microgram (µg) = 0.000000001 kilograms (kg)",
                        "1 metric ton (tonne) = 1000 kilograms (kg)",
                        "1 pound (lb) ≈ 0.453592 kilograms (kg)",
                        "1 ounce (oz) ≈ 0.0283495 kilograms (kg)",
                        "1 stone (st) ≈ 6.35029 kilograms (kg)",
                        "1 newton (N) ≈ 0.101972 kilograms (kg) (on Earth's surface)"
                }
                )
        );
        dataList.add(new dataObject("Volume",new String[]{
                "1 liter (L) = 1 liter (L)",
                "1 milliliter (mL) = 0.001 liters (L)",
                "1 cubic meter (m³) = 1000 liters (L)",
                "1 cubic centimeter (cm³) = 0.001 liters (L)",
                "1 gallon (gal) ≈ 3.78541 liters (L)",
                "1 quart (qt) ≈ 0.946353 liters (L)",
                "1 pint (pt) ≈ 0.473176 liters (L)",
                "1 fluid ounce (fl oz) ≈ 0.0295735 liters (L)",
        }));
        dataList.add(new dataObject("Distance",new String[]{
                "1 meter (m) = 1 meter (m)",
                "1 kilometer (km) = 1000 meters (m)",
                "1 centimeter (cm) = 0.01 meters (m)",
                "1 millimeter (mm) = 0.001 meters (m)",
                "1 micrometer (µm) = 0.000001 meters (m)",
                "1 nanometer (nm) = 0.000000001 meters (m)",
                "1 mile (mi) ≈ 1609.34 meters (m)",
                "1 yard (yd) ≈ 0.9144 meters (m)",
                "1 foot (ft) ≈ 0.3048 meters (m)",
                "1 inch (in) ≈ 0.0254 meters (m)"
        }
        ));
        dataList.add(new dataObject("Power", new String[]{
                "1 watt (W) = 1 watt (W)",
                "1 kilowatt (kW) = 1000 watts (W)",
                "1 megawatt (MW) = 1000000 watts (W)",
                "1 gigawatt (GW) = 1000000000 watts (W)",
                "1 horsepower (hp) ≈ 745.7 watts (W)",
                "1 milliwatt (mW) = 0.001 watts (W)",
                "1 microwatt (µW) = 0.000001 watts (W)",
                "1 nanowatt (nW) = 0.000000001 watts (W)"
        }
        ));
        dataList.add(new dataObject("Energy",new String[]{
                "1 joule (J) = 1 joule (J)",
                "1 kilojoule (kJ) = 1000 joules (J)",
                "1 megajoule (MJ) = 1000000 joules (J)",
                "1 gigajoule (GJ) = 1000000000 joules (J)",
                "1 kilowatt-hour (kWh) ≈ 3600000 joules (J)",
                "1 calorie (cal) ≈ 4.184 joules (J)",
                "1 kilocalorie (kcal) ≈ 4184 joules (J)",
                "1 electronvolt (eV) ≈ 1.60218 × 10^-19 joules (J)"
        }
        ));
        dataList.add(new dataObject("Temperature",new String[]{
                "1 degree Celsius (°C) = 1 degree Celsius (°C)",
                "1 degree Fahrenheit (°F) = 0.55556 degrees Celsius (°C)",
                "1 degree Kelvin (K) = 1 degree Celsius (°C)",
                "1 degree Rankine (°R) = 0.55556 degrees Kelvin (K)"
        }));
    }

    private void initMemo(){
        fragmentList = new ArrayList<>();
        for(dataObject data : dataList){
            fragmentList.add(MemoFragment.newInstance(data.getTitle(), data.getDescription()));
        }
    }

    private void initConv(){
        fragmentList2 = new ArrayList<>();
        List<String> names = new ArrayList<>();
        // MASS
        for(MassUnit unit : MassUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Mass", names.toArray(new String[0])));
        names.clear();


        //VOLUME
        for(VolumeUnit unit : VolumeUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Volume", names.toArray(new String[0])));
        names.clear();


        // Distance
        for(DistanceUnit unit : DistanceUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Distance", names.toArray(new String[0])));
        names.clear();

        //POWER

        for(PowerUnit unit : PowerUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Power", names.toArray(new String[0])));
        names.clear();

        //ENERGY


        for(EnergyUnit unit : EnergyUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Energy", names.toArray(new String[0])));
        names.clear();


        //Temparture
        for(TemperatureUnit unit : TemperatureUnit.values()){
            names.add(unit.getName());
        }
        fragmentList2.add(ConvFragment.newInstance("Temperature", names.toArray(new String[0])));
        names.clear();
    }

    private class dataObject{
        private String title;
        private String[] description;

        public dataObject(String title, String[] description){
            this.title = title;
            this.description = description;
        }


        public String getTitle() {
            return title;
        }

        public String[] getDescription() {
            return description;
        }
    }
}