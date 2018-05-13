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
import org.gdgsrilanka.io.subpages.questions.Question;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Questions extends Fragment {

    public Questions() {
        // Required empty public constructor
    }

    ImageView bg;
    EditText question;
    Button submit;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_questions, container, false);

        question = v.findViewById(R.id.question);
        submit = v.findViewById(R.id.qsubmit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(question.getText().toString().trim().length() == 0){
                    Toast.makeText(getContext(), "Please enter your question.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        String email = user.getEmail();
                        String name = user.getDisplayName();

                        Long tsLong = System.currentTimeMillis()/1000;
                        String timestamp = tsLong.toString();

                        String prashne = question.getText().toString();

                        String key = myRef.child("messages").push().getKey();
                        Question questiono = new Question(name, email, prashne, timestamp);
                        Map<String, Object> postValues = questiono.toMap();

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put(key, postValues);

                        myRef.updateChildren(childUpdates);

                        Toast.makeText(getContext(),"Question successfully posted.",Toast.LENGTH_SHORT).show();
                        question.setText("");

                    }
                }
            }
        });

        return v;
    }

}
