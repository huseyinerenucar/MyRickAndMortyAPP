package com.example.rickandmorty;

import static com.example.rickandmorty.util.StaticFunctions.textFixer;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rickandmorty.util.GlideImageLoader;
import com.rickandmortyapi.Character;
import com.rickandmortyapi.Episode;

import org.threeten.bp.format.DateTimeFormatter;


public class CharActivity extends AppCompatActivity {

    public static Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        Context context = CharActivity.this;

        TextView charName = findViewById(R.id.charName);
        TextView statusText = findViewById(R.id.statusText);
        TextView specyText = findViewById(R.id.specyText);
        TextView genderText = findViewById(R.id.genderText);
        TextView originText = findViewById(R.id.originText);
        TextView locationText = findViewById(R.id.locationText);
        TextView episodesText = findViewById(R.id.episodesText);
        TextView createdTimeText = findViewById(R.id.createdTimeText);
        ImageView charImage = findViewById(R.id.charImage);
        ImageView backButton = findViewById(R.id.backButton);

        charName.setText(textFixer(character.getName()));
        statusText.setText(textFixer(character.getStatus().toString()));
        specyText.setText(textFixer(character.getSpecies()));
        genderText.setText(textFixer(character.getGender().toString()));
        originText.setText(textFixer(character.getOriginLocation().getName()));
        locationText.setText(textFixer(character.getLastKnownLocation().getName()));
        for(Episode episode : character.getEpisodes()){
            episodesText.setText(episodesText.getText() + episode.getId().toString() + ", ");
        }
        episodesText.setText(episodesText.getText().subSequence(0, episodesText.getText().length() - 2));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        String formattedDateTime = character.getCreated().format(formatter);
        createdTimeText.setText(formattedDateTime);

        GlideImageLoader.setUnroundedImage(context, character.getImage().toString(), charImage, null);

        backButton.setOnClickListener(v -> finish());
    }

}