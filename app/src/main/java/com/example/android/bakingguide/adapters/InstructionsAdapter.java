package com.example.android.bakingguide.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.model.Instructions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {

        ArrayList<Instructions> mInstructions;
        InstructionsAdapter.OnClickHandler onClickHandler;

        public InstructionsAdapter() {
            this.mInstructions = new ArrayList<>();
        }

        @Override
        public InstructionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.instructions, parent, false);

            return new InstructionsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(InstructionsAdapter.ViewHolder holder, int position) {
            holder.instructionsTextView.setText(mInstructions.get(position).getShortDescription());
        }

    @Override
    public int getItemCount() {
        return mInstructions.size();
    }

    public void setInstructions(ArrayList<Instructions> instructions) {
        this.mInstructions = instructions;
        notifyDataSetChanged();
    }

    public void setOnClickHandler(OnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public interface OnClickHandler {
        void onInstructionsClicked(int position, Instructions instructions);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.instruction_text)
        TextView instructionsTextView;

        Instructions instructions;
        int position;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onClickHandler.onInstructionsClicked(position, instructions);
        }
    }
}

