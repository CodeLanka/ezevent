package org.gdgsrilanka.io.subpages.speakers;

import android.graphics.drawable.Drawable;

public class SpeakerItem {

    private String speakername;
    private String speakerrole;
    private int speakerimg;

    public SpeakerItem(){}

    public SpeakerItem(String speakername, String speakerrole, int speakerimg){
        this.speakername = speakername;
        this.speakerrole = speakerrole;
        this.speakerimg = speakerimg;
    }

    public void setSpeakername(String speakername){
        this.speakername = speakername;
    }

    public void setSpeakerrole(String speakerrole){
        this.speakerrole = speakerrole;
    }

    public void setSpeakerimg(int speakerimg){
        this.speakerimg = speakerimg;
    }

    public String getSpeakername(){
        return speakername;
    }

    public String getSpeakerrole(){
        return speakerrole;
    }

    public int getSpeakerimg(){
        return speakerimg;
    }

}
