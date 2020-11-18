package com.example.rickandmorty;

import android.support.v7.widget.RecyclerView;
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

    public HeaderViewHolder(View itemView) {
        super(itemView);
        // TODO refactor through databinding
        imgGreenTop = itemView.findViewById(R.id.image_green_top);
        imgRickAndMorty = itemView.findViewById(R.id.image_rick_and_morty);
        imgbuttonLocation = itemView.findViewById(R.id.imgbutton_location);
        imgbuttonSpecies = itemView.findViewById(R.id.imgbutton_species);
        imgbuttonStatus = itemView.findViewById(R.id.imgbutton_status);
        editCharNameFilter = itemView.findViewById(R.id.edit_input_name);
        txtTotalCharCount = itemView.findViewById(R.id.text_character_count);
    }
}
