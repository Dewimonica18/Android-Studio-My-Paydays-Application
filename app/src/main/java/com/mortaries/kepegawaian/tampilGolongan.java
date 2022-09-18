package com.mortaries.kepegawaian;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class tampilGolongan extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextIdGol;
    private EditText editTextNamaGol;
    private EditText editTextGapok;
    private EditText editTextTunMakan;
    private EditText editTextTunKeluarga;
    private EditText editTextTunJabatan;

    private Button buttonUpdateGol;
    private Button buttonDeleteGol;

    private String id_golongan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_golongan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Golongan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                =new ColorDrawable(Color.parseColor("#E3B2A6"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intent = getIntent();

        id_golongan = intent.getStringExtra(konfigurasi.GOL_ID);

        editTextIdGol = (EditText) findViewById(R.id.editTextIdGol);
        editTextNamaGol = (EditText) findViewById(R.id.editTextNamaGol);
        editTextGapok = (EditText) findViewById(R.id.editTextGapok);
        editTextTunMakan = (EditText) findViewById(R.id.editTextTunMakan);
        editTextTunKeluarga = (EditText) findViewById(R.id.editTextTunKeluarga);
        editTextTunJabatan = (EditText) findViewById(R.id.editTextTunJabatan);

        buttonUpdateGol = (Button) findViewById(R.id.buttonUpdateGol);
        buttonDeleteGol = (Button) findViewById(R.id.buttonDeleteGol);

        buttonUpdateGol.setOnClickListener(this);
        buttonDeleteGol.setOnClickListener(this);

        editTextIdGol.setText(id_golongan);

        getGolongan();

        Button btnmainmenu = (Button) findViewById(R.id.buttonMM);

        btnmainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tampilGolongan.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getGolongan(){
        class GetGolongan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tampilGolongan.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showGolongan(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_GOL,id_golongan);
                return s;
            }
        }
        GetGolongan gg = new GetGolongan();
        gg.execute();
    }

    private void showGolongan (String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama_golongan = c.getString(konfigurasi.TAG_NAMA_GOLONGAN);
            String gajih_pokok = c.getString(konfigurasi.TAG_GAJIH_POKOK);
            String tunjangan_makan = c.getString(konfigurasi.TAG_TUNJANGAN_MAKAN);
            String tunjangan_keluarga = c.getString(konfigurasi.TAG_TUNJANGAN_KELUARGA);
            String tunjangan_jabatan = c.getString(konfigurasi.TAG_TUNJANGAN_JABATAN);

            editTextNamaGol.setText(nama_golongan);
            editTextGapok.setText(gajih_pokok);
            editTextTunMakan.setText(tunjangan_makan);
            editTextTunKeluarga.setText(tunjangan_keluarga);
            editTextTunJabatan.setText(tunjangan_jabatan);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateGolongan(){
        final String nama_golongan = editTextNamaGol.getText().toString().trim();
        final String gajih_pokok = editTextGapok.getText().toString().trim();
        final String tunjangan_makan = editTextTunMakan.getText().toString().trim();
        final String tunjangan_keluarga = editTextTunKeluarga.getText().toString().trim();
        final String tunjangan_jabatan = editTextTunJabatan.getText().toString().trim();

        class UpdateGolongan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilGolongan.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilGolongan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_ID_GOLONGAN,id_golongan);
                hashMap.put(konfigurasi.KEY_NAMA_GOLONGAN,nama_golongan);
                hashMap.put(konfigurasi.KEY_GAJIH_POKOK,gajih_pokok);
                hashMap.put(konfigurasi.KEY_TUNJANGAN_MAKAN,tunjangan_makan);
                hashMap.put(konfigurasi.KEY_TUNJANGAN_KELUARGA,tunjangan_keluarga);
                hashMap.put(konfigurasi.KEY_TUNJANGAN_JABATAN,tunjangan_jabatan);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_GOL,hashMap);
                return s;
            }
        }
        UpdateGolongan ug = new UpdateGolongan();
        ug.execute();
    }

    private void deleteGolongan(){
        class DeleteGolongan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilGolongan.this,"Updating...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilGolongan.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_GOL, id_golongan);
                return s;
            }
        }
        DeleteGolongan dg = new DeleteGolongan();
        dg.execute();
    }

    private void confirmDeleteGolongan(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Golongan ini?");

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deleteGolongan();
                startActivity(new Intent(tampilGolongan.this,tampilSemuaGol.class));
            }
        });

        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdateGol){
            updateGolongan();
        }

        if(v == buttonDeleteGol){
            confirmDeleteGolongan();
        }
    }

}