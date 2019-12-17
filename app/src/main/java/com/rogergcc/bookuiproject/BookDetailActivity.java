package com.rogergcc.bookuiproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class BookDetailActivity extends AppCompatActivity {


    private ImageView bookImg;
    private TextView book_title,book_description,book_author;
    private RatingBar book_rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        iniViews();

    }

    void iniViews() {

        String bookTitle = getIntent().getExtras().getString("title");
        String bookAuthor = getIntent().getExtras().getString("imgAuthor");
        String bookDescription = getIntent().getExtras().getString("imgDescrition");
        float bookRating= getIntent().getExtras().getFloat("imgRating");

//        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        String imageResourceId = getIntent().getExtras().getString("imgURL");

        bookImg = findViewById(R.id.item_book_img);

        Glide.with(this)
                .load(imageResourceId)
                .transform(new CenterCrop(),new RoundedCorners(16))//i knowit's deprecated i will fix later
                .into(bookImg);

//        supportPostponeEnterTransition();
//
//        Glide.with(this).load(imageResourceId)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .dontAnimate()
//
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
//                                                Target<Drawable> target, boolean isFirstResource) {
////                        bookItemClickListener.onLoadCompleted(image, adapterPosition);
//                        supportStartPostponedEnterTransition();
//
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
//                            target, DataSource dataSource, boolean isFirstResource) {
//                        supportStartPostponedEnterTransition();
////                        bookItemClickListener.onLoadCompleted(image, adapterPosition);
//                        return false;
//                    }
//                })
//                .into(bookImg);

        //bookImg.setImageResource(imageResourceId);



        book_title = findViewById(R.id.item_book_title);
        book_title.setText(bookTitle);

        book_author = findViewById(R.id.item_book_author);
        book_author.setText(bookAuthor);

        book_description = findViewById(R.id.item_book_desc_detail);
        book_description.setText(bookDescription);


        getSupportActionBar().setTitle(bookTitle);

        book_rating = findViewById(R.id.item_book_ratingbar);
        book_rating.setRating(bookRating);

        // setup animation
//        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
//        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));





    }
}
