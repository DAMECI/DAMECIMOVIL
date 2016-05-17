package com.example.estudiantes.lab8diegobecerra_danielaescobar;

import android.os.AsyncTask;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
/**
 * Created by estudiantes on 11/05/16.
 */
public class Productos extends AsyncTask<JSONObject, Integer, String> {



    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {

	//Productos
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        JSONObject product= (JSONObject)params[0];
        DefaultHttpClient dhhtpc=new DefaultHttpClient();
        HttpPost postreq=new HttpPost("https://products-catalog-api.herokuapp.com/products");
        postreq.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("user", "user"), "UTF-8", false));

        //agregar la versión textual del documento jSON a la petición
        StringEntity se= null;
        try {
            se = new StringEntity(product.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        se.setContentType("application/json;charset=UTF-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
        postreq.setEntity(se);

        //ejecutar la petición
        HttpResponse httpr= null;
        String reqResponse = "";
        try {
            httpr = dhhtpc.execute(postreq);

            reqResponse= EntityUtils.toString(httpr.getEntity());
        } catch (IOException e  ) {
            e.printStackTrace();
        }
        return reqResponse;
    }




}
