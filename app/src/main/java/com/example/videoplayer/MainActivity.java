package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    VideoAdapter mVideoAdapter;
    public static ArrayList<File> mVideoList = new ArrayList<>();
    public static int PERMISSION_REQUEST = 1;
    File directory;
    boolean mBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        directory = new File("/mnt/");
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this,2);
        
        mRecyclerView.setLayoutManager(manager);
        
        seekPermission();
    }

    private void seekPermission() {
        if((ContextCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){

            if((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this , Manifest.permission.READ_EXTERNAL_STORAGE))){

            }else {
                ActivityCompat.requestPermissions(MainActivity.this , new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }
        else {
            mBoolean = true;
            getFile(directory);
            mVideoAdapter = new VideoAdapter(getApplicationContext(),mVideoList);
            mRecyclerView.setAdapter(mVideoAdapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                mBoolean = true;
                getFile(directory);
                mVideoAdapter = new VideoAdapter(getApplicationContext(),mVideoList);
                mRecyclerView.setAdapter(mVideoAdapter);

            }
            else {
                Toast.makeText(getApplicationContext(),"Please Allow the permission",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<File> getFile(File mDirectory) {
        File[] fileList = mDirectory.listFiles();

        if(fileList!= null && fileList.length > 0){
            for(int i = 0; i < fileList.length; i++){
                if(fileList[i].isDirectory()){
                    getFile(fileList[i]);
                }
                else{
                    mBoolean = false;
                    if(fileList[i].getName().endsWith(".mp4")){

                        for(int j = 0; j < mVideoList.size(); j++){
                            if(mVideoList.get(j).getName().equals(fileList[i].getName())){
                                mBoolean = true;
                            }
                        }
                        if(mBoolean){
                            mBoolean = false;
                        }else {
                            mVideoList.add(fileList[i]);
                        }
                    }
                }
            }
        }
        return mVideoList;
    }
}