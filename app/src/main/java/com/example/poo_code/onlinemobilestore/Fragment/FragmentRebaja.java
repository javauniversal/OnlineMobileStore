package com.example.poo_code.onlinemobilestore.Fragment;

import android.app.Activity;
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
import com.example.poo_code.onlinemobilestore.Adapter.CostomAdapterProduct;
import com.example.poo_code.onlinemobilestore.Entities.ListProduct;
import com.example.poo_code.onlinemobilestore.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FragmentRebaja extends BaseVolleyFragment {

    private ListView multiColumnList;
    private ImageView mErrorView;
    private ListProduct dataRebaj;
    private static Activity activity;

    public static FragmentRebaja newInstance(Bundle bundle) {
        FragmentRebaja fragment = new FragmentRebaja();
        fragment.setArguments(bundle);
        return fragment;
    }

    public FragmentRebaja() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle instanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generic, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        multiColumnList = (ListView) getActivity().findViewById(R.id.card_list);
        mErrorView = (ImageView) getActivity().findViewById(R.id.error_data);

        activity = getActivity();

        setupGrid();
    }

    private void setupGrid() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"listRebajas");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(final String response) {
                        if (!parseJSON(response)) {
                            Toast.makeText(getActivity(), "No se encontraron datos", Toast.LENGTH_LONG).show();
                            mErrorView.setVisibility(View.VISIBLE);
                        }else{
                            CostomAdapterProduct adapter = new CostomAdapterProduct(activity, dataRebaj);
                            adapter.enableAutoMeasure(250);
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
            Gson gson = new Gson();
            dataRebaj = gson.fromJson(json, ListProduct.class);
        }catch (IllegalStateException ex) {
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
