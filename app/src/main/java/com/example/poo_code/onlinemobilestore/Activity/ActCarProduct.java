package com.example.poo_code.onlinemobilestore.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.poo_code.onlinemobilestore.Adapter.AppAdapter;
import com.example.poo_code.onlinemobilestore.DataBase.DBHelper;
import com.example.poo_code.onlinemobilestore.Entities.Product;
import com.example.poo_code.onlinemobilestore.R;

import java.util.List;

public class ActCarProduct extends AppCompatActivity {

    private AppAdapter mAdapter;
    private DBHelper mydb;
    private SwipeMenuListView mListView;
    private List<Product> mAppListPublico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_car_product);
        mydb = new DBHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        mListView = (SwipeMenuListView) findViewById(R.id.listView);
        llenarData();
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                //openItem.setTitle("Editar");
                // set item title fontsize
                //openItem.setTitleSize(18);
                // set item title font color
                //openItem.setTitleColor(Color.WHITE);
                //--
                openItem.setIcon(R.drawable.ic_action_edit);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                //AddProductCar item = mAppList.get(position);
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        createDialog(position);
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
        // listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //createDialog(position);
                return false;
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void llenarData() {
        new Thread(new Runnable() {
            List<Product> mAppList = mydb.getProductCar();
            @Override
            public void run() {
                mAdapter = new AppAdapter(ActCarProduct.this, mAppList);
                mListView.setAdapter(mAdapter);
                mAppListPublico = mAppList;
            }
        }).start();
    }

    private void createDialog(final int position){
        new MaterialDialog.Builder(ActCarProduct.this)
                .title("Eliminar producto")
                .content("Esta seguro de eliminar del carrito")
                .positiveText("Aceptar")
                .negativeText("Cancelar")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        //Aceptar
                        if (!mydb.DeleteProduct(mAppListPublico.get(position).getIdAutoIncrement())) {
                            Toast.makeText(getApplicationContext(), "Problemas al eliminar el producto", Toast.LENGTH_SHORT).show();
                        } else {
                            mAppListPublico.remove(position);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        //Cancelar
                        dialog.dismiss();
                    }
                }).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
