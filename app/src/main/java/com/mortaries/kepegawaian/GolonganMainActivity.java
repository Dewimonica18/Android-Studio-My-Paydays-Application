package com.mortaries.kepegawaian;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class GolonganMainActivity extends AppCompatActivity implements View.OnClickListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextNamaGol;
    private EditText editTextGapok;
    private EditText editTextTunMakan;
    private EditText editTextTunKeluarga;
    private EditText editTextTunJabatan;

    private Button buttonAddGol;
    private Button buttonViewGol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golongan_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Golongan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                =new ColorDrawable(Color.parseColor("#E3B2A6"));
        actionBar.setBackgroundDrawable(colorDrawable);


        editTextNamaGol = (EditText) findViewById(R.id.editTextNamaGol);
        editTextGapok = (EditText) findViewById(R.id.editTextGapok);
        editTextTunMakan= (EditText) findViewById(R.id.editTextTunMakan);
        editTextTunKeluarga= (EditText) findViewById(R.id.editTextTunKeluarga);
        editTextTunJabatan= (EditText) findViewById(R.id.editTextTunJabatan);

        buttonAddGol = (Button) findViewById(R.id.buttonAddGol);
        buttonViewGol = (Button) findViewById(R.id.buttonViewGol);

        //Setting listeners to button
        buttonAddGol.setOnClickListener(this);
        buttonViewGol.setOnClickListener(this);

        Button btnmainmenu = (Button) findViewById(R.id.buttonMM);

        btnmainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GolonganMainActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    //Dibawah ini merupakan perintah untuk Menambahkan Golongan (CREATE)
    private void addGolongan() {
        final String nama_golongan = editTextNamaGol.getText().toString().trim();
        final String gajih_pokok = editTextGapok.getText().toString().trim();
        final String tunjangan_makan = editTextTunMakan.getText().toString().trim();
        final String tunjangan_keluarga = editTextTunKeluarga.getText().toString().trim();
        final String tunjangan_jabatan = editTextTunJabatan.getText().toString().trim();

        class AddGolongan extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GolonganMainActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(GolonganMainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_NAMA_GOLONGAN, nama_golongan);
                params.put(konfigurasi.KEY_GAJIH_POKOK, gajih_pokok);
                params.put(konfigurasi.KEY_TUNJANGAN_MAKAN, tunjangan_makan);
                params.put(konfigurasi.KEY_TUNJANGAN_KELUARGA, tunjangan_keluarga);
                params.put(konfigurasi.KEY_TUNJANGAN_JABATAN, tunjangan_jabatan);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD_GOL, params);
                return res;
            }
        }
        AddGolongan ag= new AddGolongan();
        ag.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAddGol){
            addGolongan();
        }

        if (v == buttonViewGol){
            startActivity(new Intent(this, tampilSemuaGol.class));
        }
    }
}