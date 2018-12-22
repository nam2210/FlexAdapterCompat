package com.hnam.myflexiableadapter.section;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hnam.myflexiableadapter.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFilterable;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.ISectionable;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by nampham on 12/22/18.
 */
public class SimpleItem extends AbstractFlexibleItem<SimpleItem.SimpleViewHolder>
    implements ISectionable<SimpleItem.SimpleViewHolder, HeaderItem> , IFilterable<String> {

    /* The header of this item */
    HeaderItem header;

    private String id;
    private String title;

    private SimpleItem(String id) {
        this.id = id;
    }

    public SimpleItem(String id, HeaderItem header) {
        this(id);
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleItem) {
            SimpleItem inItem = (SimpleItem) o;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public HeaderItem getHeader() {
        return header;
    }

    @Override
    public void setHeader(HeaderItem header) {
        this.header = header;
    }



    @Override
    public int getLayoutRes() {
        return R.layout.item_flexible;
    }

    @Override
    public SimpleViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new SimpleViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, SimpleViewHolder holder, int position, List<Object> payloads) {
        holder.title.setText("Data " + id);
    }



    @Override
    public boolean filter(String constraint) {
        return false;
    }

    static final class SimpleViewHolder extends FlexibleViewHolder {
        TextView title;

        public SimpleViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
