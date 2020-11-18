package com.example.rickandmorty;

import android.os.AsyncTask;
import android.util.Log;

import com.rickandmortyapi.Character;

import java.util.Collection;

class AsyncRequest extends AsyncTask<Void, Character, Void> {

    private static final boolean IS_FINISHED = true;
    private static final boolean NOT_FINISHED = false;
    private static final int USER_REACTION_TIME = 400;

    private Character characterApiWrapper;
    private CharacterAdapter adapter;

    AsyncRequest(Character character, CharacterAdapter adapter) {
        characterApiWrapper = character;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        waitForCancel(USER_REACTION_TIME);
        if (isCancelled()) {
            return null;
        }
        int page = 1;
        Collection<Character> tempCollection;
        do {
            tempCollection = characterApiWrapper.filter(page);
            publishProgress(tempCollection.toArray(new Character[0]));
            page++;
            if (isCancelled()) {
                break;
            }
        } while (!tempCollection.isEmpty());

        return null;
    }

    @Override
    protected final void onProgressUpdate(Character... tempCollection) {
        adapter.addItems(tempCollection);
        adapter.showCount(NOT_FINISHED);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter.showCount(IS_FINISHED);
    }

    @Override
    protected void onCancelled() {
        adapter.clearItems();
        adapter.showCount(NOT_FINISHED);
    }

    private void waitForCancel(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Log.d(AsyncRequest.class.getName(), "Thread InterruptedException.");
        }
    }
}
