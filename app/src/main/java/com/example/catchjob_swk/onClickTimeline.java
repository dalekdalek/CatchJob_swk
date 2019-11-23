package com.example.catchjob_swk;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import static androidx.core.content.ContextCompat.startActivity;

public class onClickTimeline implements View.OnClickListener {
    public onClickTimeline(Context mytimeline ,Post mypost) {
        this.mypost = mypost;
        this.mytimeline=mytimeline;
    }
    Post mypost;
    Context mytimeline;

    @Override

    public void onClick(View v) {
        Intent godetail=new Intent(mytimeline,Detail.class);
        godetail.putExtra("thispost",mypost);
        mytimeline.startActivity(godetail);
    }

}
