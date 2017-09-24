package com.example.lilei.gank.event;

public class MessageEvent {

    public MessageEvent(int id){
        setId(id);
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
