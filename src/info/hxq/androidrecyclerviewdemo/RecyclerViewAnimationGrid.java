package info.hxq.androidrecyclerviewdemo;


import info.hxq.androidrecyclerviewdemo.animation.ScaleInOutItemAnimator;
import info.hxq.androidrecyclerviewdemo.animation.SlideInOutBottomItemAnimator;
import info.hxq.androidrecyclerviewdemo.animation.SlideInOutLeftItemAnimator;
import info.hxq.androidrecyclerviewdemo.animation.SlideInOutRightItemAnimator;
import info.hxq.androidrecyclerviewdemo.animation.SlideInOutTopItemAnimator;
import info.hxq.androidrecyclerviewdemo.animation.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAnimationGrid extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<RecyclerAdapter.ViewHolder> mAdapter;
    private GridLayoutManager mLayoutManager;
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N"};
    private final ArrayList<String> dataSet = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlist);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mLayoutManager.setSpanCount(3);
        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataSet.addAll(Arrays.asList(LETTERS));

        mAdapter = new RecyclerAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private final List<String> mDataset;

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        public RecyclerAdapter(List<String> myDataset) {
            mDataset = myDataset;
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
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mDataset.get(position));
            holder.mTextView.setBackgroundColor(Color.argb(255, new Random().nextInt(255),
                    new Random().nextInt(255), new Random().nextInt(255)));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    public void controlClick(View v) {
        int vId = v.getId();
        setAnimator(new Random().nextInt(6));
        int index = dataSet.size();
        switch (vId) {
            case R.id.add:
                dataSet.add(index, String.valueOf(new Random().nextInt(10)));
                mAdapter.notifyItemInserted(index);
                break;
            case R.id.delete:
                if (index > 1) {
                    dataSet.remove(index - 1);
                    mAdapter.notifyItemRemoved(index - 1);
                }
                break;
        }
    }

    public void setAnimator(int index) {
        switch (index) {
            case 0:
                mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
                break;
            case 1:
                mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
                break;
            case 2:
                mRecyclerView.setItemAnimator(new SlideInOutTopItemAnimator(mRecyclerView));
                break;
            case 3:
                mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));
                break;
            case 4:
                mRecyclerView.setItemAnimator(new ScaleInOutItemAnimator(mRecyclerView));
                break;
            case 5:
                mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
                break;
        }
    }
}
