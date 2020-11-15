package com.example.rickandmorty;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.rickandmortyapi.ApiException;
import com.rickandmortyapi.ApiRequest;
import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

public class MainActivity extends AppCompatActivity {

    /*private static class UpgradedCharacter extends Character {

        private transient final Map<String, Object> filters = new HashMap<>();

        void addFilter(@NonNull String query, @NonNull Object value) {
            filters.put(query, value);
        }

        protected JsonArray filterAll() {
            Integer page = 1;

            JsonArray fullResponse = new JsonArray();
            JsonElement indicator = null;

            do {
                filters.put("page", page);

                try {
                    final ApiRequest request = new ApiRequest(HttpMethod.GET, "/character");
                    request.setParameters(filters);

                    final JsonObject response = request.execute();

                    fullResponse.addAll(response.getAsJsonArray("results"));
                    indicator = response.get("info").getAsJsonObject().get("next");

                    page++;

                } catch (ApiException e) {
                    return new JsonArray();
                }
            } while (!indicator.isJsonNull());

            return fullResponse;
        }
    }*/

    private class RefreshAsync extends AsyncTask<Void, Void, Void> {

        private Character rnmcharacter;
        private List<Character> listOfCharacters = new ArrayList<>();

        RefreshAsync(Character rick){
            rnmcharacter = rick;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listOfCharacters = (List<Character>) rnmcharacter.listAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView text = findViewById(R.id.text);
            //text.setText(listOfCharacters.size());
            text.setText(Integer.toString(listOfCharacters.size()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Character figure = new Character();
        //figure.withName("rick");

        new RefreshAsync(figure).execute();


    }
}
