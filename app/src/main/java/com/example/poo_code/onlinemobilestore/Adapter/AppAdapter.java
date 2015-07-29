package com.example.poo_code.onlinemobilestore.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poo_code.onlinemobilestore.Entities.Product;
import com.example.poo_code.onlinemobilestore.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.text.DecimalFormat;
import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Activity actx;
    List<Product> data;
    private ImageLoader imageLoader1;
    private DisplayImageOptions options1;

    public AppAdapter(Activity actx, List<Product> data){
        this.actx = actx;
        this.data = data;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(actx).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);

        options1 = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Product getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(actx, R.layout.item_list_app, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        Product item = getItem(position);

        holder.tv_name.setText(item.getNombre());
        DecimalFormat format = new DecimalFormat("$#,###.##");
        holder.tv_preci.setText(format.format(item.getPrecio()));
        holder.tv_cantidad.setText("Cantidad: 1");
        CargarImagen(holder,item);

        return convertView;
    }

    private void CargarImagen(ViewHolder holder,Product item){
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
                //holder.image.setVisibility(View.VISIBLE);
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub
            }
        };

        imageLoader1.displayImage(item.getImagemes(), holder.iv_icon, options1 , listener);
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_preci;
        TextView tv_cantidad;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_preci = (TextView) view.findViewById(R.id.tv_preci);
            tv_cantidad = (TextView) view.findViewById(R.id.tv_cantidad);
            view.setTag(this);
        }
    }

}
