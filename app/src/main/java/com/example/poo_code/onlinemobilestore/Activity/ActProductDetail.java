package com.example.poo_code.onlinemobilestore.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.poo_code.onlinemobilestore.Entities.MasterItem;
import com.example.poo_code.onlinemobilestore.Entities.Product;
import com.example.poo_code.onlinemobilestore.R;
import com.example.poo_code.onlinemobilestore.animator.IO2014HeaderAnimator;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.text.DecimalFormat;
import java.util.List;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;

public class ActProductDetail extends AppCompatActivity {

    private ScrollView mListView;
    private DisplayImageOptions options1;
    private ImageLoader imageLoader1;
    private KenBurnsView img;
    private TextView nombre;
    private TextView precio;
    private TableLayout tabla, table2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_detail);

        mListView = (ScrollView) findViewById(R.id.listview);
        img = (KenBurnsView) findViewById(R.id.image);
        nombre = (TextView) findViewById(R.id.txtNombre);
        precio = (TextView) findViewById(R.id.precio);
        tabla = (TableLayout) findViewById(R.id.myTable);
        table2 = (TableLayout) findViewById(R.id.myTable2);

        nombre.setText(Product.getStaticdescripcion());
        nombre.setTextColor(Color.BLACK);
        DecimalFormat format = new DecimalFormat("$#,###.##");
        precio.setText(format.format(Product.getStaticPrecio()));

        IO2014HeaderAnimator animator = new IO2014HeaderAnimator(this);
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        StikkyHeaderBuilder.stickTo(mListView).setHeader(R.id.header, (ViewGroup) view)
                .minHeightHeaderDim(R.dimen.min_height_header_materiallike)
                .animator(animator)
                .build();

        //Setup the ImageLoader, we'll use this to display our images
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options1 = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        LoadingImag();
        cargarSpinner();
        composicion();
        cuidado();

    }

    public void composicion(){

        for (int i = 0; i < Product.getStaticComposicion().size(); i++){
            TableRow currentRow = new TableRow(getBaseContext());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

            TextView currentText = new TextView(getBaseContext());
            currentText.setText(Product.getStaticComposicion().get(i).toString());
            currentText.setTextSize(11);
            currentText.setTextColor(Color.BLACK);

            currentRow.setLayoutParams(params);
            currentRow.addView(currentText);

            tabla.addView(currentRow);
        }

    }

    public void cuidado(){
        for (int i = 0; i < Product.getStaticCuidado().size(); i++){
            TableRow currentRow = new TableRow(getBaseContext());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

            TextView currentText = new TextView(getBaseContext());
            currentText.setText(Product.getStaticCuidado().get(i).toString());
            currentText.setTextSize(11);
            currentText.setTextColor(Color.BLACK);

            currentRow.setLayoutParams(params);
            currentRow.addView(currentText);

            table2.addView(currentRow);
        }
    }

    public void cargarSpinner(){

        new Thread(new Runnable() {

            List<MasterItem> tallasData = Product.getStaticTallas();

            public void run() {
                Spinner spinnerTallas = (Spinner) findViewById(R.id.spTalla);
                ArrayAdapter<MasterItem> prec = new ArrayAdapter<MasterItem>(ActProductDetail.this, R.layout.textview_spinner, tallasData);
                spinnerTallas.setAdapter(prec);
            }
        }).start();

        new Thread(new Runnable() {
            List<MasterItem> colorData = Product.getStaticColor();
            public void run() {
                Spinner spinnerColor = (Spinner) findViewById(R.id.spColor);
                ArrayAdapter<MasterItem> prec = new ArrayAdapter<MasterItem>(ActProductDetail.this, R.layout.textview_spinner, colorData);
                spinnerColor.setAdapter(prec);
            }
        }).start();

    }

    public void LoadingImag(){
        ImageLoadingListener listener = new ImageLoadingListener(){
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub
            }
        };

        imageLoader1.displayImage(Product.getStaticImagen(), img, options1, listener);
    }




}
