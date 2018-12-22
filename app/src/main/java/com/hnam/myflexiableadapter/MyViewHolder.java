package com.hnam.myflexiableadapter;

import android.view.View;
import android.widget.TextView;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by nampham on 12/22/18.
 */
public class MyViewHolder extends FlexibleViewHolder {
    public TextView mTitle;

    public MyViewHolder(View view, final FlexibleAdapter adapter) {
        super(view, adapter);
        mTitle = (TextView) view.findViewById(R.id.title);

    }
}
