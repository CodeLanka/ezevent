package org.gdgsrilanka.io.subpages.agenda;

public class AgendaItem {

    private String event;
    private String time;
    private int type;

    public AgendaItem(){}

    public AgendaItem(String event, String time, int type){
        this.event = event;
        this.time = time;
        this.type = type;
    }

    public void setEvent(String event){
        this.event = event;
    }

    public void setTTime(String time){
        this.time = time;
    }

    public void setType(int type){
        this.type = type;
    }

    public String getEvent(){
        return event;
    }

    public String getTTime(){
        return time;
    }

    public int getType(){
        return type;
    }

}
