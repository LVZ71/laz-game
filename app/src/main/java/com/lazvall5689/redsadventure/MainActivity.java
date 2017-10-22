package com.lazvall5689.redsadventure;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lazvall5689.redsadventure.web.GetHtml;
import com.lazvall5689.redsadventure.web.OnHtmlRetrieved;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context mainContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        new GetHtml(new OnHtmlRetrieved() {
            @Override
            public void onHtmlRetrieved(String rawData) {
                Log.d("hey", rawData);
            }
        }).execute("https://laz-game.herokuapp.com/");
    }
}
