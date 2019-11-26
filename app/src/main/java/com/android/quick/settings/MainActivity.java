package com.android.quick.settings;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private AppListAdapter mAdapter;

    private static final String SETTINGS_PACKAGE_NAME = "com.android.settings";
    private static final String SETTINGS_FRAGMENT_DEPENDENT_ACTIVITY = "com.android.settings.Settings";

    private static int[] SETTINGS_ITEM_NAME_IDS = {
            R.string.str_settings_network,
            R.string.str_settings_wifi,
            R.string.str_settings_bluetooth,
            R.string.str_settings_vpn,
    };

    private static int[] SETTINGS_DRAWABLES = {
            R.drawable.settings_network,
            R.drawable.settings_wifi,
            R.drawable.settings_bluetooth,
            R.drawable.settings_vpn,
    };

    private static String[] SETTINGS_FRAGMENT_NAMES = {
            "",
            "com.android.settings.Settings$WifiSettingsActivity",
            "com.android.settings.Settings$BluetoothSettingsActivity",
            "com.android.settings.Settings$VpnSettingsActivity",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView lv = (ListView) findViewById(R.id.lv_settings);
        mAdapter = new AppListAdapter(this,getAppList());
        lv.setOnItemClickListener(this);
        lv.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       App app = (App)mAdapter.getItem(position);
       startAction(app.fragment,app.pkgName,app.clsName);
    }

    public List<App> getAppList() {
        Resources res = this.getResources();

        List<App> appList = new ArrayList<App>();
        String name;
        int drawable;
        String pkgName;
        String clsName;
        String fragment;
        for (int i = 0; i < SETTINGS_ITEM_NAME_IDS.length; i++) {
            name = res.getString(SETTINGS_ITEM_NAME_IDS[i]);
            drawable = SETTINGS_DRAWABLES[i];
            pkgName = SETTINGS_PACKAGE_NAME;
            clsName = SETTINGS_FRAGMENT_DEPENDENT_ACTIVITY;
            fragment = SETTINGS_FRAGMENT_NAMES[i];

            if (i == 0) {
                pkgName = "com.android.phone";
                clsName = "com.android.phone.MobileNetworkSettings";
            }

            App app = new App(name, drawable, pkgName, clsName, fragment);
            appList.add(app);
        }
        return appList;
    }

    private void startAction(String fragment, String pkgName, String clsName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.putExtra("extra_prefs_show_button_bar",true);
        intent.putExtra("extra_prefs_set_next_text","");
        if (!TextUtils.isEmpty(fragment)) {
            /*intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, fragment);
            intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);*/
            clsName = fragment;
        }
        intent.setClassName(pkgName, clsName);
        startActivity(intent);
    }
}
