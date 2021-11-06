package com.pojo.wefourtech.menuoperation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pojo.wefourtech.R;
import com.pojo.wefourtech.model.ViewDetailDTO;

import java.util.List;

public class ViewDetailAdapter extends RecyclerView.Adapter<ViewDetailAdapter.ViewDetailsViewHolder> {


    Context mCtx;
    List<ViewDetailDTO> viewDetailDTOLisst;
    UpdateListener updateListener;


    public ViewDetailAdapter(Context mCtx, List<ViewDetailDTO> viewDetailDTOLisst, UpdateListener updateListener) {
        this.mCtx = mCtx;
        this.viewDetailDTOLisst = viewDetailDTOLisst;
        this.updateListener = updateListener;
    }

    public interface  UpdateListener{
        void onUpdateClickListener(ViewDetailDTO viewDetailDTO);

    }


    @NonNull
    @Override
    public ViewDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return viewDetailDTOLisst==null?0:viewDetailDTOLisst.size();
    }

    class ViewDetailsViewHolder extends RecyclerView.ViewHolder{

        TextView viewName,viewEmail,viewMobile;

        public ViewDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName=(TextView)itemView.findViewById(R.id.viewNameText);
            viewEmail=(TextView)itemView.findViewById(R.id.viewEmailText);
            viewMobile=(TextView)itemView.findViewById(R.id.viewMobileText);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ViewDetailDTO viewDetailDTO=viewDetailDTOLisst.get(getAdapterPosition());



                }
            });


        }
    }

}
