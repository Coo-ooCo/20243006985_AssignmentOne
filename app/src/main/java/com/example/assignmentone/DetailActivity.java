package com.example.assignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Part F + H: Detail screen shown when one of the topic ImageButtons is
 * tapped. Receives a unique topic identifier through the Intent (Part G)
 * and uses it to look up the matching title, content and image stored in
 * the three arrays in arrays.xml (Part H) - no if statements or switch
 * cases are needed because the identifier is used directly as the array
 * index.
 */
public class DetailActivity extends AppCompatActivity {

    /** Unique identifier extra: the index of the clicked topic (0-3). */
    public static final String EXTRA_TOPIC_INDEX = "com.example.assignmentone.EXTRA_TOPIC_INDEX";

    /** Extra carrying the display name of the clicked topic (e.g. "Facilities"). */
    public static final String EXTRA_TOPIC_NAME = "com.example.assignmentone.EXTRA_TOPIC_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        displayTopicContent();
    }

    /**
     * Part H: populates the DetailActivity with the dummy data stored in
     * arrays.xml. The unique identifier passed through the Intent is the
     * index into the three parallel arrays (titles, content, images), so
     * the correct values are retrieved directly without branching.
     */
    private void displayTopicContent() {
        Intent intent = getIntent();
        int topicIndex = intent.getIntExtra(EXTRA_TOPIC_INDEX, 0);
        String topicName = intent.getStringExtra(EXTRA_TOPIC_NAME);

        // Retrieve the three arrays from arrays.xml.
        String[] titles = getResources().getStringArray(R.array.string_array_titles);
        String[] contents = getResources().getStringArray(R.array.string_array_content);
        String[] images = getResources().getStringArray(R.array.string_array_images);

        // Pick the values that match the clicked topic.
        String title = titles[topicIndex].trim();
        String content = contents[topicIndex].trim();
        String imageName = images[topicIndex].trim();

        // Resolve the image resource name (e.g. "img_btn_facilities") to a drawable id.
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        // Populate the UI.
        TextView headerTitle = findViewById(R.id.detail_header_title);
        TextView titleText = findViewById(R.id.detail_title);
        TextView contentText = findViewById(R.id.detail_content);
        ImageView iconImage = findViewById(R.id.detail_icon);

        headerTitle.setText(topicName);
        titleText.setText(title);
        contentText.setText(content);
        iconImage.setImageResource(imageResId);
    }
}
