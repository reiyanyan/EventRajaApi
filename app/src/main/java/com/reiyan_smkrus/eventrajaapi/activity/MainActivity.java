package com.reiyan_smkrus.eventrajaapi.activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Wilayah> mData;
    private RecyclerAdapter mAdapter;
    private LinearLayoutManager mLinearLayout;
    public static final String ID_PROPINSI = "id_propinsi";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Provinsi");
        mRecyclerView = findViewById(R.id.recyclerView);
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
        ServiceAPI mService = ClientAPI.getRetrofitClient().create(ServiceAPI.class);
        Call<RajaApi> mCall = mService.getProvinsi();
        mCall.enqueue(new Callback<RajaApi>() {
            @Override
            public void onResponse(Call<RajaApi> call, Response<RajaApi> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                mRecyclerView.setVisibility(View.VISIBLE);
                mData = response.body().getData();
                mAdapter = new RecyclerAdapter(MainActivity.this, mData);
                mLinearLayout = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(mLinearLayout);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.onItemClick(new ItemClick() {
                    @Override
                    public void onClick(View view, int position, int id) {
                        Intent mIntent = new Intent(MainActivity.this, KabupatenActivity.class);
                        mIntent.putExtra(ID_PROPINSI, id);
                        startActivity(mIntent);
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
