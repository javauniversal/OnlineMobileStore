package com.example.poo_code.onlinemobilestore.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poo_code.onlinemobilestore.Entities.Category;
import com.example.poo_code.onlinemobilestore.R;

import java.util.List;

public class CustomAdapterCategory extends BucketListAdapterCategotia<String> {

    private Activity mActivity;

    public CustomAdapterCategory(Activity ctx, List<Category> elements) {
        super(ctx, elements);
        this.mActivity=ctx;
    }

    @Override
    protected View getBucketElement(int position, final Category currentElement) {
        final ViewHolder holder;
        View bucketElement = null;
        LayoutInflater inflater = mActivity.getLayoutInflater();
        bucketElement = inflater.inflate(R.layout.list_giditem_category, null);
        holder = new ViewHolder(bucketElement);
        bucketElement.setTag(holder);

        LinearLayout fon = (LinearLayout) bucketElement.findViewById(R.id.fondo);
        fon.setBackgroundColor(Color.parseColor(currentElement.getImgen()));
        holder.name.setText(currentElement.getDescription());

        return bucketElement;
    }

    class ViewHolder {
        public TextView name = null;

        ViewHolder(View row) {
            name = (TextView) row.findViewById(R.id.txtNombreCategoria);
        }

        void populateFrom(String s) {
            name.setText(s);
        }
    }
}


