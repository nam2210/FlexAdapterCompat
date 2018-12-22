package com.hnam.myflexiableadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hnam.myflexiableadapter.section.DataFactory;
import com.hnam.myflexiableadapter.section.ExampleAdapter;

import java.util.ArrayList;
import java.util.List;


import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlexibleAdapter.useTag("MainActivityAdapter");
        FlexibleAdapter.enableLogs(eu.davidea.flexibleadapter.utils.Log.Level.DEBUG);

        // Optional but strongly recommended: Compose the initial list
//        List<IFlexible> myItems = getDatabaseList();
//        // Initialize the Adapter
//        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(myItems);

        //header data
        List<AbstractFlexibleItem> myItems = DataFactory.getData(100, 5);
        ExampleAdapter adapter= new ExampleAdapter(myItems, this);


        // Initialize the RecyclerView and attach the Adapter to it as usual
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(createNewLinearLayoutManager());
        rv.setHasFixedSize(true); //Size of RV will not change

        // More settings
        adapter.setStickyHeaderElevation(5)
                // Show Headers at startUp, 1st call, correctly executed, no warning log message!
                .setDisplayHeadersAtStartUp(true)
                .setStickyHeaders(true);

    }

    protected LinearLayoutManager createNewLinearLayoutManager() {
        return new SmoothScrollLinearLayoutManager(this);
    }

    public List<IFlexible> getDatabaseList() {
        List<IFlexible> list = new ArrayList<>();
        list.add(new MyItem("1", "Hello"));
        list.add(new MyItem("2", "World"));
        list.add(new MyItem("3", "Hello"));
        list.add(new MyItem("4", "World"));
        list.add(new MyItem("5", "Hello"));
        list.add(new MyItem("6", "World"));
        list.add(new MyItem("7", "Hello"));
        list.add(new MyItem("8", "World"));
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
