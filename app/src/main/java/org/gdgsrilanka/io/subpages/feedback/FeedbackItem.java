package org.gdgsrilanka.io.subpages.feedback;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FeedbackItem {

    public String name;
    public String email;
    public String question;
    public String timestamp;

    public Map<String, Boolean> stars = new HashMap<>();

    public FeedbackItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public FeedbackItem(String name, String email, String question, String timestamp) {
        this.name = name;
        this.email = email;
        this.question = question;
        this.timestamp = timestamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("Delete", "false");
        result.put("Description", question);
        result.put("Name", name);
        result.put("Time", timestamp);
        result.put("Email", email);

        return result;
    }

}