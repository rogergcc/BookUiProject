package com.rogergcc.bookuiproject.recyclerview;

import android.widget.ImageView;


import com.rogergcc.bookuiproject.model.Book;

public interface BookItemClickListener {

    void onBookClick(Book book, ImageView bookImageView); // we will need the imageview to make the shared animation between the two activity

}
