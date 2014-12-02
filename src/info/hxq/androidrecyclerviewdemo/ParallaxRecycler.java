package info.hxq.androidrecyclerviewdemo;

import info.hxq.androidrecyclerviewdemo.ParallaxRecyclerAdapter.OnParallaxScroll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ParallaxRecycler extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<RecyclerAdapter.ViewHolder> mAdapter;
    private LinearLayoutManager mLayoutManager;
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N"};
    private final ArrayList<String> dataSet = new ArrayList<String>();
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // requestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        mActionBar = this.getSupportActionBar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dataSet.addAll(Arrays.asList(LETTERS));


        ParallaxRecyclerAdapter<String> mAdapter = new ParallaxRecyclerAdapter<String>(dataSet);
        mAdapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods() {

            class ViewHolder extends RecyclerView.ViewHolder {
                public TextView mTextView;

                public ViewHolder(TextView v) {
                    super(v);
                    mTextView = v;
                }
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                ((ViewHolder) viewHolder).mTextView.setText(dataSet.get(i));
                ((ViewHolder) viewHolder).mTextView.setBackgroundColor(Color.argb(255,
                        new Random().nextInt(255), new Random().nextInt(255),
                        new Random().nextInt(255)));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.vlist_item_recycler, viewGroup, false));
            }

            @Override
            public int getItemCount() {
                return dataSet.size();
            }

        });
        mAdapter.setParallaxHeader(
                LayoutInflater.from(this).inflate(R.layout.parallaxview, mRecyclerView, false),
                mRecyclerView);
        mAdapter.setOnParallaxScroll(new OnParallaxScroll() {

            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                ColorDrawable colorDrawable = new ColorDrawable(Color.argb(255, 0, 180, 255));
                colorDrawable.setAlpha(Math.round(percentage * 255));
                DrawerLayout mDrawerLayout = new DrawerLayout(ParallaxRecycler.this);
                mDrawerLayout.setBackgroundColor(Color.TRANSPARENT);
                mActionBar.setCustomView(mDrawerLayout);
                mActionBar.setBackgroundDrawable(colorDrawable);
            }
        });


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
}
