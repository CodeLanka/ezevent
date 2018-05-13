package org.gdgsrilanka.io.subpages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gdgsrilanka.io.R;
import org.gdgsrilanka.io.subpages.speakers.SpeakerAdapter;
import org.gdgsrilanka.io.subpages.speakers.SpeakerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Speakers extends Fragment {


    private RecyclerView rv;
    private SpeakerAdapter cadapter;
    public List<SpeakerItem> data;

    public Speakers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_speakers, container, false);

        rv = v.findViewById(R.id.speakerview);
        data = new ArrayList<>();

        //arrays
        String names[] = {"Roshanth Gardiarachchi","Prash Balakrishnan","Rainer Deutschmann","Kumar Sangakkara","Peter De Almeida","Sammani Kusaladharma","Paul Ravindranath","Tharaka Devinda","Dilini Weerasinghe","Lim Shang Yi","Keshan Sodimana","Richa Singh"};
        String roles[] = {"Senior Manager, Ideamart","Google Country Consultant","Group Chief Operating Officer, Dialog Axiata PLC","Former Sri Lanka Cricket Captain","Managing Director and Chief Executive, N-Able","Senior Executive - IdeaMart","Regional Lead, DevRel India","GDG Sri Lanka","GDG Sri Lanka","Google Developer Expert","Google Engineering Consultant","Program Manager, Google Crowdsource"};
        int images[] = {R.drawable.roshanth,R.drawable.prash,R.drawable.rainer,R.drawable.sanga,R.drawable.peter,R.drawable.sammani,R.drawable.paul,R.drawable.tharaka,R.drawable.dilini,R.drawable.lim,R.drawable.keshan,R.drawable.richa};

        for(int i=0;i<12;i++){

            SpeakerItem comm = new SpeakerItem();
            comm.setSpeakername(names[i]);
            comm.setSpeakerrole(roles[i]);
            comm.setSpeakerimg(images[i]);

            data.add(comm);

        }

        cadapter = new SpeakerAdapter(getActivity(), data);
        rv.setAdapter(cadapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

}
