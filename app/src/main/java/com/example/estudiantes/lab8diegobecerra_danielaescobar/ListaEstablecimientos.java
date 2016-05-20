package com.example.estudiantes.lab8diegobecerra_danielaescobar;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 2087151 on 18/05/16.
 */
public class ListaEstablecimientos extends ActionBarActivity {
    private Context damcontext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaestablecimientos);
        Establecimientostask establecimiento= (Establecimientostask) new Establecimientostask().execute();

    }
    private class Establecimientostask extends AsyncTask<Void,Void,JSONArray>{

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            TableLayout tableestable= (TableLayout) findViewById(R.id.tablaestablecimiento);
            tableestable.removeAllViews();
            TableRow tr_head = new TableRow(damcontext);
            tr_head.setId(10);
            tr_head.setBackgroundColor(Color.BLACK);
            tr_head.setLayoutParams(new Toolbar.LayoutParams(
                    Toolbar.LayoutParams.FILL_PARENT,
                    Toolbar.LayoutParams.WRAP_CONTENT));


            TextView label_nit = new TextView(damcontext);
            label_nit.setId(20);
            label_nit.setText("Nombre");
            label_nit.setTextColor(Color.WHITE);
            label_nit.setPadding(15, 15, 15, 15);
            tr_head.addView(label_nit);// add the column to the table row here

            TextView label_name = new TextView(damcontext);
            label_name.setId(21);// define id that must be unique
            label_name.setText("Direccion"); // set the text for the header
            label_name.setTextColor(Color.WHITE); // set the color
            label_name.setPadding(15, 15, 15, 15); // set the padding (if required)
            tr_head.addView(label_name); // add the column to the table row here

            tableestable.addView(tr_head, new TableLayout.LayoutParams(
                    Toolbar.LayoutParams.FILL_PARENT,
                    Toolbar.LayoutParams.WRAP_CONTENT));

            System.out.println(jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    final JSONObject estableci = (JSONObject) jsonArray.get(i);
                    TableRow tr = new TableRow(damcontext);
                    tr.setBackgroundColor(Color.GRAY);
                    //}
                    tr.setId(100 + i);
                    tr.setClickable(true);
                    tr.setPadding(30, 30, 30, 30);
                    tr.setLayoutParams(new Toolbar.LayoutParams(
                            Toolbar.LayoutParams.FILL_PARENT,
                            Toolbar.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
                    // Create a TextView to add date
                    TextView id = new TextView(damcontext);
                    id.setId(200 + i);
                    id.setText("" + estableci.get("razonsocial"));
                    id.setPadding(0, 0, 0, 0);
                    id.setTextColor(Color.WHITE);
                    tr.addView(id);


                    TextView direccion = new TextView(damcontext);
                    direccion.setId(200 + i);
                    direccion.setText(estableci.get("direccion") + "");
                    direccion.setPadding(2, 0, 5, 0);
                    //price.setPadding(2, 0, 5, 0);
                    direccion.setTextColor(Color.WHITE);
                    tr.addView(direccion);



                    tableestable.addView(tr, new TableLayout.LayoutParams(
                            Toolbar.LayoutParams.FILL_PARENT,
                            Toolbar.LayoutParams.WRAP_CONTENT));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONArray establecimiento= null;
            String userPass = "dam:dam123";
            //String userPass = "Du:van:password";
            String basicAuth = "Basic " + new String(Base64.encodeToString(userPass.getBytes(), Base64.NO_WRAP));
            URL obj;

            try {
                obj = new URL("https://deporteatusmanos.herokuapp.com/establecimientos");
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Authorization", basicAuth);
                int rc = con.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                establecimiento = new JSONArray(response.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return establecimiento;
        }
    }



}
