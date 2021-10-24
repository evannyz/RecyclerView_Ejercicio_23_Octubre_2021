package com.example.apprecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprecyclerview.databinding.WordListItemBinding;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private List<String> mWordList;
    private PassElementSelected listener;


    public WordListAdapter(List<String> mWordList, PassElementSelected listener) {

        this.mWordList = mWordList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        WordListItemBinding binding = WordListItemBinding
                .inflate(LayoutInflater.from(parent.getContext())
                        , parent, false);
        return new WordViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String word = mWordList.get(position);
        holder.bind(word);//Se cargan los datos, importante es cargarle la lista y no el dato enduro

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    public class WordViewHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvWordItem;


        public WordViewHolder(WordListItemBinding binding) {
            super(binding.getRoot());
            tvWordItem = binding.tvWordItem;
            tvWordItem.setOnClickListener(this);
        }

        public void bind(String word) {

            tvWordItem.setText(word);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            String element = mWordList.get(position);
            mWordList.set(position, "Seleccionado " + element);
            notifyDataSetChanged();
            listener.PassElement(element);



        }
    }
}
