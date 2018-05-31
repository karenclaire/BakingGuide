package com.example.android.bakingguide.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingguide.R;
import com.example.android.bakingguide.model.Instructions;
import com.example.android.bakingguide.model.Recipe;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InstructionsDetailsFragment extends Fragment {

    private static final String LOG_TAG = InstructionsDetailsFragment.class.getSimpleName();


    public static final String STEP_BUNDLE_KEY = "RECIPE_STEP_DATA";
    public static final String STEPS_INDEX = "RECIPE_STEP_INDEX";
    static final String CURRENT_INDEX_BUNDLE_KEY = "RECIPE_CURRENT_INDEX";
    static final String RECIPES_BUNDLE_KEY = "RECIPES";

    @BindView(R.id.exoplayer_view)
    public SimpleExoPlayerView exoPlayerView;

    @BindView(R.id.thumbnail_image_view)
    ImageView thumbnailImageView;


    @BindView(R.id.instructions_detail_text)
    TextView instructionsDetailTextView;

    @BindView(R.id.previous_step)
    Button previousStepButton;

    @BindView(R.id.next_step)
    Button nextStepButton;

    int mCurrentPosition;

   ArrayList<Recipe> mRecipes;
    ArrayList<Instructions> mInstructions;
    SimpleExoPlayer player;
    private BandwidthMeter bandwidthMeter;
    private Handler mainHandler;
    String recipeName;


    //MAdatory constructor
    public InstructionsDetailsFragment() {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(STEP_BUNDLE_KEY, Parcels.wrap(mInstructions));
        outState.putParcelable(STEPS_INDEX, Parcels.wrap(mCurrentPosition));
        outState.putString("Title", recipeName);

        }



    private onInstructionsClickHandler instructionsClickHandler;

    public interface onInstructionsClickHandler {
        void onInstructionsClickHandler(List<Instructions> instructions, int Index, String recipeName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView;
        mainHandler = new Handler();
        bandwidthMeter = new DefaultBandwidthMeter();

        //instructionsClickHandler = (RecipeDetailActivity)getActivity();

        mRecipes = new ArrayList<>();

        if(savedInstanceState != null) {
            mInstructions = savedInstanceState.getParcelable(STEP_BUNDLE_KEY);
            mCurrentPosition = savedInstanceState.getInt(STEPS_INDEX);
            recipeName = savedInstanceState.getString("Title");


        }
        else {
            mInstructions =getArguments().getParcelable(STEP_BUNDLE_KEY);
            if (mInstructions!=null) {
                mInstructions =getArguments().getParcelable(STEP_BUNDLE_KEY);
                mCurrentPosition=getArguments().getInt(STEPS_INDEX);
                recipeName=getArguments().getString("Title");
            }
            else {
                mRecipes =getArguments().getParcelable(RECIPES_BUNDLE_KEY);
                //casting List to ArrayList
                mInstructions=(ArrayList<Instructions>)mRecipes.get(0).getInstructions();
                mCurrentPosition=0;
            }

        }



        View rootView = inflater.inflate(R.layout.instructions_details_fragment, container, false);
        textView = (TextView) rootView.findViewById(R.id.instruction_text);
        textView.setText(mInstructions.get(mCurrentPosition).getDescription());
        textView.setVisibility(View.VISIBLE);

        exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exoplayer_view);
        exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        String videoURL = mInstructions.get(mCurrentPosition).getVideoURL();

        /**if (rootView.findViewWithTag("sw600dp-port-recipe_step_detail")!=null) {
            recipeName=((RecipeDetailActivity) getActivity()).recipeName;
            ((RecipeDetailActivity) getActivity()).getSupportActionBar().setTitle(recipeName);
        }**/

        String imageUrl= mInstructions.get(mCurrentPosition).getThumbnailURL();
        if (imageUrl!="") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            ImageView thumbImage = (ImageView) rootView.findViewById(R.id.thumbnail_image_view);
            Picasso.with(getContext()).load(builtUri).into(thumbImage);
        }

        if (!videoURL.isEmpty()) {


            initializePlayer(Uri.parse(mInstructions.get(mCurrentPosition).getVideoURL()));

            /**if (rootView.findViewWithTag("sw600dp-land-recipe_step_detail")!=null) {
                getActivity().findViewById(R.id.fragment_container2).setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
                simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);**/
            } if (isInLandscapeMode(getContext())){
                textView.setVisibility(View.GONE);
        }

        else {
            player=null;
            exoPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.baseline_image_black_48));
            exoPlayerView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        }


        Button previousStepButton = (Button) rootView.findViewById(R.id.previous_step);
        Button nextStepButton = (Button) rootView.findViewById(R.id.next_step);

        previousStepButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mInstructions.get(mCurrentPosition).getId() > 0) {
                    if (player!=null){
                        player.stop();
                    }
                    instructionsClickHandler.onInstructionsClickHandler(mInstructions,
                            mInstructions.get(mCurrentPosition).getId() - 1,recipeName);
                }
                else {
                    Toast.makeText(getActivity(),"First instruction is currently displayed", Toast.LENGTH_SHORT).show();

                }
            }});

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                int lastIndex = mInstructions.size()-1;
                if (mInstructions.get(mCurrentPosition).getId() < mInstructions.get(lastIndex).getId()) {
                    if (player!=null){
                        player.stop();
                    }
                    instructionsClickHandler.onInstructionsClickHandler(mInstructions,
                            mInstructions.get(mCurrentPosition).getId() + 1,recipeName);
                }
                else {
                    Toast.makeText(getContext(),"Last instruction is currently displayed. " +
                            "Press previous button to see previous step or back button to select another recipe", Toast.LENGTH_SHORT).show();

                }
            }});




        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
            LoadControl loadControl = new DefaultLoadControl();

            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(player);

            String userAgent = Util.getUserAgent(getContext(), "Baking Guide");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }
    }



    public boolean isInLandscapeMode( Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player!=null) {
            player.stop();
            player.release();
            player=null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    public void setInstructions(ArrayList<Instructions> instructions) {
        this.mInstructions = instructions;

}}
