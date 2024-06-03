package com.example.tuprak2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    Intent open_profile_intent, open_result_intent;
    Uri image_uri;
    String name, username, note_title, note_content;
    Button finish_button;
    EditText get_note_title, get_note_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        open_profile_intent = getIntent();
        name = open_profile_intent.getStringExtra("name");
        username = open_profile_intent.getStringExtra("username");
        image_uri = open_profile_intent.getParcelableExtra("image");

        get_note_title = findViewById(R.id.editText);
        get_note_content = findViewById(R.id.editTextMultiLine);
        finish_button = findViewById(R.id.finishButton);

        finish_button.setOnClickListener(e -> {
            open_result_intent = new Intent(this, ResultActivity.class);
            note_content = get_note_content.getText().toString().trim();
            note_title = get_note_title.getText().toString().trim();

            open_result_intent.putExtra("username", username);
            open_result_intent.putExtra("name", name);
            if (image_uri != null) {
                open_result_intent.putExtra("image_uri", image_uri);
            }
            open_result_intent.putExtra("title", note_title);
            open_result_intent.putExtra("content", note_content);

            startActivity(open_result_intent);
        });

        Toast.makeText(NoteActivity.this, name, Toast.LENGTH_SHORT).show();
    }
}