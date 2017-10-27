package com.lazvall5689.redsadventure;

public class Scene {

    private String title;
    private String content;
    private int[] routes;
    private String item;

    public Scene(String title, String content, String routes, String item) {
        this.title = title;
        this.content = content;
        // trim excessive spaces and split string on whitespace
        if(!routes.trim().isEmpty()) {
            String[] arr = routes.trim().split("\\s+");
            this.routes = StringUtil.strToInt(arr);
        }else{
            // empty
            this.routes = new int[0];
        }
        this.item = item;
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

    public int numRoutes(){
        return routes.length;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content +
                "\nRoutes: " + StringUtil.intArrToStr(routes) + "\nItem: " + item;
    }
}
