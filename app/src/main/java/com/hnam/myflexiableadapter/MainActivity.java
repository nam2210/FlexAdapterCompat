package com.hnam.myflexiableadapter;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hnam.myflexiableadapter.loadmore.ProgressItem;
import com.hnam.myflexiableadapter.section.DataFactory;
import com.hnam.myflexiableadapter.section.ExampleAdapter;
import com.hnam.myflexiableadapter.section.HeaderItem;
import com.hnam.myflexiableadapter.section.SimpleItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;


import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.flexibleadapter.items.ISectionable;

public class MainActivity extends AppCompatActivity {

    ExampleAdapter mAdapter;

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
        final List<AbstractFlexibleItem> myItems = DataFactory.getData(50, 5);
        mAdapter= new ExampleAdapter(myItems, this);


        // Initialize the RecyclerView and attach the Adapter to it as usual
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(createNewLinearLayoutManager());
        rv.setHasFixedSize(true); //Size of RV will not change

        // More settings
        mAdapter.setStickyHeaderElevation(5)
                // Show Headers at startUp, 1st call, correctly executed, no warning log message!
                .setDisplayHeadersAtStartUp(true)
                .setStickyHeaders(true);

        mAdapter.setEndlessScrollListener(new FlexibleAdapter.EndlessScrollListener() {
            @Override
            public void noMoreLoad(int newItemsSize) {
                Log.e(TAG, "setEndlessScrollListener() -> noMoreLoad=" + newItemsSize);
                mAdapter.setEndlessProgressItem(new ProgressItem());
            }

            @Override
            public void onLoadMore(int lastPosition, int currentPage) {
                Log.e(TAG, "setEndlessScrollListener() -> lastPosition=" + lastPosition + " currentPage=%s" + currentPage);
                HeaderItem h = DataFactory.newHeader(5);
                SimpleItem item = DataFactory.newSimpleItem(10001, h);
                List<AbstractFlexibleItem> data = new ArrayList<>();
                data.add(item);

                HeaderItem h2 = DataFactory.newHeader(lastPosition / 5 + 1);
                for(int i = 1; i < 21; i++){
                    SimpleItem item1 = DataFactory.newSimpleItem(1000+i, h);
                    data.add(item1);
                }

                onLoadMoreComplete(data);
            }
        }, new ProgressItem()).setEndlessScrollThreshold(5)
        .setEndlessPageSize(5); // Default=1;

    }

    public void onLoadMoreComplete(final List<AbstractFlexibleItem> newItems) {
        // Callback the Adapter to notify the change:
        // - New items will be added to the end of the main list and progressItem
        //   will be hidden.
        // - Instead, when list is null or empty, and limits are reached, Endless
        //   scroll will be disabled. To enable again, you must call
        //   setEndlessProgressItem(@Nullable T progressItem).
        Log.e(TAG,"load more >>>>>>");
        int lastPosition = mAdapter.getItemCount() - 2;
        AbstractFlexibleItem item = mAdapter.getItem(lastPosition);
        HeaderItem h = null;
        if (item instanceof SimpleItem){
            h = ((SimpleItem) item).getHeader();
        } else if (item instanceof HeaderItem){
            h = (HeaderItem) item;
        }

        if (h == null){
            return;
        }
        for (int i = 0; i < newItems.size(); i++){
            AbstractFlexibleItem ni = newItems.get(i);
            if (ni instanceof SimpleItem && ((SimpleItem) ni).getHeader().equals(h)){
                mAdapter.addItemToSection((ISectionable) ni, h, mAdapter.getItemCount() - 1 + i);
            }
        }


        mAdapter.onLoadMoreComplete(Collections.<AbstractFlexibleItem>emptyList(), 0);





        // Notify user
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
