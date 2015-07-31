package com.example.poo_code.onlinemobilestore.Activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.poo_code.onlinemobilestore.Fragment.FragmentRebaja;
import com.example.poo_code.onlinemobilestore.R;

public class CategoriaProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null){
            if (bundle != null) {
                int idCategoria = bundle.getInt("categoria");
                Bundle arguments = new Bundle();
                arguments.putInt("categoria", idCategoria);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, FragmentRebaja.newInstance(arguments))
                        .commit();
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
