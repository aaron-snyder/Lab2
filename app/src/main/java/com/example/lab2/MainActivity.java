package com.example.lab2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final int MAX_INT = Integer.MAX_VALUE;
    // Map to store video IDs and video items
    private Map<Integer, VideoItem> videoMap = new HashMap<>();
    // Random number generator
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for this activity
        EdgeToEdge.enable(this);

        // Set the layout resource for the activity
        setContentView(R.layout.activity_main);

        // Adjust the padding of the root view to account for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get the system bar insets
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Set padding based on the system bar insets
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the ViewPager2 widget in the layout
        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        // Create a list to hold the video items that will be displayed
        List<VideoItem> videoItemsList = new ArrayList<>();

        // Add videos with unique IDs
        videoItemsList.add(createVideoItem("https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Sam%20Sulek%20Funny%20Moments.mp4?alt=media&token=5ec1eccb-6d29-4b0c-93cb-9406c99cfacd",
                "Sam Sulek Funny Moment",
                "A meme about birthdays with one of Sam Sulek's videos"));

        videoItemsList.add(createVideoItem("https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Ronnie%20Coleman%20Yeah%20Buddy.mp4?alt=media&token=69d1ffb3-a978-4390-90a0-ad539a9de3c8",
                "Lightweight Baby",
                "One of the greatest bodybuilders of all time, Ronnie Coleman, squatting some light weight"));

        videoItemsList.add(createVideoItem("https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/I%20Still%20Standing%20Space%20Marine%20Sings%20%23edit%20%23warhammer40k%20%23vs%20%23battle%20%23subscribe%20%23shorts%20%23short%20%23music.mp4?alt=media&token=8e4cd128-92b7-45bd-b7ea-0332e1af4c96",
                "Space Marines",
                "Funny meme about a space marine not knowing he's on an open mic and singing a hype song"));

        videoItemsList.add(createVideoItem("https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Guts%20_%20Berserk%20%5BEDIT%5D%20clips%20from_%20%40sweetheartist%20%40eldritx%20%40markReymer.mp4?alt=media&token=ceba5985-292f-40f5-bafa-264b4e75b16a",
                "Berserk Rage",
                "A cool edit from a great anime Berserk of the protagonist Guts raging"));

        videoItemsList.add(createVideoItem("https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Are%20you%20maximizing%20your%20RAM%20speed_.mp4?alt=media&token=ee51802d-638b-49a2-ba15-2018d0809b77",
                "Maximize RAM",
                "A cool tip on how to optimize RAM usage"));

        // Set the adapter for the ViewPager2 with the list of video items
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
    // Method to create video item with unique ID
    private VideoItem createVideoItem(String url, String title, String description) {
        VideoItem videoItem = new VideoItem();
        videoItem.videoUrl = url;
        videoItem.videoTitle = title;
        videoItem.videoDescription = description;

        // Generate unique ID for the video
        int videoId = generateUniqueId();
        // Add the video to the map with the generated ID
        videoMap.put(videoId, videoItem);
        // Set video items unique id
        videoItem.videoId = videoId;

        return videoItem;
    }

    // Method to generate a unique ID
    private int generateUniqueId() {
        int id;
        do {
            // Generate a random ID between 0 and Integer.MAX_VALUE
            id = random.nextInt(MAX_INT);
        } while (videoMap.containsKey(id)); // Ensure the ID is unique
        return id;
    }
}
