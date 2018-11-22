package com.example.jayeshdk.receivedatabase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BackgroundTask extends AsyncTask<Void, Data, Void> {
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ProgressDialog progressDialog;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Data> arrayList = new ArrayList<>();
    public BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
        activity = (Activity)ctx;
    }

    String json_string="http://10.10.40.161/Fruit_Details/data.php";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line ;

            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }

            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("Server Response");
            int count=0;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                Data data = new Data(JO.getString("name"),JO.getInt("calories"),JO.getDouble("fat"));
                publishProgress(data);
                Thread.sleep(1000);
            }

            Log.d("JSON STRING",json_string);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

       recyclerView = (RecyclerView)activity.findViewById(R.id.recyclerView);
       layoutManager = new LinearLayoutManager(ctx);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setHasFixedSize(true);
       adapter = new RecyclerAdapter(arrayList);
       recyclerView.setAdapter(adapter);
       progressDialog = new ProgressDialog(ctx);
       progressDialog.setTitle("Please Wait...");
       progressDialog.setMessage("Data is Loading !!!");
       progressDialog.setIndeterminate(true);
       progressDialog.setCancelable(false);
       progressDialog.show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Data... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }
}
