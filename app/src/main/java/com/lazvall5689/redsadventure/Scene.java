package com.lazvall5689.redsadventure;

public class Scene {

    private String title;
    private String content;
    private int[] routes;
    private String path;

    public Scene(String title, String content, String routes, String path) {
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
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public int numRoutes(){
        return routes.length;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content +
                "\nRoutes: " + StringUtil.intArrToStr(routes) + "\nPath: " + path;
    }
}
