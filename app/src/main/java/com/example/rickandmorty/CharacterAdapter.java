package com.example.rickandmorty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.*;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> data;
    private Context context;

    public CharacterAdapter(Context context, Collection<Character> data) {
        this.context = context;
        this.data = new ArrayList<>(data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgHolding, imgSpeciesIcon, imgLocationIcon, imgStatusIcon;
        private TextView txtName, txtSpecies, txtLocation, txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            imgHolding = itemView.findViewById(R.id.image_holding);
            imgSpeciesIcon = itemView.findViewById(R.id.image_species_icon);
            imgLocationIcon = itemView.findViewById(R.id.image_location_icon);
            imgStatusIcon = itemView.findViewById(R.id.image_status_icon);
            txtName = itemView.findViewById(R.id.text_character_name);
            txtSpecies = itemView.findViewById(R.id.text_species_icon);
            txtLocation = itemView.findViewById(R.id.text_location_icon);
            txtStatus = itemView.findViewById(R.id.text_status_icon);
        }

        public void setDetails(Context context, Character character, int position) {
            setTxtName(context, character, position);
            setImgHolding(position);
            setSpecies(context, character);
            setLocation(context, character);
            setStatus(context, character);
        }

        private void setTxtName(Context context, Character character, int position) {
            if (position % 2 == 0) {
                txtName.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));
            }
            else {
                // is default orange
            }
            txtName.setText(character.getName());
        }

        private void setImgHolding(int position) {
            if (position % 2 == 0)
                imgHolding.setImageResource(R.drawable.holding_mask_green);
            else {
                // is default orange
            }
        }

        private void setSpecies(Context context, Character character) {
            if (character.getSpecies().equals("unknown")) {
                imgSpeciesIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkRed));
                txtSpecies.setTextColor(ContextCompat.getColor(context, R.color.colorDarkRed));
                txtSpecies.setText(character.getSpecies());
                return;
            }

            Pattern p = Pattern.compile("[Hh]uman");
            Matcher m = p.matcher(character.getSpecies());
            if (!m.matches()) {
                imgSpeciesIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorOrange));
                txtSpecies.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            }

            txtSpecies.setText(character.getSpecies());
        }

        private void setLocation(Context context, Character character) {
            if (character.getOriginLocation().getName().equals("unknown")) {
                imgLocationIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkRed));
                txtLocation.setTextColor(ContextCompat.getColor(context, R.color.colorDarkRed));
                txtLocation.setText(character.getOriginLocation().getName());
                return;
            }

            Pattern p = Pattern.compile("[Ee]arth");
            Matcher m = p.matcher(character.getOriginLocation().getName());
            if (!m.matches()) {
                imgLocationIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorOrange));
                txtLocation.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                txtLocation.setText(character.getOriginLocation().getName());
            }
        }

        private void setStatus(Context context, Character character) {
            if (character.getStatus().equals(Character.Status.DEAD)) {
                imgStatusIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorOrange));
                txtStatus.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (character.getStatus().equals(Character.Status.UNKNOWN)) {
                imgStatusIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkRed));
                txtStatus.setTextColor(ContextCompat.getColor(context, R.color.colorDarkRed));
            } else {
                // is default alive
            }
            txtStatus.setText(character.getStatus().toString());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setDetails(context, data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
