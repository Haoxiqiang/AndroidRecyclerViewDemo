package info.hxq.androidrecyclerviewdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewList extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<RecyclerAdapter.ViewHolder> mAdapter;
    private LinearLayoutManager mLayoutManager;
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N"};
    private final ArrayList<String> dataSet = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlist);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setStackFromEnd(false);
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
        switch (vId) {
            case R.id.add:
                dataSet.add(String.valueOf(new Random().nextInt(10)));
                break;
            case R.id.delete:
                if (dataSet.size() > 1) {
                    dataSet.remove(dataSet.size() - 1);
                }
                break;

            default:
                break;
        }
        mAdapter.notifyDataSetChanged();
    }
}
