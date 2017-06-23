package com.shohiebsense.myapplication.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shohiebsense.myapplication.R;
import com.shohiebsense.myapplication.models.Contact;
import com.shohiebsense.myapplication.models.Human;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shohieb on 5/13/2017.
 */

public class ContactItem extends RecyclerView.ViewHolder {
    private static Context context;
    @BindView(R.id.textview_name)
    TextView nameTextView;

    @BindView(R.id.textview_email)
    TextView emailTextView;

    @BindView(R.id.textview_phone)
    TextView phoneTextView;


    public ContactItem(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    void bind(Human human){
        nameTextView.setText(human.getName());
        emailTextView.setText(human.getHeight()+"");
        phoneTextView.setText(human.getGender());
    }

}
