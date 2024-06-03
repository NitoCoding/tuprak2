package com.example.tuprak2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    ImageView pick_image;
    EditText get_name,get_username;
    Button next_button;
    Intent open_galery_intent,open_note_intent;
    String name,username;
    Uri image_uri;

    ActivityResultLauncher<Intent> galery_launcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        open_note_intent = new Intent(this,NoteActivity.class);

        pick_image = findViewById(R.id.imageProfile);
        get_name = findViewById(R.id.nameField);
        get_username = findViewById(R.id.usernameField);
        next_button = findViewById(R.id.nextButton);

        galery_launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        try {
                            image_uri = o.getData().getData();
                            pick_image.setImageURI(image_uri);
                        }catch (Exception e){
                            Toast.makeText(ProfileActivity.this, "Please pick a profile image first!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        pick_image.setOnClickListener( e -> {
            open_galery_intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            galery_launcher.launch(open_galery_intent);
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (get_name != null && get_username != null) {
                    name = get_name.getText().toString().trim();
                    username = get_username.getText().toString().trim();

                    // Check if image_uri is not null before using it
                    if (image_uri != null) {
                        open_note_intent.putExtra("username", username);
                        open_note_intent.putExtra("name", name);
                        open_note_intent.putExtra("image", image_uri);

                        startActivity(open_note_intent);
                    } else {
                        Log.e("MainActivity", "image_uri is null");
                        // Handle the case where image_uri is null
                    }
                } else {
                    Log.e("MainActivity", "One or more views or intent is null");
                    // Handle the case where one or more views or intent is null
                }
            }
        });



    }



}