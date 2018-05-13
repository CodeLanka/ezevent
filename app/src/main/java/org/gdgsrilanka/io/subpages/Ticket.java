package org.gdgsrilanka.io.subpages;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gdgsrilanka.io.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * A simple {@link Fragment} subclass.
 */
public class Ticket extends Fragment {

    TextView ticketno, tsize;

    public Ticket() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket, container, false);

        ticketno = v.findViewById(R.id.ticketno);
        tsize = v.findViewById(R.id.tsize);

        if(isInternetAvailable()){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail().toString();
                String token = email+"your salt here";
                token = encodemd5(token);

                CheckTicket checkTicket = new CheckTicket();
                checkTicket.execute(email,token);
            }
        } else {
            Toast.makeText(getContext(),"This feature requires an internet connection.",Toast.LENGTH_SHORT).show();
        }

        return v;
    }


    public boolean isInternetAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public String encodemd5(String plaintext){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }

    class CheckTicket extends AsyncTask<String, String, String> {

        private ProgressDialog regprogress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            regprogress = new ProgressDialog(getContext());
            regprogress.setIndeterminate(true);
            regprogress.setMessage("Getting details");
            regprogress.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String email = params[0];
            String token = params[1];

            try{
                String link = "http://ticket-api.gdgsrilanka.org/?e="+email;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                request.setHeader("Authorization","Bearer "+token);
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String json = in.readLine();
                in.close();

                Log.d("JSON STRING",json);

                JSONObject jsonObject = new JSONObject(json);
                boolean valid = jsonObject.getBoolean("valid");
                String ticket = "invalid";
                if(valid == true){
                    ticket = jsonObject.getString("ticket");
                    String id = jsonObject.getString("id");
                }

                return ticket;
            }

            catch(Exception e){
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            regprogress.dismiss();

            if(s.matches("invalid")){
                Toast.makeText(getContext(),"You have not been selected to attend the event.",Toast.LENGTH_SHORT).show();
            } else {
                String[] tokens = s.split("-", -1);
                ticketno.setText(tokens[0]);
                tsize.setText(tokens[1]);
            }

        }
    }

}
