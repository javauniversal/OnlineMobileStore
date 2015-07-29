package com.example.poo_code.onlinemobilestore.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.poo_code.onlinemobilestore.Adapter.DrawerItemAdapter;
import com.example.poo_code.onlinemobilestore.Entities.DrawerItem;
import com.example.poo_code.onlinemobilestore.Entities.DrawerMenu;
import com.example.poo_code.onlinemobilestore.Fragment.FragmentCategory;
import com.example.poo_code.onlinemobilestore.Fragment.FragmentRebaja;
import com.example.poo_code.onlinemobilestore.R;
import java.util.Arrays;
import java.util.List;

public class ActMain extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final RecyclerView drawerOptions = (RecyclerView) findViewById(R.id.drawer_options);
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerShadow(R.mipmap.drawer_shadow, Gravity.START);
        drawerLayout.setStatusBarBackground(R.color.color_principal);
        drawerLayout.setDrawerListener(drawerToggle);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<DrawerItem> drawerItems =  new java.util.ArrayList<DrawerItem>(Arrays.asList(
                new DrawerItem(DrawerItem.Type.HEADER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_tags).setText(getString(R.string.drawer_rebajas, 1)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_tiles_small).setText(getString(R.string.drawer_categoria, 3)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_cart).setText(getString(R.string.drawer_carrito, 5)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_location).setText(getString(R.string.drawer_tiendas, 7)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_tshirt).setText(getString(R.string.drawer_nueva_temporada, 9)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_user).setText(getString(R.string.drawer_perfil, 11)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_dialog).setText(getString(R.string.drawer_ayuda, 13)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_barcode_2).setText(getString(R.string.drawer_scan, 15)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.mipmap.ic_action_exit).setText(getString(R.string.drawer_salir, 17)),
                new DrawerItem(DrawerItem.Type.DIVIDER)));


        drawerOptions.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DrawerItemAdapter(drawerItems);
        adapter.setOnItemClickListener(new DrawerItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                onDrawerMenuSelected(position);
            }
        });

        drawerOptions.setAdapter(adapter);
        drawerOptions.setHasFixedSize(true);

    }

    private void onDrawerMenuSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle arguments = new Bundle();
        switch (position){
            case 3:
                arguments.putString("operador", "menu");
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, FragmentCategory.newInstance(arguments))
                        .commit();
                break;
            case 1:
                arguments.putString("operador", "menu");
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, FragmentRebaja.newInstance(arguments))
                        .commit();
                break;
            case 0:
                /*arguments.putString("operador", "menu");
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, FragListCategorias.newInstance(arguments))
                        .commit();*/
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                //salirApp();
                break;
            default:

        };
        drawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
