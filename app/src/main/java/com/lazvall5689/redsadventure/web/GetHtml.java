package com.lazvall5689.redsadventure.web;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by oscartorres on 10/17/17.
 */

public class GetHtml extends AsyncTask<String, Void, String> {
    private OnHtmlRetrieved thisHtmlRetrieved;

    public GetHtml(OnHtmlRetrieved onHtmlRetrieved) {
        thisHtmlRetrieved = onHtmlRetrieved;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        String html ="";
        try {
            html =  Jsoup.connect(urls[0]).get().html();
        }catch (Exception e){
            e.printStackTrace();
        }


        Document document = Jsoup.parse(html);
        return document.html();
    }

    @Override
    protected void onPostExecute(String rawData) {
        thisHtmlRetrieved.onHtmlRetrieved(rawData);
    }
}
