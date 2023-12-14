/*package com.android.hmh.googlepalmaichatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextInputEditText userEntered;
    TextView showOutputHere;
    Button submitBtn;
    ProgressBar progressBar;
    String apiKey = "AIzaSyBdYrFbOVdhQZLrEuwnk1FjmoCUWquHjxQ";
    String apiCallUrlEndPoint = "https://generativelanguage.googleapis.com/v1beta3/models/text-bison-001:generateText?key="+apiKey;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEntered = findViewById(R.id.userEntered);
        submitBtn = findViewById(R.id.submitBtn);
        showOutputHere = findViewById(R.id.showOutputHere);
        progressBar = findViewById(R.id.progressBar);

        showOutputHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                askAI();
            }
        });
    }

    private void askAI(){
        // Inside the askAI method
        RequestQueue queue = Volley.newRequestQueue(this);
        String whatToAskAI = "If a patient says, I have Cold and cough and sneezing for a doctor, then what can be the possible diagnosis for the patient, Act as a doctor and tell me some rough idea";

        if (!userEntered.getText().toString().trim().isEmpty()) {
            whatToAskAI = userEntered.getText().toString().trim();
        }

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectText = new JSONObject();
        try {
            jsonObjectText.put("text", whatToAskAI);
            jsonObject.put("prompt", jsonObjectText);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, apiCallUrlEndPoint, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String gotResponse = response.getJSONArray("candidates")
                                    .getJSONObject(0)
                                    .getString("output");
                            showOutputHere.setText(gotResponse);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showOutputHere.setText("Some Error bro, idk wats tht error");
                Toast.makeText(MainActivity.this, "IDK Wats tht error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> apiHeader = new HashMap<>();
                apiHeader.put("Content-Type", "application/json");
                return apiHeader;
            }
        };

        int timeOut = 60000; // 60 Seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(retryPolicy);

        queue.add(jsonObjectRequest);
    }
    private void aaskAI() {
        String whatToAskAI = "If a patient says, I have Cold and cough and sneezing for a doctor, then what can be the possible diagnosis for the patient, Act as a doctor and tell me some rough idea";

        if(!userEntered.getText().toString().trim().isEmpty()){
            whatToAskAI = userEntered.getText().toString().trim();
        }

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectText = new JSONObject();
        try {
            jsonObjectText.put("text", whatToAskAI);
            jsonObject.put("prompt", jsonObjectText);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiCallUrlEndPoint,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String gotResponse = response.getJSONArray("candidates")
                            .getJSONObject(0)
                            .getString("output");
                    showOutputHere.setText(gotResponse);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showOutputHere.setText("Some Error bro, idk wats tht error");
                Toast.makeText(MainActivity.this, "IDK Wats tht error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> apiHeader = new HashMap<>();
                apiHeader.put("Content-Type", "application/json");
                return apiHeader;
            }
        };

        int timeOut = 60000; //60 Seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
    }
}
*/























/*
package com.android.hmh.googlepalmaichatbot;

        import android.annotation.SuppressLint;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.material.textfield.TextInputEditText;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextInputEditText userEntered;
    TextView showOutputHere;
    Button submitBtn;
    ProgressBar progressBar;

    String apiKey = "AIzaSyBdYrFbOVdhQZLrEuwnk1FjmoCUWquHjxQ";
    String apiCallUrlEndPoint = "https://generativelanguage.googleapis.com/v1beta3/models/text-bison-001:generateText?key=" + apiKey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEntered = findViewById(R.id.userEntered);
        submitBtn = findViewById(R.id.submitBtn);
        showOutputHere = findViewById(R.id.showOutputHere);
        progressBar = findViewById(R.id.progressBar);

        showOutputHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                askAI();
            }
        });
    }

    private void askAI() {
        String whatToAskAI = "If a patient says, I have Cold and cough and sneezing for a doctor, then what can be the possible diagnosis for the patient, Act as a doctor and tell me some rough idea";

        if (!userEntered.getText().toString().trim().isEmpty()) {
            whatToAskAI = userEntered.getText().toString().trim();
        }

        new AIRequestTask().execute(whatToAskAI);
    }

    private class AIRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String apiUrl = apiCallUrlEndPoint + "&prompt=" + params[0];
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }

                reader.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                String gotResponse = new JSONObject(response)
                        .getJSONArray("candidates")
                        .getJSONObject(0)
                        .getString("output");

                showOutputHere.setText(gotResponse);
                progressBar.setVisibility(View.GONE);
            } catch (JSONException e) {
                showOutputHere.setText("Some Error bro, idk wats tht error");
                Toast.makeText(MainActivity.this, "IDK Wats tht error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

*/



/*
package com.android.hmh.googlepalmaichatbot;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userEntered;
    private Button submitBtn;
    private TextView showOutputHere;
    private ProgressBar progressBar;

    private final String apiKey = "AIzaSyBdYrFbOVdhQZLrEuwnk1FjmoCUWquHjxQ";
    private final String apiCallUrlEndPoint = "https://generativelanguage.googleapis.com/v1beta3/models/text-bison-001:generateText?key=" + apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEntered = findViewById(R.id.userEntered);
        submitBtn = findViewById(R.id.submitBtn);
        showOutputHere = findViewById(R.id.showOutputHere);
        progressBar = findViewById(R.id.progressBar);

        showOutputHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                askAI();
            }
        });
    }

    private void askAI() {
        String whatToAskAI = userEntered.getText().toString().trim();
        if (whatToAskAI.isEmpty()) {
            whatToAskAI = "If a patient says, I have Cold and cough and sneezing for a doctor, then what can be the possible diagnosis for the patient, Act as a doctor and tell me some rough idea";
        }

        new AskAIAsyncTask().execute(whatToAskAI);
    }

    @SuppressLint("StaticFieldLeak")
    private class AskAIAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String whatToAskAI = params[0];
            String apiUrl = apiCallUrlEndPoint + "&prompt=" + whatToAskAI;

            try {
                URL url = new URL(apiUrl);
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
                Toast.makeText(MainActivity.this, "IDK Wats tht error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
*/


package com.android.hmh.googlepalmaichatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userEntered;
    private TextView showOutputHere;
    private Button submitBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEntered = findViewById(R.id.userEntered);
        submitBtn = findViewById(R.id.submitBtn);
        showOutputHere = findViewById(R.id.showOutputHere);
        progressBar = findViewById(R.id.progressBar);

        showOutputHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                askAI();
            }
        });
    }

    private void askAI() {
        String whatToAskAI = "If a patient says, I have Cold and cough and sneezing for a doctor, then what can be the possible diagnosis for the patient, Act as a doctor and tell me some rough idea";

        if (!userEntered.getText().toString().trim().isEmpty()) {
            whatToAskAI = userEntered.getText().toString().trim();
        }

        AskAIAsyncTask asyncTask = new AskAIAsyncTask(showOutputHere, progressBar);
        asyncTask.execute(whatToAskAI);
    }
}
