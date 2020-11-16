package com.example.rickandmorty;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.*;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Character> data;
    private Context context;

    private static int TYPE_HEADER = 0;
    private static int TYPE_CHARACTER = 1;

    public CharacterAdapter(Context context, Collection<Character> data) {
        this.context = context;
        this.data = new ArrayList<>(data);
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgHolding, imgSpeciesIcon, imgLocationIcon, imgStatusIcon;
        private TextView txtName, txtSpecies, txtLocation, txtStatus;

        public CharacterViewHolder(View itemView) {
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

        private void setSpecies(Context context, Character character) {
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

        private void setLocation(Context context, Character character) {
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

        private void setStatus(Context context, Character character) {
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

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGreenTop, imgRickAndMorty;
        private ImageButton imgbuttonLocation, imgbuttonSpecies, imgbuttonStatus;
        private EditText editCharNameFilter;
        private TextView txtTotalCharCount;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            imgGreenTop = itemView.findViewById(R.id.image_green_top);
            imgRickAndMorty = itemView.findViewById(R.id.image_rick_and_morty);
            imgbuttonLocation = itemView.findViewById(R.id.imgbutton_location);
            imgbuttonSpecies = itemView.findViewById(R.id.imgbutton_species);
            imgbuttonStatus = itemView.findViewById(R.id.imgbutton_status);
            editCharNameFilter = itemView.findViewById(R.id.edit_input_name);
            txtTotalCharCount = itemView.findViewById(R.id.text_character_count);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_CHARACTER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.header_recycler_layout, parent, false);
            return new HeaderViewHolder(view);
        }
        else{
            view = LayoutInflater.from(context).inflate(R.layout.character_item_row, parent, false);
            return new CharacterViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(position >= 1) {
            ((CharacterViewHolder) viewHolder).setDetails(context, data.get(position-1), position);
        }
        else {
            //TODO implement header layout functionality
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }
}
