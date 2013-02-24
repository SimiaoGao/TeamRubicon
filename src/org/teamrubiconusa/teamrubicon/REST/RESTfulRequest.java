package org.teamrubiconusa.teamrubicon.REST;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RESTfulRequest extends AsyncTask<String, Integer, ByteArrayOutputStream>{
	
    private XMLParser parser;
    private ProgressBar progressBar;
    private Activity parent;
    
    
    public RESTfulRequest(final Activity parent, final ProgressBar progress) {
        this.parent = parent;
        this.progressBar = progress;
    }
    
    @Override
    protected ByteArrayOutputStream doInBackground(String... uri) {
    	progressBar.setVisibility(View.VISIBLE);
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        ByteArrayOutputStream responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out;
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(ByteArrayOutputStream result) {
        super.onPostExecute(result);
        if(result == null){
        	Toast.makeText(this.parent, "No Internet Connections", Toast.LENGTH_SHORT).show();
        	progressBar.setVisibility(View.GONE);
        } else{
        	parser = new XMLParser(progressBar, parent);
        	parser.execute(result.toString());
        }
    }
    

}