package org.gdgsrilanka.io.subpages.agenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.gdgsrilanka.io.R;

import java.util.Collections;
import java.util.List;


public class AgendaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;
    List<AgendaItem> data= Collections.emptyList();
    AgendaItem current;

    public AgendaAdapter(Context context, List<AgendaItem> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.agenda_item_default, parent, false);

        MyHolder holder=new MyHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder= (MyHolder) holder;
        current=data.get(position);

        myHolder.tvEvent.setText(current.getEvent());
        myHolder.tvTime.setText(current.getTTime());

        switch(current.getType()){
            case 1:
                myHolder.icon.setImageResource(R.drawable.ic_create_black_24dp);
                break;
            case 2:
                myHolder.icon.setImageResource(R.drawable.ic_people_outline_black_24dp);
                break;
            case 3:
                myHolder.icon.setImageResource(R.drawable.ic_music_note_black_24dp);
                break;
            case 4:
                myHolder.icon.setImageResource(R.drawable.ic_restaurant_black_24dp);
                break;
            case 5:
                myHolder.icon.setImageResource(R.drawable.ic_star_black_24dp);
                break;
            case 7:
                myHolder.icon.setImageResource(R.drawable.ic_photo_camera_black_24dp);
                break;
           default:
                myHolder.icon.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView tvEvent, tvTime;
        ImageView icon;

        public MyHolder(final View itemView) {
            super(itemView);

            tvEvent = itemView.findViewById(R.id.subheading);
            tvTime = itemView.findViewById(R.id.time);
            icon = itemView.findViewById(R.id.agendaicon);
        }

    }
}
