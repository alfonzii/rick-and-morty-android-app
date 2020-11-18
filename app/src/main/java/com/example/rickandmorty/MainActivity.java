package com.example.rickandmorty;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeaderViewHolder.FilterInputListener {

    long startTime;

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    //private Collection<Character> collection;
    //private Context context;

    private class RefreshAsync extends AsyncTask<Void, Collection<Character>, Void> {

        private Character rnmcharacter;
        private Collection<Character> collection = new ArrayList<>();


        RefreshAsync(Character rick){
            rnmcharacter = rick;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //TODO
            startTime = System.nanoTime();
            int page = 1;
            Collection<Character> tempCollection;
            //collection = rnmcharacter.filterAll();
            do {
                tempCollection = rnmcharacter.list(page);
                publishProgress(tempCollection);
                //collection.addAll(tempCollection);
                page++;
            } while (!tempCollection.isEmpty());
            //TODO
            Log.e("Measure", "TASK took : " +  ((System.nanoTime()-startTime)/1000000)+ "mS\n");

            return null;
        }

        @SafeVarargs
        @Override
        protected final void onProgressUpdate(Collection<Character>... tempCollection) {
            //Parcelable recyclerViewState;
            //recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

            adapter.addItems(tempCollection[0]);
            adapter.showCount();
            //recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            //adapter.updateItems(collection);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CharacterAdapter(this, this);

        Character figure = new Character();
        //figure.withName("rick");

        recyclerView.setAdapter(adapter);
        new RefreshAsync(figure).execute();
    }

    @Override
    public void onLocationClicked() {

    }

    @Override
    public void onSpeciesClicked() {

    }

    @Override
    public void onStatusClicked() {

    }

    @Override
    public void afterTextChanged() {

    }
}
