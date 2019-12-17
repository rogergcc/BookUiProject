package com.rogergcc.bookuiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.rogergcc.bookuiproject.api.Utils;
import com.rogergcc.bookuiproject.model.Book;
import com.rogergcc.bookuiproject.model.GBook;
import com.rogergcc.bookuiproject.model.GBookRequest;
import com.rogergcc.bookuiproject.recyclerview.BookAdapter;
import com.rogergcc.bookuiproject.recyclerview.BookItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BookItemClickListener {
    //other way https://mikescamell.com/shared-element-transitions-part-4-recyclerview/
    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        //initmdataBooks();
        getBookByVolumens("developer","newest");



    }

    private void setupBookAdapter() {
        bookAdapter = new BookAdapter(mdata,this);
        rvBooks.setAdapter(bookAdapter);
    }

    void getBookByVolumens(String vol,String orderby) {

//        view.showLoading();
        Call<GBookRequest> mealsCall = Utils.getApi().getBookByVolumens(vol,orderby);
        mealsCall.enqueue(new Callback<GBookRequest>() {
            @Override
            public void onResponse(@NonNull Call<GBookRequest> call,@NonNull Response<GBookRequest> response) {
//                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
//                    view.setMeals(response.body().getMeals());
                   mdataGoogleBooks(response.body().getItems());

                } else {
//                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GBookRequest> call,@NonNull Throwable t) {
//                view.hideLoading();
//                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
    public static double getRandom(double min, double max) {
        return (Math.random() * (max + 1 - min)) + min;
    }

    public static int nextInt(int min, int max) {
         Random random = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return random.nextInt((max - min) + 1) + min;
    }

    private void mdataGoogleBooks(List<GBookRequest.Items> gBooksRequest) {
        //for tesing purpos im creting randon set of books
        //u may get yr data from web services or firebase
        mdata = new ArrayList<>();

        for (int i = 0; i < gBooksRequest.size(); i++) {
            Book book = new Book();
            GBookRequest.Items gbookitem = gBooksRequest.get(i);

            int random= new Random().nextInt(5)+1;

            int randomreview = (int )(Math.random() * 90 + 1);
            float  randomrating = (float )(Math.random() * 5 + 1);

            book.setTitle(gbookitem.getVolumeInfo().getTitle());

            book.setImgUrl(gbookitem.getVolumeInfo().getImageLinks().getThumbnail());

            String authors = TextUtils.join(",", gbookitem.getVolumeInfo().getAuthors());
            book.setAuthor(authors);

            book.setPages(gbookitem.getVolumeInfo().getPageCount());
            book.setReview(randomreview);
            book.setRating(randomrating);

            book.setDescription(gbookitem.getVolumeInfo().getDescription());

            mdata.add(book);

        }

        setupBookAdapter();

    }

    private void initmdataBooks() {
        //for tesing purpos im creting randon set of books
        //u may get yr data from web services or firebase
        mdata = new ArrayList<>();
        mdata.add(new Book("He dies with his eyes open","lorem titulo 1asffasf.","Derek Raymond","",520,42,4.3f,R.drawable.hediedwith));
        mdata.add(new Book("Where you go bernabette","lorem titulo 1asffasf.","Maria Simple","",500,32,4.1f,R.drawable.mariasemples));
        mdata.add(new Book("Privacy","lorem titulo 1asffasf999.","Geret feiker","",420,35,5.0f,R.drawable.privacy));
        mdata.add(new Book("The Martian","lorem titulo 1asffasf8888.","Andy Weir","",320,22,4.0f,R.drawable.themartian));
        mdata.add(new Book("The Vegetarian","lorem titulo 1asffasf4455.","Han Kan","",660,25,4.5f,R.drawable.thevigitarian));
        mdata.add(new Book("The Wild Robot","lorem titulo 1asffa123123.","Peter Brown","",360,72,3.3f,R.drawable.thewildrobot));


    }

    private void initViews() {
        rvBooks = findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);
    }

    @Override
    public void onBookClick(Book book, ImageView bookImageView) {

        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this,BookDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",book.getTitle());
        intent.putExtra("imgURL",book.getImgUrl());
        intent.putExtra("imgAuthor",book.getAuthor());
        intent.putExtra("imgDescrition",book.getDescription());
        intent.putExtra("imgRating",book.getRating());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                bookImageView,"sharedName");
//
        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        Toast.makeText(this,"item clicked : " + book.getTitle(),Toast.LENGTH_LONG).show();
        // it works great
    }
}
