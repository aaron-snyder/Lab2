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

public class MainActivity extends AppCompatActivity {

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

        // Create and add the first video
        VideoItem samFunnyMoments = new VideoItem();
        samFunnyMoments.videoUrl = "https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Sam%20Sulek%20Funny%20Moments.mp4?alt=media&token=5ec1eccb-6d29-4b0c-93cb-9406c99cfacd";
        samFunnyMoments.videoTitle = "Sam Sulek Funny Moment";
        samFunnyMoments.videoDescription = "A meme about birthdays with one of Sam Sulek's videos";
        videoItemsList.add(samFunnyMoments); // Add this video item to the list.

        // Create and add the second video
        VideoItem lightweight = new VideoItem();
        lightweight.videoUrl = "https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Ronnie%20Coleman%20Yeah%20Buddy.mp4?alt=media&token=69d1ffb3-a978-4390-90a0-ad539a9de3c8";
        lightweight.videoTitle = "Lightweight Baby";
        lightweight.videoDescription = "One of the greatest bodybuilders of all time, Ronnie Coleman, squatting some light weight";
        videoItemsList.add(lightweight); // Add this video item to the list.

        // Create and add the third video
        VideoItem spaceMarine = new VideoItem();
        spaceMarine.videoUrl = "https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/I%20Still%20Standing%20Space%20Marine%20Sings%20%23edit%20%23warhammer40k%20%23vs%20%23battle%20%23subscribe%20%23shorts%20%23short%20%23music.mp4?alt=media&token=8e4cd128-92b7-45bd-b7ea-0332e1af4c96";
        spaceMarine.videoTitle = "Space Marines";
        spaceMarine.videoDescription = "Funny meme about a space marine not knowing he's on an open mic and singing a hype song";
        videoItemsList.add(spaceMarine); // Add this video item to the list.

        // Create and add the fourth video
        VideoItem gutsRage = new VideoItem();
        gutsRage.videoUrl = "https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Guts%20_%20Berserk%20%5BEDIT%5D%20clips%20from_%20%40sweetheartist%20%40eldritx%20%40markReymer.mp4?alt=media&token=ceba5985-292f-40f5-bafa-264b4e75b16a";
        gutsRage.videoTitle = "Berserk Rage";
        gutsRage.videoDescription = "A cool edit from a great anime Berserk of the protagonist Guts raging";

        // Create and add the fifth video
        VideoItem maxRam = new VideoItem();
        maxRam.videoUrl = "https://firebasestorage.googleapis.com/v0/b/cs460-bd7cf.appspot.com/o/Are%20you%20maximizing%20your%20RAM%20speed_.mp4?alt=media&token=ee51802d-638b-49a2-ba15-2018d0809b77";
        maxRam.videoTitle = "Maximize RAM";
        maxRam.videoDescription = "A cool tip on how to optimize RAM usage";
        videoItemsList.add(maxRam); // Add this video item to the list.

        // Set the adapter for the ViewPager2 with the list of video items
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}
