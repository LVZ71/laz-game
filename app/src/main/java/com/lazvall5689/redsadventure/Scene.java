package com.lazvall5689.redsadventure;

/**
 * Created by oscartorres on 10/21/17.
 */

public class Scene {

    private String title;
    private String content;
    private int[] routes;
    private String item;

    public Scene(String title, String content, String routes, String item) {
        this.title = title;
        this.content = content;
        // trim excessive spaces and split string on whitespace
        String[] arr = routes.trim().split("\\s+");
        this.routes = strToInt(arr);
        this.item = item;
    }

    private int[] strToInt(String[] arr) {
        int[] ruts = new int[arr.length];
        for (int x = 0; x < arr.length; x++) {
            ruts[x] = parseInt(arr[x]);
        }
        return ruts;
    }

    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            // couldn't parse int, return -1 to notify
            return -1;
        }
    }

    private String intArrToStr(int[] ints) {
        StringBuilder builder = new StringBuilder();
        for (int a : ints) {
            builder.append(a);
            builder.append(" ");
        }
        return builder.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int[] getRoutes() {
        return routes;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content +
                "\nRoutes: " + intArrToStr(routes) + "\nItem: " + item;
    }
}
