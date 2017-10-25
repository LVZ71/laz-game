package com.lazvall5689.redsadventure.web;

import android.os.AsyncTask;

import com.lazvall5689.redsadventure.Scene;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GetHtml extends AsyncTask<String, Void, ArrayList<Scene>> {
    private OnHtmlRetrieved thisHtmlRetrieved;

    public GetHtml(OnHtmlRetrieved onHtmlRetrieved) {
        thisHtmlRetrieved = onHtmlRetrieved;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Scene> doInBackground(String... urls) {
        String html;
        try {
            html = Jsoup.connect(urls[0]).get().html();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


        Document document = Jsoup.parse(html);
        Element table = document.select("table").get(0);
        Elements rows = table.select("tr");

        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) { // start at 1 to skip title
            Element row = rows.get(i);
            String title = row.child(0).html();
            String content = row.child(1).html();
            String routes = row.child(2).html();
            String item = row.child(3).html();
            scenes.add(new Scene(title, content, routes, item));
        }

        return scenes;
    }

    @Override
    protected void onPostExecute(ArrayList<Scene> list) {
        thisHtmlRetrieved.scenesRetrieved(list);
    }
}
