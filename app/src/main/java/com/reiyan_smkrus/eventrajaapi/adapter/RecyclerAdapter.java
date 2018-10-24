package com.reiyan_smkrus.eventrajaapi.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.reiyan_smkrus.eventrajaapi.ItemClick;
import com.reiyan_smkrus.eventrajaapi.R;
import com.reiyan_smkrus.eventrajaapi.model.Wilayah;
import java.util.List;
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private View mView;
    private Context mContext;
    private List<Wilayah> mData;
    private ItemClick mClick;
    public RecyclerAdapter(Context mContext, List<Wilayah> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextNama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextNama = itemView.findViewById(R.id.txtNama);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mClick.onClick(itemView, getAdapterPosition(), mData.get(getAdapterPosition()).getId());
        }
    }
    public void onItemClick(ItemClick mClick){ this.mClick = mClick; }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        mView = mInflater.inflate(R.layout.blueprint_recycler, viewGroup, false);
        return new ViewHolder(mView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Wilayah mModel = mData.get(i);
        viewHolder.mTextNama.setText(mModel.getName());
    }
    @Override
    public int getItemCount() { return mData.size(); }
}
