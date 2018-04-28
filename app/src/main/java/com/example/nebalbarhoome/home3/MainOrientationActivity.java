package com.example.nebalbarhoome.home3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static com.example.nebalbarhoome.home3.DataBase.fName;

public class MainOrientationActivity extends AppCompatActivity implements DetailDisplayer {

    public static final String DATABASE_NAME = "database.serialized";
    private boolean landscape;
    private int pageIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageIndex = 0;
        setContentView(R.layout.activity_orientation_main);
        loadDatabase();

        landscape = findViewById(R.id.detail_fragment_container) != null;
        if (!landscape)
            onCreatePortrait();
        else
            onCreateLandscape();

        updateUI();
    }

    private void onCreatePortrait() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.list_fragment_container);

        if (f == null) {
            f = createMainFragment();
            fm.beginTransaction()
                    .add(R.id.list_fragment_container, f)
                    .commit();
        }
    }

    private void onCreateLandscape() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.list_fragment_container);

        if (f == null) {
            f = createMainFragment();
            fm.beginTransaction()
                    .add(R.id.list_fragment_container, f)
                    .commit();
        }

        f = fm.findFragmentById(R.id.detail_fragment_container);
        if (f == null) {
            f = createDetailFragment();
            fm.beginTransaction()
                    .add(R.id.detail_fragment_container, f)
                    .commit();
        }
    }

    private Fragment createDetailFragment() {
        return DetailFragment.newInstance(this, pageIndex);
    }

    private Fragment createMainFragment() {
        return new MainFragment();
    }

    private void updateUI() {

    }

    private void loadDatabase() {
        File f = new File(getFilesDir(), fName);
        try {
            DataBase.load(f);
        } catch (IOException e) {
            DataBase.createSampleData();
        } catch (ClassNotFoundException e) {
            Log.d("COUNTER", "Data file corrupted (class not found): "+e.getMessage());
            Toast.makeText(this, "Failed to load database", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showDetail(int pageIndex) {
        // increment + save database
        this.pageIndex = pageIndex;
        DataBase.getInstance().getPage(pageIndex).incrementVisitCount();
        File file = new File(getFilesDir(), fName);
        try {
            DataBase.getInstance().save(file);
        } catch (IOException e) {
            Toast.makeText(this, "Failed to load database", Toast.LENGTH_LONG).show();
        }

        if (!landscape) {
            Intent intent = DetailPagerActivity.newIntent(this, pageIndex);
            startActivity(intent);
        } else {
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = createDetailFragment();
                fm.beginTransaction()
                        .replace(R.id.detail_fragment_container, f)
                        .commit();

        }
    }
}
