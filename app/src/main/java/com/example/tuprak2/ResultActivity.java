package com.example.tuprak2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    Intent open_profile_intent, open_result_intent;
    Button restartButton;
    Uri image_uri;
    String name, username, note_title, note_content;
    TextView set_username, set_name, set_title, set_content;
    ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        set_title = findViewById(R.id.title);
        set_content = findViewById(R.id.content);
        set_username = findViewById(R.id.username);
        set_name = findViewById(R.id.name);
        imageProfile = findViewById(R.id.image);
        restartButton = findViewById(R.id.restart);

        open_result_intent = getIntent();
        name = open_result_intent.getStringExtra("name");
        username = open_result_intent.getStringExtra("username");
        image_uri = open_result_intent.getParcelableExtra("image_uri");
        note_title = open_result_intent.getStringExtra("title");
        note_content = open_result_intent.getStringExtra("content");

        set_title.setText(note_title);
        set_content.setText(note_content);
        set_username.setText(username);
        set_name.setText(String.format("created by %s", name));

        if (image_uri != null) {
            Log.e("resultActivty", image_uri.toString());
            imageProfile.setImageURI(image_uri);
        } else {
            Log.e("resultActivty", "image_uri is null");
            // Handle jika image_uri null, misalnya dengan gambar placeholder
        }

        restartButton.setOnClickListener(e -> {
            open_profile_intent = new Intent(this, ProfileActivity.class);
            startActivity(open_profile_intent);
        });
    }
}