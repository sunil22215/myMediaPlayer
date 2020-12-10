package com.example.mymediaplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private final ArrayList<File> items;

    public AudioAdapter(ArrayList<File> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItems = layoutInflater.inflate(R.layout.row_audio_list, parent, false);
        return new ViewHolder(listItems);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String string = items.get(position).toString();
        holder.textViewAudioName.setText(string);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewAudioName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAudioName = itemView.findViewById(R.id.textViewAudioName);
        }
    }
}
