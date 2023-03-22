package com.gestionmdp.gestionmdp;

public class GetUserId {
    private static GetUserId instance;

    public int id;

    private GetUserId() {

    }

    public static GetUserId getInstance() {
        if (instance == null) {
            instance = new GetUserId();
        }
        return instance;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
