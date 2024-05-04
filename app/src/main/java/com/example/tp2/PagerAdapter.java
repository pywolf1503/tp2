package com.example.tp2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {
    public static int currentPos = 0;
    public static String mode = "Memo";
    List<Fragment> memoList;
    List<Fragment> conv;
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> memoList,List<Fragment> conv) {
        super(fragmentActivity);
        this.memoList = memoList;
        this.conv = conv;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(PagerAdapter.mode.equals("Memo")) {
            return memoList.get(position);
        }
        else{
            return conv.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }
}
