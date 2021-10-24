package com.example.apprecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apprecyclerview.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements PassElementSelected{

    private FragmentFirstBinding binding;
    private List<String> mWordList;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        wordListAutoGenerate();
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupOnClickListener();

       /* binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }

    private void setupOnClickListener() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addWordItem();

            }
        });
    }

    private void addWordItem() {
        mWordList.add("Valor desde Java");
        binding.rvList.getAdapter().notifyItemInserted(mWordList.size());//Añadimos sobre la lista ya generada
        binding.rvList.smoothScrollToPosition(mWordList.size()); //Se hace scroll cuando añades algo mas
    }

    private void setupRecyclerView() {
        WordListAdapter wordListAdapter = new WordListAdapter(mWordList, this);
        binding.rvList.setAdapter(wordListAdapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private List<String> wordListAutoGenerate() {
        mWordList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mWordList.add("Word " + i); }
        return mWordList; }



    @Override
    public void PassElement(String element) {
        Toast.makeText(getContext(), element,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}