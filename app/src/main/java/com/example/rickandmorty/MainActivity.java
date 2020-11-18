package com.example.rickandmorty;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeaderViewHolder.FilterInputListener {

    private AsyncRequest asyncRequest;
    private Character charApi;
    private CharacterAdapter adapter;

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
    public void onLocationClicked() {

    }

    @Override
    public void onSpeciesClicked() {

    }

    @Override
    public void onStatusClicked() {

    }

    @Override
    public void afterTextChanged(Editable e) {
        asyncRequest.cancel(true);
        charApi.withName(e.toString());
        asyncRequest = new AsyncRequest(charApi, adapter);
        asyncRequest.execute();
    }
}
