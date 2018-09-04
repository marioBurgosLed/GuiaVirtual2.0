package com.example.note.guiavirtual.OTs;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.note.guiavirtual.R;

import org.json.JSONObject;

import java.util.Calendar;

import static org.json.JSONObject.NULL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentOTs.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentOTs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOTs extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText etIdEquipo,etDetalle, etFecha,etTipo, etEstado, etUsu;
    Button btEscanear, btGenerarOT;
    int dia,mes,anio;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public FragmentOTs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOTs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOTs newInstance(String param1, String param2) {
        FragmentOTs fragment = new FragmentOTs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_fragment_ots, container, false);

        etIdEquipo=(EditText) vista.findViewById(R.id.etIdEquipo);
        etDetalle=(EditText) vista.findViewById(R.id.etDetalleOT);
        etFecha=(EditText) vista.findViewById(R.id.etFechaOT);
        etTipo=(EditText) vista.findViewById(R.id.etIdTipo);
        etEstado=(EditText) vista.findViewById(R.id.etIdEstado);
        etUsu=(EditText) vista.findViewById(R.id.etIdUsuario);
        btEscanear=(Button) vista.findViewById(R.id.btScan);
        btGenerarOT=(Button) vista.findViewById(R.id.btAltaOT);

        request= Volley.newRequestQueue(getContext());

        btGenerarOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v==btGenerarOT){
                    final Calendar c=Calendar.getInstance();
                    dia=c.get(Calendar.DAY_OF_MONTH);
                    mes=c.get(Calendar.MONTH);
                    anio=c.get(Calendar.YEAR);
                    etFecha.setText(anio+"-"+mes+"-"+dia);

                    //etUsu.setText(idUsuariooo);
                    //Toast.makeText(getContext(),"Valor en Fragment: "+idUsuariooo,Toast.LENGTH_SHORT).show();
                }
                GenerarOT();
            }
        });


        return vista;
    }

    private void GenerarOT() {

        String urlAltaOT = "http://sigequip.esy.es/OTs/AltaOT.php?descOT=" + etDetalle.getText().toString()+
                                                        "&idUsuario=" + etUsu.getText().toString()+
                                                        "&idEquipo=" + etIdEquipo.getText().toString() +
                                                        "&idEstado=" + etEstado.getText().toString() +
                                                        "&fechaOT=" + etFecha.getText().toString() +
                                                        "&idTipo=" + etTipo.getText().toString();



        urlAltaOT=urlAltaOT.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlAltaOT, null,this,this);
        if(jsonObjectRequest!=NULL){
        request.add(jsonObjectRequest);}
        else {        Toast.makeText(getContext(),"No funciona...",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se guardó el registro...",Toast.LENGTH_LONG).show();
        etDetalle.setText("");
        etUsu.setText("");
        etIdEquipo.setText("");
        etEstado.setText("0");
        etFecha.setText("");
        etTipo.setText("0");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Se produjo un error..."+error.toString(),Toast.LENGTH_LONG).show();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
