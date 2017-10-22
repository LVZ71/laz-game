package com.lazvall5689.redsadventure;

/**
 * Created by oscartorres on 10/21/17.
 */

public class Scene {

    public String title;
    public String content;
    public String[] routes;
    public String item;

    public Scene(String title, String content, String routes, String item){
        this.title = title;
        this.content = content;
        this.routes = routes.trim().replaceAll("\\s+", " ").split(" ");
        this.item = item;
    }
}
