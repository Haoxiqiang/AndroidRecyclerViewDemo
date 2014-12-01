package info.hxq.androidrecyclerviewdemo;

import info.hxq.androidrecyclerviewdemo.RecyclerAdapter.OnItemViewHolderClickLisenter;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MainActivity extends ActionBarActivity implements OnItemViewHolderClickLisenter {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<RecyclerAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final Class[] TARGET = {RecyclerViewVerticalList.class};
    private static final String[] TARGETSTRING = {"RecyclerViewVerticalList"};
    private final ArrayList<String> dataSet = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // 使用此设置来提高性能，如果你知道，改变内容不改变RecyclerView的布局大小
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dataSet.addAll(Arrays.asList(TARGETSTRING));

        mAdapter = new RecyclerAdapter(dataSet, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent();
        intent.setClass(this, TARGET[position]);
        startActivity(intent);
    }
}
