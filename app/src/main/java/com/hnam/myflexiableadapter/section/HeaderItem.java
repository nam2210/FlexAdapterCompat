package com.hnam.myflexiableadapter.section;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hnam.myflexiableadapter.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by nampham on 12/22/18.
 */
public class HeaderItem extends AbstractHeaderItem<HeaderItem.HeaderViewHolder> {

    private String id;
    private String title;

    public HeaderItem(String id) {
        super();
        this.id = id;
        this.title = String.format("title %s", id);
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof HeaderItem) {
            HeaderItem inItem = (HeaderItem) inObject;
            return this.getId().equals(inItem.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.item_header;
    }

    @Override
    public HeaderViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new HeaderViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, HeaderViewHolder holder, int position, List<Object> payloads) {
        if (payloads.size() > 0) {
            Log.e(this.getClass().getSimpleName(), "HeaderItem " + id + " Payload " + payloads);
        } else {
            holder.tvTitle.setText(getTitle());
        }

    }

    static class HeaderViewHolder extends FlexibleViewHolder {

        TextView tvTitle;

        public HeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public String toString() {
        return "HeaderItem[id=" + id +
                ", title=" + title + "]";
    }
}
