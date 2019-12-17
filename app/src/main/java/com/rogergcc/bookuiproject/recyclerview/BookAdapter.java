package com.rogergcc.bookuiproject.recyclerview;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.rogergcc.bookuiproject.R;
import com.rogergcc.bookuiproject.model.Book;

import java.util.List;

/**
 * Created by rogergcc on 14/12/2019.
 * Copyright Ⓒ 2019 . All rights reserved.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookviewholder> {

    List<Book> mdata;

    BookItemClickListener bookItemClickListener;


    public BookAdapter(List<Book> mdata, BookItemClickListener listener) {
        this.mdata = mdata;
        this.bookItemClickListener = listener;
    }

    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_book,parent,false);
        return new bookviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final bookviewholder holder, final int position) {
        //bind book item data here
        //i'm just going to bind the image ...

        //load book image using Glide
        Book bookh = mdata.get(position);

        String pagesReviews = bookh.getPages() +" pages | "+bookh.getReview() + " reviews";


        holder.author.setText(mdata.get(position).getAuthor());

        holder.title.setText(mdata.get(position).getTitle());
        holder.pages.setText(pagesReviews);

        holder.ratingBar.setRating(mdata.get(position).getRating());

        //Images drawable int
//        Glide.with(holder.itemView.getContext())
//                .load(mdata.get(position).getDrawableResource())
//                .transform(new CenterCrop(),new RoundedCorners(16))//i knowit's deprecated i will fix later
//                .into(holder.imgBook); //destination path

//        RequestOptions requestOptions = new RequestOptions();
//
//        requestOptions.placeholder(R.drawable.ic_placeholder);
//        requestOptions.error(R.drawable.ic_error);

        //Images Url String
        Glide.with(holder.itemView.getContext())
                .load(mdata.get(position).getImgUrl())
                .transform(new CenterCrop(),new RoundedCorners(16))//i knowit's deprecated i will fix later
                .into(holder.imgBook); //destination path


//        Glide.with(holder.itemView.getContext())
//                .load(mdata.get(position).getImgUrl())
//                .transform(new CenterCrop(),new RoundedCorners(16))//i knowit's deprecated i will fix later
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
//                                                Target<Drawable> target, boolean isFirstResource) {
////                        bookItemClickListener.onLoadCompleted(image, adapterPosition);
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
//                            target, DataSource dataSource, boolean isFirstResource) {
////                        bookItemClickListener.onLoadCompleted(image, adapterPosition);
//                        return false;
//                    }
//                })
//                .into((ImageView) holder.imgBook);

//        Glide.with(holder.itemView.getContext())
//                .load(mdata.get(position).getImgUrl())
//                .thumbnail(0.5f)
////                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imgBook);

        //holder.ratingBar.setRating(4.5f);

        ViewCompat.setTransitionName(holder.imgBook, mdata.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookItemClickListener.onBookClick(mdata.get(position), holder.imgBook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class bookviewholder extends RecyclerView.ViewHolder{

        ImageView imgBook,imgFav;
        TextView title,description,author,pages,rate;
        RatingBar ratingBar;

        public bookviewholder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.item_book_img);

            title = itemView.findViewById(R.id.item_book_title);
            author = itemView.findViewById(R.id.item_book_author);
            pages = itemView.findViewById(R.id.item_book_pages);
            rate = itemView.findViewById(R.id.item_book_score);
            ratingBar= itemView.findViewById(R.id.item_book_ratingbar);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    bookItemClickListener.onBookClick(mdata.get(getAdapterPosition()),imgBook);
//
//
//                }
//            });

        }
    }
}
