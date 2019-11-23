package com.example.catchjob_swk;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class timeline extends AppCompatActivity {
    LinearLayout layout = null;
    UserAccount myaccount;
    FirebaseFirestore database;
    ScrollView timelinescroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout= (LinearLayout) findViewById(R.id.time_line_main);

        Intent i = getIntent();
        myaccount = (UserAccount)i.getSerializableExtra("my_account");
//Setting the above params to our TextView
        database= FirebaseFirestore.getInstance();
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadPost();
    }


    void loadPost(){
        if(myaccount.getFollowers()==null) return;
        database.getInstance().collection("Post").whereIn("owner", myaccount.getFollowers()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post my_post= document.toObject(Post.class);
                                printAPost(my_post);
                            }
                        } else {
                        }
                    }
                });



    }

   TextView maketextView(){
       TextView dynamicTextView = new TextView(this);
       /*dynamicTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
               LinearLayout.LayoutParams.WRAP_CONTENT));*/
        return dynamicTextView;
   }
    void addtitle(Post mypost){
        TextView titleview=maketextView();
        onClickTimeline myclick= new onClickTimeline(timeline.this,mypost);
        titleview.setOnClickListener(myclick );
        titleview.setTextSize(25);
        titleview.setText(mypost.getTitle());
        layout.addView(titleview);


    }
    void addcontent(Post mypost){
        TextView contentview=maketextView();
        onClickTimeline myclick= new onClickTimeline(timeline.this,mypost);
        contentview.setOnClickListener(myclick );
        contentview.setTextSize(19);
        if(mypost.getContents().length()>100){
                contentview.setText(mypost.getContents().substring(0,100)+"...");
        }else {
            contentview.setText(mypost.getContents());
        }
        layout.addView(contentview);
    }
    void addname(Post mypost){
        TextView nameview=maketextView();
        nameview.setTextSize(15);
        nameview.setText(mypost.getName());
        layout.addView(nameview);
    }
    void adddate(Timestamp date){
        TextView titleview=maketextView();
        titleview.setText(date.toDate().toString());
        layout.addView(titleview);
    }
    void addmargin(){
        TextView titleview=maketextView();
        layout.addView(titleview);
    }
    void printAPost(Post mypost){

        addtitle(mypost);
        addname(mypost);
        addcontent(mypost);
                                /*Timestamp aaa=(Timestamp)document.getData().get("uploadtime");
                                adddate(aaa);*/
        addmargin();

    }
    void toDeatilActivity(){

    }
}

