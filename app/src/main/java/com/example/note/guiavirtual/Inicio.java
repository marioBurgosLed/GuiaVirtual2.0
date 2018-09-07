package com.example.note.guiavirtual;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.note.guiavirtual.OTs.EnviarMensaje;
import com.example.note.guiavirtual.OTs.FragmentOTs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.note.guiavirtual.R.id.fragmentOTID;

public class Inicio extends Activity {

    // json object response url
    // public static final String PASOVAR="IdentificaUsu";


    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest;
    private String urlLogin = "http://sigequip.esy.es/getLogin2.php";


    // Progress dialog
    private ProgressDialog pDialog;
    private EditText etUsuario, etPass;
    private String jsonResponse;
    private TextView txtResponse;
    int auxUsuInicio,auxRolInicio;
    String nombreUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnMakeObjectRequest = (Button) findViewById(R.id.btIngresar);
        etUsuario=(EditText)findViewById(R.id.etUsu);
        etPass=(EditText)findViewById(R.id.etPass);
        // txtResponse=(TextView)findViewById(R.id.textView18);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Espere por favor...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Loguearse();
            }
        });
    }

    /*
    Metodo para loguearse
     */
    int j=0;
    private void Loguearse() {
        //final String urlJsonArry2 = "http://sigequip.esy.es/getLogin2.php";
        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlLogin, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject person = (JSONObject) response.get(i);

                        int idUsuario=person.getInt("idUsuario");
                        String nomb=person.getString("usuNombre");
                        String contra=person.getString("usuPass");
                        int idRol=person.getInt("idRol");

                        if(contra.equals(etPass.getText().toString())){
                            auxUsuInicio=idUsuario;
                            auxRolInicio=idRol;
                            nombreUsuario=nomb;
                            j=1;
                        }

                    }
                    if(j==1){
                        Toast.makeText(getApplicationContext(),"Bienvenido usuario: "+ nombreUsuario + "  IdUsuario: "+auxUsuInicio,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Inicio.this,MainActivity.class);
                        intent.putExtra("Usuario",auxUsuInicio);
                        intent.putExtra("Rol",auxRolInicio);
                        startActivity(intent);
                        j=0;

                        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("USUARIOSSS", auxUsuInicio);
                        editor.putInt("ROLLL", auxRolInicio);
                        editor.commit();


                    }else{
                        Toast.makeText(getApplicationContext(),"verifique su contraseña",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        // Toast.makeText(getApplicationContext(),"Prueba",Toast.LENGTH_SHORT).show();
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(req);
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}

