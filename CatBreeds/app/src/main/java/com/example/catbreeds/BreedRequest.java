package com.example.catbreeds;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

class BreedRequest extends AsyncTask<String, String, String> {
    Context context;
    TextView showBreed;
    TextView showDescription;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getShowBreed() {
        return showBreed;
    }

    public void setShowBreed(TextView showBreed) {
        this.showBreed = showBreed;
    }

    public TextView getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(TextView showDescription) {
        this.showDescription = showDescription;
    }

    BreedRequest(Context context, TextView tV, TextView tV2) {
        this.context = context;
        showBreed = tV;
        showDescription= tV2;
    }

    @Override
    protected String doInBackground(String... params) {
        final String app_key = "e788ec2e-5182-4505-b2cc-1a98f238578b";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @Override
    protected void onPostExecute(String name) {
        super.onPostExecute(name);
        String def;
        String desc;
        try {
            JSONArray search = null;
            JSONObject js = new JSONObject((Map) search);
            search = js.getJSONArray("search");
            JSONObject js0 = search.getJSONObject(0);

            JSONObject js1 = new JSONObject();
            JSONArray js3 = js1.getJSONArray("name");
            def = js3.getString(0);
            JSONObject js5= new JSONObject();
            JSONArray js6 =js5.getJSONArray("description");
            desc = js6.getString(0);
            showBreed.setText(def);
            showDescription.setText(desc);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
