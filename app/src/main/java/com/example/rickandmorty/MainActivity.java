package com.example.rickandmorty;

import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.ImageButton;

import com.rickandmortyapi.Character;


public class MainActivity extends AppCompatActivity implements HeaderViewHolder.FilterInputListener {
    private static final int STATE_GREEN = 0;
    private static final int STATE_ORANGE = 1;
    private static final int STATE_UNKNOWN = 2;
    private static final int STATE_OFF = 3;

    private AsyncRequest asyncRequest;
    private Character charApi;
    private CharacterAdapter adapter;

    private int locationButtonState = STATE_OFF, speciesButtonState = STATE_OFF, statusButtonState = STATE_OFF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CharacterAdapter(this, this);
        recyclerView.setAdapter(adapter);

        charApi = new Character();
        charApi.withName("");
        asyncRequest = new AsyncRequest(charApi, adapter);
        asyncRequest.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncRequest.cancel(true);
    }

    // Not filtering yet - just changes color
    private int buttonFilterUpdate(ImageButton button, int buttonState) {
        switch (buttonState) {
            case STATE_GREEN:
                button.setImageTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorOrange)));
                buttonState = STATE_ORANGE;
                break;

            case STATE_ORANGE:
                button.setImageTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorDarkRed)));
                buttonState = STATE_UNKNOWN;
                break;

            case STATE_UNKNOWN:
                button.setImageTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.black)));
                button.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
                buttonState = STATE_OFF;
                break;

            case STATE_OFF:
                button.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorBlock)));
                button.setImageTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorGreen)));
                buttonState = STATE_GREEN;
                break;
        }
        return buttonState;
    }

    @Override
    public void onLocationClicked(ImageButton locationButton) {
        locationButtonState = buttonFilterUpdate(locationButton, locationButtonState);
    }

    @Override
    public void onSpeciesClicked(ImageButton speciesButton) {
        speciesButtonState = buttonFilterUpdate(speciesButton, speciesButtonState);
    }

    @Override
    public void onStatusClicked(ImageButton statusButton) {
        statusButtonState = buttonFilterUpdate(statusButton, statusButtonState);
    }

    @Override
    public void afterTextChanged(Editable e) {
        asyncRequest.cancel(true);
        charApi.withName(e.toString());
        asyncRequest = new AsyncRequest(charApi, adapter);
        asyncRequest.execute();
    }
}
