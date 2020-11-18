package com.example.rickandmorty;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickandmortyapi.Character;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgHolding, imgSpeciesIcon, imgLocationIcon, imgStatusIcon;
    private TextView txtName, txtSpecies, txtLocation, txtStatus;
    private ConstraintLayout layout;
    private Character character;

    public CharacterViewHolder(View itemView) {
        super(itemView);
        // TODO refactor through databinding
        imgHolding = itemView.findViewById(R.id.image_holding);
        imgSpeciesIcon = itemView.findViewById(R.id.image_species_icon);
        imgLocationIcon = itemView.findViewById(R.id.image_location_icon);
        imgStatusIcon = itemView.findViewById(R.id.image_status_icon);
        txtName = itemView.findViewById(R.id.text_character_name);
        txtSpecies = itemView.findViewById(R.id.text_species_icon);
        txtLocation = itemView.findViewById(R.id.text_location_icon);
        txtStatus = itemView.findViewById(R.id.text_status_icon);
        layout = itemView.findViewById(R.id.layout_constraint);

        // No interface here, as this action should always produce same result
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to new activity with setup values
            }
        });
    }

    public void setDetails(Context context, Character character, int position) {
        setCharacter(character);
        setTxtName(context, position);
        setImgHolding(position);
        setSpecies(context);
        setLocation(context);
        setStatus(context);
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    private void setTxtName(Context context, int position) {
        txtName.setTextColor(ContextCompat.getColor(context,
                (position % 2 == 1) ? R.color.colorGreen : R.color.colorOrange
        ));
        txtName.setText(character.getName());
    }

    private void setImgHolding(int position) {
        imgHolding.setImageResource(
                (position % 2 == 1) ? R.drawable.holding_mask_green : R.drawable.holding_mask_orange
        );
    }

    private void setSpecies(Context context) {
        if (character.getSpecies().equals("unknown")) {
            imgSpeciesIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkRed));
            txtSpecies.setTextColor(ContextCompat.getColor(context, R.color.colorDarkRed));
            txtSpecies.setText(character.getSpecies());
            return;
        }

        Pattern p = Pattern.compile("[Hh]uman");
        Matcher m = p.matcher(character.getSpecies());
        boolean equalsHuman = m.find();

        imgSpeciesIcon.setColorFilter(ContextCompat.getColor(context,
                equalsHuman ? R.color.colorGreen : R.color.colorOrange
        ));

        txtSpecies.setTextColor(ContextCompat.getColor(context,
                equalsHuman ? R.color.colorGreen : R.color.colorOrange
        ));

        txtSpecies.setText(character.getSpecies());
    }

    private void setLocation(Context context) {
        if (character.getOriginLocation().getName().equals("unknown")) {
            imgLocationIcon.setColorFilter(ContextCompat.getColor(context, R.color.colorDarkRed));
            txtLocation.setTextColor(ContextCompat.getColor(context, R.color.colorDarkRed));
            txtLocation.setText(character.getOriginLocation().getName());
            return;
        }

        Pattern p = Pattern.compile("[Ee]arth");
        Matcher m = p.matcher(character.getOriginLocation().getName());
        boolean equalsEarth = m.find();

        imgLocationIcon.setColorFilter(ContextCompat.getColor(context,
                equalsEarth ? R.color.colorGreen : R.color.colorOrange
        ));

        txtLocation.setTextColor(ContextCompat.getColor(context,
                equalsEarth ? R.color.colorGreen : R.color.colorOrange
        ));

        txtLocation.setText(
                equalsEarth ? context.getString(R.string.earth) : character.getOriginLocation().getName()
        );
    }

    private void setStatus(Context context) {
        int color = 0;
        String status = null;

        switch (character.getStatus()) {
            case DEAD:
                color = R.color.colorOrange;
                status = "Dead";
                break;

            case ALIVE:
                color = R.color.colorGreen;
                status = "Alive";
                break;

            case UNKNOWN:
                color = R.color.colorDarkRed;
                status = "unknown";
                break;
        }

        imgStatusIcon.setColorFilter(ContextCompat.getColor(context, color));
        txtStatus.setTextColor(ContextCompat.getColor(context, color));
        txtStatus.setText(status);
    }
}
