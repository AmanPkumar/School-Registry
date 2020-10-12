package com.example.videoplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder>{
    Context context;
    ArrayList<File> videoArrayList;

    VideoAdapter(Context context,ArrayList<File> videoArrayList){
        this.context = context;
        this.videoArrayList = videoArrayList;
    }
    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list,parent,false);
        return new VideoAdapter.VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.videoName.setText(videoArrayList.get(position).getName());
        Bitmap image = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(position).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.videoImage.setImageBitmap(image);
    }

    @Override
    public int getItemCount() {
        if(videoArrayList.size() > 0){
            return videoArrayList.size();
        }else {
            return 1;
        }

    }
    static class VideoHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        TextView videoName;
        ImageView videoImage;

        VideoHolder(View view){
            super(view);
            mCardView = view.findViewById(R.id.card_view);
            videoName = view.findViewById(R.id.video_name);
            videoImage = view.findViewById(R.id.video_image);
        }
    }
}

