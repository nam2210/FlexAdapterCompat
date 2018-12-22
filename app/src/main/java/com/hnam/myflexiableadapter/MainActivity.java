package com.hnam.myflexiableadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Optional but strongly recommended: Compose the initial list
        List<IFlexible> myItems = getDatabaseList();

        // Initialize the Adapter
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(myItems);

        // Initialize the RecyclerView and attach the Adapter to it as usual
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter.addListener(clickListenerAdapter1);

    }

    public List<IFlexible> getDatabaseList() {
        List<IFlexible> list = new ArrayList<>();
        list.add(new MyItem("1", "Hello"));
        list.add(new MyItem("2", "World"));
        return list;
    }
    private static final String TAG = MainActivity.class.getSimpleName();

    private FlexibleAdapter.OnItemClickListener clickListenerAdapter1 =
            new FlexibleAdapter.OnItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position) {
                    Log.e(TAG, "click here" + position);
                    return false;
                }
            };

    private MyListener objectListener = new MyListener() {
        @Override
        public void onClick(int pos) {

        }
    };

    private interface MyListener{
        void onClick(int pos);
    }
}
