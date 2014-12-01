package info.hxq.androidrecyclerviewdemo;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final List<String> mDataset;
    private final OnItemViewHolderClickLisenter mLisenter;

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

    }

    public RecyclerAdapter(List<String> myDataset, OnItemViewHolderClickLisenter lisenter) {
        this.mDataset = myDataset;
        this.mLisenter = lisenter;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v =
                (TextView) LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.vlist_item_recycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.mTextView.setBackgroundColor(Color.argb(255, new Random().nextInt(255),
                new Random().nextInt(255), new Random().nextInt(255)));
        holder.mTextView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLisenter.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemViewHolderClickLisenter {
        public void onClick(View v, int position);
    }

}
