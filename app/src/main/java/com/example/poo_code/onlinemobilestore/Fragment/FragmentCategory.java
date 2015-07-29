package com.example.poo_code.onlinemobilestore.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.poo_code.onlinemobilestore.Adapter.CustomAdapterCategory;
import com.example.poo_code.onlinemobilestore.Entities.Category;
import com.example.poo_code.onlinemobilestore.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentCategory extends BaseVolleyFragment {

    private ListView multiColumnList;
    private ArrayList<Category> modelCategory;
    private ImageView mErrorView;

    public static FragmentCategory newInstance(Bundle bundle) {
        FragmentCategory fragment = new FragmentCategory();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FragmentCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle instanceState) {
        return inflater.inflate(R.layout.fragment_generic, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        multiColumnList = (ListView) getActivity().findViewById(R.id.card_list);
        mErrorView = (ImageView) getActivity().findViewById(R.id.error_data);
        setCategoryListView();
    }

    private void setCategoryListView() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"listCategory");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(final String response) {
                       if (!parseJSON(response)) {
                           Toast.makeText(getActivity(), "No se encontraron datos", Toast.LENGTH_LONG).show();
                           mErrorView.setVisibility(View.VISIBLE);
                       }else{
                           CustomAdapterCategory adapter = new CustomAdapterCategory(getActivity(), modelCategory);
                           adapter.enableAutoMeasure(150);
                           multiColumnList.setAdapter(adapter);
                           mErrorView.setVisibility(View.GONE);
                       }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        onConnectionFailed(error.toString());
                        mErrorView.setVisibility(View.GONE);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<>();

                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    private boolean parseJSON(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray mDatos = jsonObject.getJSONArray("publication");
            modelCategory = new ArrayList<Category>();
            for (int i = 0; i < mDatos.length(); i++) {
                JSONObject serializa = mDatos.getJSONObject(i);

                Category categoria = new Category(serializa.getInt("idcategory"),
                        serializa.getString("description"),
                        serializa.getString("imagen"),
                        serializa.getInt("state"));
                modelCategory.add(categoria);
            }

            if(modelCategory.size() <= 0)
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
