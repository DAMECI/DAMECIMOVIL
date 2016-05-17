package com.example.estudiantes.lab8diegobecerra_danielaescobar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainDiegoDaniela extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.LogIn);
        Button boton= (Button) findViewById(R.id.ingresar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario= ((EditText)findViewById(R.id.user)).getText().toString();
                String password= ((EditText)findViewById(R.id.pass)).getText().toString();
                if (usuario.equals("dam")&& password.equals("dam") ){
                    Intent nuevoformato = new Intent(MainDiegoDaniela.this,secundario.class);
                    startActivity(nuevoformato);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

            public void Agregar(View v) throws JSONException {
                EditText id = (EditText) this.findViewById(R.id.idProducto);
                EditText name = (EditText) this.findViewById(R.id.nombreProducto);
                EditText price = (EditText) this.findViewById(R.id.precioProducto);

                //construir un objeto jSON
                JSONObject jso=new JSONObject();
                jso.put("idproducto",id.getText());
                jso.put("nombre",name.getText());
                jso.put("precio", price.getText());

                AsyncTask task = new Productos();
                task.execute(new JSONObject[]{jso});


            }

}
