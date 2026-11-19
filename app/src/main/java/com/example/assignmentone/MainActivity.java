package com.example.assignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * CampusLife - main screen of the University of Waikato companion app.
 *
 * Shows a welcome banner and four topic tiles (Facilities, Events, Clubs,
 * Support). Tapping a tile opens DetailActivity with the matching content
 * (Parts E-G); the topic index doubles as the unique identifier passed to
 * the detail screen.
 */
public class MainActivity extends AppCompatActivity {

    // Resource ids of the four topic ImageButtons (order matches arrays.xml).
    private final int[] topicButtonIds = {
            R.id.btn_facilities,
            R.id.btn_events,
            R.id.btn_clubs,
            R.id.btn_support
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupTopicButtons();
    }

    /**
     * Part E: attach an onClick listener to each of the four ImageButtons.
     * The loop walks the buttons in the same order as the arrays in
     * arrays.xml, so each listener knows which topic it belongs to
     * without needing any if statements or switch cases.
     */
    private void setupTopicButtons() {
        for (int i = 0; i < topicButtonIds.length; i++) {
            final int topicIndex = i;
            ImageButton button = findViewById(topicButtonIds[i]);
            button.setOnClickListener(v -> onTopicClicked(topicIndex, button));
        }
    }

    /**
     * Called when one of the four topic ImageButtons is clicked.
     * Part E: shows a Toast so the click can be verified while testing.
     * Part G: builds an explicit Intent that starts the DetailActivity and
     * passes a unique identifier (the topic index) plus the topic name,
     * which DetailActivity uses to work out which content to display.
     */
    private void onTopicClicked(int topicIndex, View view) {
        String topicName = view.getContentDescription().toString();
        Toast.makeText(this, topicName + " clicked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, topicIndex);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_NAME, topicName);
        startActivity(intent);
    }
}
