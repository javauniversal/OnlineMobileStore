package com.example.poo_code.onlinemobilestore.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.poo_code.onlinemobilestore.Activity.ActProductDetail;
import com.example.poo_code.onlinemobilestore.Entities.ListProduct;
import com.example.poo_code.onlinemobilestore.Entities.Product;
import com.example.poo_code.onlinemobilestore.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class CostomAdapterProduct extends BucketListAdapterProduct {

    private Activity mActivity;
    private ListProduct elements;
    private DisplayImageOptions options1;
    private ImageLoader imageLoader1;

    public CostomAdapterProduct(Activity ctx, ListProduct elements) {
        super(ctx, elements);
        this.mActivity=ctx;
        this.elements = elements;

        //Setup the ImageLoader, we'll use this to display our images
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mActivity).build();
        imageLoader1 = ImageLoader.getInstance();
        imageLoader1.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options1 = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

    }

    @Override
    protected View getBucketElement(final int position) {
        final ViewHolder2 holder;
        View bucketElement;
        LayoutInflater inflater = mActivity.getLayoutInflater();
        bucketElement = inflater.inflate(R.layout.list_giditem_generic, null);
        holder = new ViewHolder2(bucketElement);
        bucketElement.setTag(holder);

        //Llenado static product


        ImageLoadingListener listener = new ImageLoadingListener(){
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                // TODO Auto-generated method stub
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                // TODO Auto-generated method stub
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                holder.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub
                holder.progressBar.setVisibility(View.GONE);
            }
        };

        imageLoader1.displayImage(elements.get(position).getImagemes(), holder.img, options1, listener);
        holder.name.setText(elements.get(position).getNombre());
        holder.precie.setText(String.format("Precio: $ %s",elements.get(position).getPrecio()));
        bucketElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mActivity, elements.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                Product.setStaticIdProduct(elements.get(position).getIdProduct());
                Product.setStaticNombre(elements.get(position).getNombre());
                Product.setStaticdescripcion(elements.get(position).getDescripcion());
                Product.setStaticPrecio(elements.get(position).getPrecio());
                Product.setStaticImagen(elements.get(position).getImagemes());

                Product.setStaticColor(elements.get(position).getColor());
                Product.setStaticTallas(elements.get(position).getTalla());
                Product.setStaticComposicion(elements.get(position).getComposicion());
                Product.setStaticCuidado(elements.get(position).getCuidado());

                Intent intent = new Intent(mActivity, ActProductDetail.class);
                mActivity.startActivity(intent);
            }
        });

        return bucketElement;
    }

    class ViewHolder2 {
        public TextView name = null;
        public TextView precie = null;
        public ImageView img = null;
        public ProgressBar progressBar;

        ViewHolder2(View row) {
            name = (TextView) row.findViewById(R.id.namet);
            img = (ImageView) row.findViewById(R.id.listicon);
            precie = (TextView) row.findViewById(R.id.precio);
            progressBar = (ProgressBar) row.findViewById(R.id.progressBar);
        }
        void populateFrom(String s) {
            name.setText(s);
        }
    }
}
