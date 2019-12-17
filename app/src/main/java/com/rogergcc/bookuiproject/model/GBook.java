package com.rogergcc.bookuiproject.model;

/**
 * Created by rogergcc on 15/12/2019.
 * Copyright Ⓒ 2019 . All rights reserved.
 */
public class GBook {
    private String mBookTitle;
    private String[] mBookAuthors;
    private String mBookDescription;
    private String mBookInfoLink;

    public GBook(String bookTitle, String[] bookAuthors, String bookDescription, String bookInfoLink) {
        mBookTitle = bookTitle;
        mBookAuthors = bookAuthors;
        mBookDescription = bookDescription;
        mBookInfoLink = bookInfoLink;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String[] getAuthors() {
        return mBookAuthors;
    }

    public String generateStringOfAuthor() {
        String s = "";
        for(int i=0;i<mBookAuthors.length;i++) {
            if(i == mBookAuthors.length-1)
                s += mBookAuthors[i];
            else
                s += mBookAuthors[i] + ", ";
        }
        return s;
    }

    public String getBookDescription() {
        return mBookDescription;
    }

    public String getBookInfoLink() {
        return mBookInfoLink;
    }
}
