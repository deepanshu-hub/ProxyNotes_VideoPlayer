package com.example.videoplayer;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class videoadapter extends RecyclerView.Adapter<videoadapter.myviewholder>
{

    ArrayList<videomodel> videos;

    public videoadapter(ArrayList<videomodel> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
           holder.setdata(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    class myviewholder extends RecyclerView

            .ViewHolder {

        VideoView videoView;
        TextView title, desc;
        ProgressBar pbar;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoview);
            title = itemView.findViewById(R.id.textVideoTitle);
            desc = itemView.findViewById(R.id.textVideoDesprition);
            pbar = itemView.findViewById(R.id.videoProgressBar);
        }

        void setdata(videomodel obj) {
            videoView.setVideoPath(obj.getUrl());
            title.setText(obj.getTitle());
            desc.setText(obj.getDesc());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pbar.setVisibility(View.GONE);
                    mp.start();
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }
}
