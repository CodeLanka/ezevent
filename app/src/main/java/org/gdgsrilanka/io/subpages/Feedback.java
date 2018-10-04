package org.gdgsrilanka.io.subpages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.gdgsrilanka.io.R;
import org.gdgsrilanka.io.subpages.feedback.FeedbackItem;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {


    public Feedback() {
        // Required empty public constructor
    }

    ImageView bg;
    EditText question;
    Button submit;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_feedback, container, false);

        question = v.findViewById(R.id.feedback);
        submit = v.findViewById(R.id.fbsubmit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("feedbacks");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(question.getText().toString().trim().length() == 0){
                    Toast.makeText(getContext(), R.string.feedback_enter, Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        String email = user.getEmail();
                        String name = user.getDisplayName();

                        Long tsLong = System.currentTimeMillis()/1000;
                        String timestamp = tsLong.toString();

                        String prashne = question.getText().toString();

                        String key = myRef.child("feedbacks").push().getKey();
                        FeedbackItem questiono = new FeedbackItem(name, email, prashne, timestamp);
                        Map<String, Object> postValues = questiono.toMap();

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put(key, postValues);

                        myRef.updateChildren(childUpdates);

                        Toast.makeText(getContext(),"Feedback successfully sent.",Toast.LENGTH_SHORT).show();
                        question.setText("");

                    }
                }
            }
        });

        return v;
    }

}
