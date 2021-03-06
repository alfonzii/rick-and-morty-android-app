package com.example.rickandmorty;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

class HeaderViewHolder extends RecyclerView.ViewHolder {
    private ImageButton imgbuttonLocation, imgbuttonSpecies, imgbuttonStatus;
    private EditText editCharNameFilter;
    private TextView txtTotalCharCount;

    private FilterInputListener listener;

    private int count = 0;

    public interface FilterInputListener{
        void onLocationClicked(ImageButton button);
        void onSpeciesClicked(ImageButton button);
        void onStatusClicked(ImageButton button);
        void afterTextChanged(Editable s);
    }

    public HeaderViewHolder(View itemView, final FilterInputListener listener) {
        super(itemView);
        this.listener = listener;

        // TODO refactor through databinding
        imgbuttonLocation = itemView.findViewById(R.id.imgbutton_location);
        imgbuttonSpecies = itemView.findViewById(R.id.imgbutton_species);
        imgbuttonStatus = itemView.findViewById(R.id.imgbutton_status);
        editCharNameFilter = itemView.findViewById(R.id.edit_input_name);
        txtTotalCharCount = itemView.findViewById(R.id.text_character_count);

        imgbuttonLocation.setOnClickListener(v -> listener.onLocationClicked(imgbuttonLocation));
        imgbuttonSpecies.setOnClickListener(v -> listener.onSpeciesClicked(imgbuttonSpecies));
        imgbuttonStatus.setOnClickListener(v -> listener.onStatusClicked(imgbuttonStatus));
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

    void setCount(int i) {
        count = i;
    }

    void updateLoadingTxtTotalCharCount() {
        txtTotalCharCount.setText(count + " characters found yet (Loading another...)");
    }

    void updateTxtTotalCharCount() {
        txtTotalCharCount.setText(count + " characters found");
    }
}
