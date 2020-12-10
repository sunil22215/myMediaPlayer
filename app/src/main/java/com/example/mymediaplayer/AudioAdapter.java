package com.example.mymediaplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private String[] items;

    public AudioAdapter(String[] items){
        this.items=items;
    }
    @NonNull
    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItems=layoutInflater.inflate(R.layout.audio_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItems);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final  String string= items[position];
        holder.audioName.setText(items[position]);


    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView audioName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.audioName=(TextView) itemView.findViewById(R.id.audioName);
        }
    }
}
