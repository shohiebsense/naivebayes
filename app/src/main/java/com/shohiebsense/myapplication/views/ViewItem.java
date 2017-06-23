package com.shohiebsense.myapplication.views;

import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.shohiebsense.myapplication.R;
import com.shohiebsense.myapplication.models.Contact;
import com.shohiebsense.myapplication.models.Human;

import java.util.List;

/**
 * Created by Shohieb on 5/13/2017.
 */

public class ViewItem<V extends View> extends AbstractItem<ViewItem, ContactItem>   {


    int type;
    Human human;

    public ViewItem(Human human) {
        this.human = human;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.id_contact;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_contact;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ContactItem viewHolder, List payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        viewHolder.bind(human);
    }

    @Override
    public ContactItem getViewHolder(View v) {
        return new ContactItem(v);
    }
}
