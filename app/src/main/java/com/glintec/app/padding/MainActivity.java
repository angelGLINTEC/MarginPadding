package com.glintec.app.padding;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSave, btnLoad;
    private EditText edName, edPhone;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edTxt_NAME);
        edPhone = findViewById(R.id.edTxt_NUMBER);
        btnLoad = findViewById(R.id.btn_RECUPERAR);
        btnSave = findViewById(R.id.btn_Guardar);
        View.OnClickListener clSave = new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                savecontact();
            }
        };

        View.OnClickListener clLoad = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               loadContact();
            }
        };

        btnSave.setOnClickListener(clSave);
        btnLoad.setOnClickListener(clLoad);
    }

    private void savecontact(){
        SharedPreferences preferencia = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String nombre = edName.getText().toString();
        String phone = edPhone.getText().toString();

        SharedPreferences.Editor editor = preferencia.edit();
        editor.putString(nombre,phone);
        editor.commit();
        Toast.makeText(this, "El contacto se ha guardado correcgamente", Toast.LENGTH_SHORT).show();
    }
    private void loadContact(){
        SharedPreferences preferencia = getSharedPreferences("agenda",Context.MODE_PRIVATE);
        String nombre = edName.getText().toString();
        String posiblePhone = preferencia.getString(nombre,"");
        if(posiblePhone.length()==0){
            Toast.makeText(this,"El contacto no existe",Toast.LENGTH_LONG).show();
            edPhone.setText("");
        }else{
            edPhone.setText(posiblePhone);

        }


    }
}
