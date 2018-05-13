package org.gdgsrilanka.io.subpages.speakers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gdgsrilanka.io.R;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class SpeakerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;
    List<SpeakerItem> data= Collections.emptyList();
    SpeakerItem current;

    public SpeakerAdapter(Context context, List<SpeakerItem> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.speaker_item, parent, false);

        MyHolder holder=new MyHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder= (MyHolder) holder;
        current=data.get(position);

        myHolder.speakername.setText(current.getSpeakername());
        myHolder.speakerrole.setText(current.getSpeakerrole());
        myHolder.speakerimage.setImageResource(current.getSpeakerimg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView speakername, speakerrole;
        CircleImageView speakerimage;

        public MyHolder(final View itemView) {
            super(itemView);

            speakername = itemView.findViewById(R.id.speakertitle);
            speakerrole = itemView.findViewById(R.id.speakerrole);
            speakerimage = itemView.findViewById(R.id.speakerimage);
        }

    }
}
