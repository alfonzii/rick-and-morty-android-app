package com.example.rickandmorty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickandmortyapi.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Character> data;
    private Context context;
    private HeaderViewHolder header = null;

    private static int TYPE_HEADER = 0;
    private static int TYPE_CHARACTER = 1;

    public CharacterAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void addItems(final Collection<Character> newItems) {
        data.addAll(new ArrayList<>(newItems));
        // Keep RecyclerView scrolling state
        this.notifyItemRangeChanged(this.getItemCount() - 20, this.getItemCount());
    }

    public void updateItems(final Collection<Character> newItems) {
        final List<Character> oldItems = data;
        data = new ArrayList<>(newItems);

        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldItems.size();
            }

            @Override
            public int getNewListSize() {
                return newItems.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldItems.get(oldItemPosition).getId().equals(data.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return oldItems.get(oldItemPosition).equals(data.get(newItemPosition));
            }
        }).dispatchUpdatesTo(this);

    }

    public void showCount() {
        header.setCount(this.getItemCount()-1);
        header.updateTxtTotalCharCount();
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
            if (header == null)
                header = new HeaderViewHolder(view);
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
        } else {
            // TODO implement HeaderViewHolder onBind
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }
}
