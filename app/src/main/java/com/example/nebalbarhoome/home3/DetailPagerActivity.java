package com.example.nebalbarhoome.home3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class DetailPagerActivity extends AppCompatActivity {

    private static final String PAGE_INDEX_KEY = "PAGE_INDEX_KEY";

    public static Intent newIntent(Context c, int pageIndex) {
        Intent intent = new Intent(c, DetailPagerActivity.class);
        intent.putExtra(PAGE_INDEX_KEY, pageIndex);
        return intent;
    }

    private static int getPageIndex(Intent i) {
        return i.getIntExtra(PAGE_INDEX_KEY, 0);
    }

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_detail);

        viewPager = findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return DetailFragment.newInstance(DetailPagerActivity.this, position);
            }

            @Override
            public int getCount() {
                return DataBase.getInstance().getPageCount();
            }
        });
        viewPager.setCurrentItem(getPageIndex(getIntent()));
    }
}
