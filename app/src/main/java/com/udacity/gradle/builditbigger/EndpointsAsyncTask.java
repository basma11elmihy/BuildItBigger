package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.example.android.androidlibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private taskListener mListner;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    public EndpointsAsyncTask() {
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:3333/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    public EndpointsAsyncTask setListner(taskListener listner){
        this.mListner = listner;
        return this;
    }

    @Override
    protected void onPostExecute(String result) {
       // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        try {
            if (mListner != null)
                this.mListner.onComplete(result);
        }catch (Exception e){
            e.printStackTrace();
            this.mListner.onComplete("");
        }
        //while testing comment the next 3 lines.
        Intent intent = new Intent(context,JokeActivity.class);
        intent.putExtra("joke",result);
        context.startActivity(intent);
    }

    public static interface taskListener{
        public void onComplete(String resultString);
    }
}
