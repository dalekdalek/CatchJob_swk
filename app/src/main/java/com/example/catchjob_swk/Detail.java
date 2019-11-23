package com.example.catchjob_swk;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Detail extends AppCompatActivity {

   LinearLayout mainlayout;
   TextView titleview;
   TextView nameview;
   TextView contentsview;
    EditText commentfield;
    Button commit;
    Post mypost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainlayout= (LinearLayout) findViewById(R.id.detailmainlayout);

        mypost=(Post)getIntent().getSerializableExtra("thispost");
        titleview= new TextView(this);
        titleview.setText(mypost.getTitle());
        titleview.setTextSize(31);
        nameview= new TextView(this);
        nameview.setTextSize(20);
        nameview.setText(mypost.getName());
        contentsview= new TextView(this);
        contentsview.setText(mypost.getContents());
        contentsview.setTextSize(19);
        commentfield= new EditText(this);
        commit= new Button(this);
        commit.setText("등록");
        mainlayout.addView(titleview);
        mainlayout.addView(nameview);
        mainlayout.addView(contentsview);
        addmargin();
        mainlayout.addView(commentfield);
        mainlayout.addView(commit);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }  TextView maketextView(){
        TextView dynamicTextView = new TextView(this);
       /*dynamicTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
               LinearLayout.LayoutParams.WRAP_CONTENT));*/
        return dynamicTextView;
    }
    void addmargin(){
        TextView titleview=maketextView();
        mainlayout.addView(titleview);
    }
}
