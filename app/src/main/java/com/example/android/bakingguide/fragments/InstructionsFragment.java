package com.example.android.bakingguide.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.adapters.InstructionsAdapter;
import com.example.android.bakingguide.model.Instructions;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionsFragment extends Fragment {

    public static final String BUNDLE_DATA_KEY = "RECIPE_STEPS_DATA";
    public static final String DEBUG_TAG ="DebugStuff";

    @BindView(R.id.instructions_recycler)
    RecyclerView instructionsRecyclerView;

    static ArrayList<Instructions> mInstructions;
    InstructionsAdapter mInstructionsAdapter;
    static InstructionsAdapter.OnClickHandler mOnInstructionsClickHandler;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_DATA_KEY, Parcels.wrap(mInstructions));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "IngstructionsFragment onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_instructions, container, false);
        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            mInstructions = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_DATA_KEY));
        }

        mInstructionsAdapter = new InstructionsAdapter();
        mInstructionsAdapter.setInstructions(mInstructions);
        mInstructionsAdapter.setOnClickHandler(mOnInstructionsClickHandler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        instructionsRecyclerView.setLayoutManager(layoutManager);
        instructionsRecyclerView.setAdapter(mInstructionsAdapter);

        return rootView;
    }

    public static void setInstructions(ArrayList<Instructions> instructions) {
        mInstructions = instructions;
    }
    public static void setOnInstructionsClickHandler(InstructionsAdapter.OnClickHandler onInstructionsClickHandler) {
        mOnInstructionsClickHandler = onInstructionsClickHandler;
    }
}



