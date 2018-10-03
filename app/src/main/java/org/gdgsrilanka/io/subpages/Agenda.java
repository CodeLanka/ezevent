package org.gdgsrilanka.io.subpages;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.gdgsrilanka.io.R;
import org.gdgsrilanka.io.subpages.agenda.AgendaAdapter;
import org.gdgsrilanka.io.subpages.agenda.AgendaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Agenda extends Fragment {

    FirebaseDatabase database;
    ProgressDialog pd;

    private RecyclerView rv;
    private AgendaAdapter cadapter;
    public List<AgendaItem> data;

    public Agenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agenda, container, false);

        rv = v.findViewById(R.id.agenda_view);
        data = new ArrayList<>();

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();

        pd = new ProgressDialog(getActivity());
        pd.setMessage(getString(R.string.progress_dialog_agenda));
        pd.show();

        return v;
    }

    @Override
    public void onStart() {

        Query agendaRef = database.getReference("agenda");
        //agendaRef.keepSynced(true);
        agendaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                getAllComments(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    private void getAllComments(DataSnapshot dataSnapshot) {
        pd.dismiss();
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

            String event = singleSnapshot.child("event").getValue().toString();
            String time = singleSnapshot.child("time").getValue().toString();
            int type = Integer.parseInt(singleSnapshot.child("type").getValue().toString());

            AgendaItem comm = new AgendaItem();
            comm.setEvent(event);
            comm.setTTime(time);
            comm.setType(type);

            data.add(comm);
        }

        cadapter = new AgendaAdapter(getActivity(), data);
        rv.setAdapter(cadapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
