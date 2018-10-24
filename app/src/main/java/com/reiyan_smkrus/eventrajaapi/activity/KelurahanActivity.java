package com.reiyan_smkrus.eventrajaapi.activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reiyan_smkrus.eventrajaapi.ItemClick;
import com.reiyan_smkrus.eventrajaapi.R;
import com.reiyan_smkrus.eventrajaapi.adapter.RecyclerAdapter;
import com.reiyan_smkrus.eventrajaapi.model.RajaApi;
import com.reiyan_smkrus.eventrajaapi.model.Wilayah;
import com.reiyan_smkrus.eventrajaapi.network.ClientAPI;
import com.reiyan_smkrus.eventrajaapi.network.ServiceAPI;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class KelurahanActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private LinearLayoutManager mLinearLayout;
    private List<Wilayah> mData;
    private int mId;
    private LinearLayout mLinear;
    private Snackbar mSnackbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelurahan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kelurahan");
        mRecyclerView = findViewById(R.id.recyclerView);
        mLinear = findViewById(R.id.linearLayout);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue), getResources().getColor(R.color.green), getResources().getColor(R.color.orange));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.setVisibility(View.INVISIBLE);
                loadData();
            }
        });
        loadData();
    }
    private void loadData(){
        mId = getIntent().getIntExtra(KecamatanActivity.ID_KECAMATAN, 0);
        final ServiceAPI mService = ClientAPI.getRetrofitClient().create(ServiceAPI.class);
        Call<RajaApi> mCall = mService.getKelurahan(mId);
        mCall.enqueue(new Callback<RajaApi>() {
            @Override
            public void onResponse(Call<RajaApi> call, final Response<RajaApi> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                mRecyclerView.setVisibility(View.VISIBLE);
                mData = response.body().getData();
                mAdapter = new RecyclerAdapter(KelurahanActivity.this, mData);
                mLinearLayout = new LinearLayoutManager(KelurahanActivity.this);
                mRecyclerView.setLayoutManager(mLinearLayout);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.onItemClick(new ItemClick() {
                    @Override
                    public void onClick(View view, int position, int id) {
                        String mNamaKelurahan = response.body().getData().get(position).getName();
                        mSnackbar = Snackbar.make(mLinear, mNamaKelurahan, Snackbar.LENGTH_SHORT);
                        mSnackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSnackbar.dismiss();
                            }
                        });
                        mSnackbar.show();
                    }
                });
            }
            @Override
            public void onFailure(Call<RajaApi> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
            }
        });
    }
}
