package com.example.rickandmorty;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.ws.rs.HttpMethod;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    //private Collection<Character> collection;
    private Context context;

    private class RefreshAsync extends AsyncTask<Void, Void, Void> {

        private Character rnmcharacter;
        private Collection<Character> collection;


        RefreshAsync(Character rick){
            rnmcharacter = rick;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            collection = rnmcharacter.filterAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter = new CharacterAdapter(context, collection);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        context = this;


        Character figure = new Character();
        figure.withName("rick");

        new RefreshAsync(figure).execute();

    }
}
