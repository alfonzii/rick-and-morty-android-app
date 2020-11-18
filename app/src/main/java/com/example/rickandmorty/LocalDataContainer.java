package com.example.rickandmorty;

import com.rickandmortyapi.Character;

import java.util.Collection;

// Singleton class
final class LocalDataContainer {
    private static final LocalDataContainer INSTANCE = new LocalDataContainer();

    private LocalDataContainer() {

    }

    static LocalDataContainer getInstance() { return INSTANCE; }

    private boolean isDataDownloaded = false;
    private Collection<Character> localData;

    public boolean isDataDownloaded() {
        return isDataDownloaded;
    }
    public void setDataDownloaded(boolean dataDownloaded) {
        isDataDownloaded = dataDownloaded;
    }


}
