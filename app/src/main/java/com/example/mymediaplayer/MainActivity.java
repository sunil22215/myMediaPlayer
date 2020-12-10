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
   // ListView listView;
    RecyclerView audioListView;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioListView = (RecyclerView) findViewById(R.id.audioListView);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            runTimePermission();
        }
    }

    public void runTimePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        display();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findSong(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

   void display() {
       final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());
       items = new String[mySongs.size()];
       for (int i = 0; i < mySongs.size(); i++) {
           items[i] = mySongs.get(i).getName().toString().replace(".mp3", "").replace(".wav", "");

       }

       audioListView=(RecyclerView) findViewById(R.id.audioListView);
       AudioAdapter adapter=new AudioAdapter(items);
       audioListView.setLayoutManager(new LinearLayoutManager(this));
       audioListView.setAdapter(adapter);
   }
       /* ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songName = recyclerView.getChildItemId(position).toString();

                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("songs", mySongs);
                intent.putExtra("songame", songName);
                intent.putExtra("pos", position);
                startActivity(intent);

                startActivity(new Intent(getApplicationContext(), PlayerActivity.class)
                        .putExtra("songs", mySongs).putExtra("songame", songName)
                        .putExtra("pos", position));

            }
        });
    */
}