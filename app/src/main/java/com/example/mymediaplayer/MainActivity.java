package com.example.mymediaplayer;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAudioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayoutView();

        fetchAudioList();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            runTimePermission();
        }
    }

    private void fetchAudioList() {
        final ArrayList<File> audioFileList = findAllAudioFiles(Environment.getExternalStorageDirectory());
        AudioAdapter adapter = new AudioAdapter(audioFileList);
        recyclerViewAudioList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAudioList.setAdapter(adapter);
    }

    private void initLayoutView() {
        recyclerViewAudioList = findViewById(R.id.recyclerViewAudioList);
    }

    private void runTimePermission() {

    }

    private ArrayList<File> findAllAudioFiles(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findAllAudioFiles(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
}