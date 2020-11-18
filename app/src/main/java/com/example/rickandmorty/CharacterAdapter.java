package com.example.rickandmorty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Character> data;
    private Context context;
    private HeaderViewHolder header = null;

    private HeaderViewHolder.FilterInputListener filterInputListener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CHARACTER = 1;

    CharacterAdapter(Context context, HeaderViewHolder.FilterInputListener filterInputListener) {
        this.context = context;
        this.data = new ArrayList<>();
        this.filterInputListener = filterInputListener;
    }

    void addItems(final Character... newItems) {
        Collections.addAll(data, newItems);
        // Keep RecyclerView scrolling state
        this.notifyItemRangeChanged(this.getItemCount() - 20, this.getItemCount());
    }

    void clearItems() {
        data.clear();
        this.notifyItemRangeRemoved(1, this.getItemCount() - 1);
    }

    void showCount(boolean isFinished) {
        if (!isFinished) {
            header.setCount(this.getItemCount() - 1);
            header.updateLoadingTxtTotalCharCount();
        } else {
            header.setCount(this.getItemCount() - 1);
            header.updateTxtTotalCharCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_CHARACTER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.header_recycler_layout, parent, false);
            if (header == null)
                header = new HeaderViewHolder(view, filterInputListener);
            return header;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.character_item_row, parent, false);
            return new CharacterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (position >= 1) {
            ((CharacterViewHolder) viewHolder).setDetails(context, data.get(position - 1), position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }
}
