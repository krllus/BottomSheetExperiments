package com.mayojava.sample.bottomsheetdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button_show_bottom_sheet)
    Button mButtonShowBottomSheet;
    @BindView(R.id.button_collapse_bottom_sheet)
    Button mCollapseBottomSheet;
    @BindView(R.id.button_hide_bottom_sheet)
    Button mHideBottomSheet;
    @BindView(R.id.button_show_bottom_sheet_dialog)
    Button mShowBottomSheetDialog;
    @BindView(R.id.button_change_bottom_sheet_content)
    Button mChangeBottomSheetContent;
    @BindView(R.id.bottom_sheet)
    LinearLayout mLayoutBottomSheet;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    private BottomSheetBehavior mBottomSheetBehavior;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(mLayoutBottomSheet);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        showFab();
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        hideFab();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        showFab();
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void hideFab() {
        floatingActionButton.animate()
                .scaleX(0)
                .scaleY(0)
                .setDuration(300).start();
    }

    private void showFab() {
        floatingActionButton.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(300).start();
    }

    @OnClick(R.id.button_show_bottom_sheet)
    public void showBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        showFab();
    }

    @OnClick(R.id.button_collapse_bottom_sheet)
    public void collapseBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        showFab();
    }

    @OnClick(R.id.button_hide_bottom_sheet)
    public void hideBottomSheet() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        hideFab();
    }

    @OnClick(R.id.button_show_bottom_sheet_dialog)
    public void showBottomSheetDialog() {
        CustomBottomSheetDialog bottomSheetDialog = CustomBottomSheetDialog.getInstance();
        bottomSheetDialog.show(getSupportFragmentManager(), "Custom Bottom Sheet");
    }

    @OnClick(R.id.button_change_bottom_sheet_content)
    public void changeBottomSheetContent() {

        TextView chossen_content;

        switch (count) {
            case 1:
                chossen_content = (TextView) findViewById(R.id.bottom_sheet_content_I);
                break;
            case 2:
                chossen_content = (TextView) findViewById(R.id.bottom_sheet_content_II);
                break;
            case 3:
                chossen_content = (TextView) findViewById(R.id.bottom_sheet_content_III);
                break;
            default:
                chossen_content = (TextView) findViewById(R.id.bottom_sheet_content_IV);
                break;
        }

        if (chossen_content.getVisibility() == View.GONE) {
            chossen_content.setVisibility(View.VISIBLE);
            count--;
        } else {
            chossen_content.setVisibility(View.GONE);
            count++;
        }


    }


}
