package com.android.hmh.googlepalmaichatbot;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AskAIAsyncTask extends AsyncTask<String, Void, String> {

    private TextView showOutputHere;
    private ProgressBar progressBar;

    // Constructor to receive TextView and ProgressBar references
    public AskAIAsyncTask(TextView showOutputHere, ProgressBar progressBar) {
        this.showOutputHere = showOutputHere;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(String... params) {
        String whatToAskAI = params[0];
        String apiKey = "YOUR_API_KEY"; // Replace with your actual API key
        String apiCallUrlEndPoint = "https://generativelanguage.googleapis.com/v1beta3/models/text-bison-001:generateText?key=" + apiKey;

        try {
            Uri.Builder builder = Uri.parse(apiCallUrlEndPoint).buildUpon();
            builder.appendQueryParameter("prompt", URLEncoder.encode(whatToAskAI, StandardCharsets.UTF_8.toString()));
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            StringBuilder result = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String gotResponse = jsonResponse.getJSONArray("candidates")
                    .getJSONObject(0)
                    .getString("output");

            showOutputHere.setText(gotResponse);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            showOutputHere.setText("Some Error bro, idk wats tht error");
            Toast.makeText(showOutputHere.getContext(), "IDK Wats tht error", Toast.LENGTH_SHORT).show();
        }
    }
}
