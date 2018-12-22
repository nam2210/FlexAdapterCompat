package com.hnam.myflexiableadapter.section;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;

/**
 * Created by nampham on 12/22/18.
 */
public class DataFactory {

    public static List<AbstractFlexibleItem> getData(int size, int headers){
        List<AbstractFlexibleItem> mItems = new ArrayList<>();
        HeaderItem header = null;
        mItems.clear();
        int lastHeaderId = 0;
        for (int i = 0; i < size; i++) {
            header = i % Math.round(size / headers) == 0 ? newHeader(++lastHeaderId) : header;
            mItems.add(newSimpleItem(i + 1, header));
        }
        return mItems;
    }

    public static HeaderItem newHeader(int id){
        return new HeaderItem(String.valueOf(id));
    }

    public static SimpleItem newSimpleItem(int id, HeaderItem header){
        return new SimpleItem(String.valueOf(id), header);
    }
}
