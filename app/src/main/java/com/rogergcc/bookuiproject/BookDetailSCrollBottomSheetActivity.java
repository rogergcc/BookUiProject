package com.rogergcc.bookuiproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BookDetailSCrollBottomSheetActivity extends AppCompatActivity {


    CollapsingToolbarLayout mCollapsingToolbarLayout;

    BottomSheetBehavior sheetBehavior;
    LinearLayout bottomSheet;

    private ImageView bookImg;
    private TextView book_title, book_description, book_author;
    private RatingBar book_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_scroll_bottom_sheet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iniViews();
        bottomSheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
//                        btn_bottom_sheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
//                        btn_bottom_sheet.setText("Expand Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHalfExpandedRatio(0.65f);

        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle("Account");
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    //carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void iniViews() {

        String bookTitle = getIntent().getExtras().getString("title");
        String bookAuthor = getIntent().getExtras().getString("imgAuthor");
        String bookDescription = getIntent().getExtras().getString("imgDescrition");
        float bookRating = getIntent().getExtras().getFloat("imgRating");

//        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        String imageResourceId = getIntent().getExtras().getString("imgURL");

//        bookImg = findViewById(R.id.item_book_img);
//
//        Glide.with(this)
//                .load(imageResourceId)
//                .transform(new CenterCrop(),new RoundedCorners(16))//i knowit's deprecated i will fix later
//                .into(bookImg);

//


        book_title = findViewById(R.id.item_book_title);
        book_title.setText(bookTitle);

        book_author = findViewById(R.id.item_book_author);
        book_author.setText(bookAuthor);

        book_description = findViewById(R.id.item_book_desc_detail);
        book_description.setText(bookDescription);


        getSupportActionBar().setTitle(bookTitle);

        book_rating = findViewById(R.id.item_book_ratingbar);
        book_rating.setRating(bookRating);


    }

    /**
     * Show BottomSheetDialog on click of button
     */
    void showBottomSheetDialog() {
        View getSheetLayout = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BookDetailSCrollBottomSheetActivity.this, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(getSheetLayout);

//        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }
}
