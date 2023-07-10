package com.example.mytodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class RvAdapter extends ListAdapter<Notes,RvAdapter.ViewHolder> {



    public  RvAdapter(){
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Notes> CALLBACK= new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDis().equals(newItem.getDis());

        }
    };

    


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_rv,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         Notes notes =getItem(position);

         holder.titelrv.setText(notes.getTitle());
         holder.descriptionrv.setText(notes.getDis());


    }

    public Notes getNotes(int position){

        return getItem(position);
    }



    public  class ViewHolder extends RecyclerView.ViewHolder{

         TextView titelrv,descriptionrv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             titelrv=itemView.findViewById(R.id.titlerv);
             descriptionrv=itemView.findViewById(R.id.descriptionrv);


        }
    }
}
