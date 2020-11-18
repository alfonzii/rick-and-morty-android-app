package com.example.rickandmorty;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgGreenTop, imgRickAndMorty;
    private ImageButton imgbuttonLocation, imgbuttonSpecies, imgbuttonStatus;
    private EditText editCharNameFilter;
    private TextView txtTotalCharCount;

    private int count = 0;

    FilterInputListener listener;

    public interface FilterInputListener{
        void onLocationClicked();
        void onSpeciesClicked();
        void onStatusClicked();
        void afterTextChanged(Editable s);
    }

    public HeaderViewHolder(View itemView, final FilterInputListener listener) {
        super(itemView);
        this.listener = listener;

        // TODO refactor through databinding
        imgGreenTop = itemView.findViewById(R.id.image_green_top);
        imgRickAndMorty = itemView.findViewById(R.id.image_rick_and_morty);
        imgbuttonLocation = itemView.findViewById(R.id.imgbutton_location);
        imgbuttonSpecies = itemView.findViewById(R.id.imgbutton_species);
        imgbuttonStatus = itemView.findViewById(R.id.imgbutton_status);
        editCharNameFilter = itemView.findViewById(R.id.edit_input_name);
        txtTotalCharCount = itemView.findViewById(R.id.text_character_count);

        imgbuttonLocation.setOnClickListener(v -> listener.onLocationClicked());
        imgbuttonSpecies.setOnClickListener(v -> listener.onSpeciesClicked());
        imgbuttonStatus.setOnClickListener(v -> listener.onStatusClicked());
        editCharNameFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.afterTextChanged(s);
            }
        });

    }

    public void incrementCount() {
        count++;
    }

    public void setCount(int i) {
        count = i;
    }

    public void updateLoadingTxtTotalCharCount() {
        txtTotalCharCount.setText(Integer.toString(count) + " " + "characters found yet (Loading another...)");
    }

    public void updateTxtTotalCharCount() {
        txtTotalCharCount.setText(Integer.toString(count) + " " + "characters found");
    }
}
