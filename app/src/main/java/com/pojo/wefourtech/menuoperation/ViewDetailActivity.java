package com.pojo.wefourtech.menuoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pojo.wefourtech.R;
import com.pojo.wefourtech.model.ViewDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailActivity extends AppCompatActivity {

    RecyclerView viewDetailsRecyclerView;
    ViewDetailAdapter viewDetailAdapter;
    List<ViewDetailDTO> viewDetailDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        viewDetailsRecyclerView = (RecyclerView) findViewById(R.id.viewDetailRecyclerView);
        viewDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(ViewDetailActivity.this));
        viewDetailsRecyclerView.setHasFixedSize(true);

        viewDetailDTOList=new ArrayList<>();

//        viewDetailAdapter=new ViewDetailAdapter();

        viewDetailsRecyclerView.setAdapter(viewDetailAdapter);

        doGetDetails();



    }

    private void doGetDetails() {



    }
}