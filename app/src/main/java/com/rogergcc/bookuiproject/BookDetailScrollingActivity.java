package com.rogergcc.bookuiproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BookDetailScrollingActivity extends AppCompatActivity {

    CollapsingToolbarLayout mCollapsingToolbarLayout;


    private ImageView bookImg;
    private TextView book_title, book_description, book_author;
    private RatingBar book_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);

        iniViews();

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
                    mCollapsingToolbarLayout.setTitle("Book Details");
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

        Glide.with(this)
                .load(imageResourceId)
                .centerCrop()
                .apply(new RequestOptions().override(600, 200))
//                .placeholder(R.drawable.loading_spinner)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mCollapsingToolbarLayout.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

//        if (imageResourceId != null) {
//            mCollapsingToolbarLayout.setBackgroundResource(Integer.parseInt(imageResourceId));
//        }


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

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BookDetailScrollingActivity.this, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(getSheetLayout);
        bottomSheetDialog.show();
    }
}
