package com.shohiebsense.myapplication.controllers;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.shohiebsense.myapplication.R;
import com.shohiebsense.myapplication.models.Contact;
import com.shohiebsense.myapplication.models.Human;
import com.shohiebsense.myapplication.views.ViewItem;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_contacts)
    RecyclerView contactRecyclerView;
    @BindView(R.id.edittext_jawaban)
    EditText jawabanEditText;

    FastItemAdapter fastItemAdapter = new FastItemAdapter();
    List<Human> humanList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        fastItemAdapter.withSelectable(true);
        HumanController contactControllers = new HumanController().init();
        contactControllers.addHumans();
        initRecyclerView();
        contactRecyclerView.setAdapter(fastItemAdapter);
        createItems(contactControllers.getAllHumans());



        //percobaan laki2
        double rata2 = HumanController.rata2(contactControllers.hitungLakiTinggi());
        double PLpendek = HumanController.normalDistribution(contactControllers.hitungLakiTinggi(),173);
        double PLtinggi = HumanController.normalDistribution(contactControllers.hitungLakiPendek(),173);

        if(PLpendek > PLtinggi)
        {
            jawabanEditText.setText("Pendek \n"+ HumanController.getRounded(PLpendek) + " (Plpendek) > (Pltinggi)" +  HumanController.getRounded(PLtinggi));
        }
        else{
            jawabanEditText.setText("Tinggi \n" +  HumanController.getRounded(PLtinggi) + " (PLtinggi) > (Plpendek) "+  HumanController.getRounded(PLpendek));
        }



        double anu = HumanController.normalDistribution(null,74);

        //rata2 perempuan tinggi

        Log.e("shohiebsense  ",anu+ "");

    }

    public void initRecyclerView(){
        contactRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contactRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        contactRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void createItems(List<Human> humanList) {
        this.humanList = humanList;
        for (int i = 0; i < humanList.size(); i++) {
            Human human = humanList.get(i);
            ViewItem viewItem = new ViewItem(human);
            Log.e("shohiebsense for ","anu");
            fastItemAdapter.add(viewItem);
        }
        fastItemAdapter.notifyAdapterDataSetChanged();
    }

    public void onRadioButtonClicked(View view) {

    }
}
