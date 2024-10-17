package com.example.lab2;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// VideoAdapter extends RecyclerView.Adapter to handle the display of video items in a RecyclerView
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    // List that holds the VideoItem objects to be displayed
    final private List<VideoItem> videoItems;

    // Constructor to initialize the VideoAdapter with a list of video items
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    // Inflates the layout for each video item and returns a new VideoViewHolder
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for individual video items
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent, false)
        );
    }

    // Binds video data
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    // Return the total number of video items
    @Override
    public int getItemCount() {
        return videoItems.size();
    }


    /**
     * ViewHolder class for managing individual video items in the RecyclerView
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder{
        // UI components
        TextView textVideoTitle1, textVideoDescription1, textVideoID;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * Constructor for VideoViewHolder
         * @param itemView The root view of the individual video item.
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind the UI components to their respective views
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textViewTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            textVideoID = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        // Set the video data
        void setVideoData(VideoItem videoItem) {
            // Set the video title and description
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            // Set the video URL in the VideoView
            videoView.setVideoPath(videoItem.videoUrl);
            textVideoID.setText("ID: " + Integer.toString(videoItem.videoId));

            // Set a listener to handle actions when the video is ready to play
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Once video is ready hide the progress bar and start playing the video
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    // Get the video aspect ratio
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    // Get the screen's aspect ratio
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();

                    // Calculate scale to adjust video for the screen size
                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        // If the video is wider scale horizontally
                        videoView.setScaleX(scale);

                    } else {
                        // If the video is taller scale vertically
                        videoView.setScaleY(1f / scale);
                    }
                }
            });

            // Set a listener to loop the video when it completes playing
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Restart the video once it's done playing
                    mp.start();
                }
            });
        }
    }
}
