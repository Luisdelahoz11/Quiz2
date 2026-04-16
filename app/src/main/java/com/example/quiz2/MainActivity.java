package com.example.quiz2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    public static String datacatCache="datacat";

    private static final int modo_private = Context.MODE_PRIVATE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText usuario,edad;
    Spinner Categoria;
    String[] opciones = {"Deportes", "Musica", "Cine"};

    AppCompatButton btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnGuardar = findViewById(R.id.btnGuardar);
        usuario= findViewById(R.id.usuario);
        edad = findViewById(R.id.edad);
        Categoria= findViewById(R.id.Categoria);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, opciones);
        Categoria.setAdapter(adapter);


        sharedPreferences = getSharedPreferences(datacatCache,modo_private);
        editor= sharedPreferences.edit();
        
        GuardarForm();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usuario.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Los campos estan vacios",Toast.LENGTH_LONG).show();

                }else {
                    editor.putString("user",usuario.getText().toString());
                    editor.putString("edad",edad.getText().toString());
                    editor.putString("categoria", Categoria.getSelectedItem().toString());
                    editor.commit();

                    String categoriaSelec= Categoria.getSelectedItem().toString();

                    if(categoriaSelec.equals("Deportes")){
                        Intent i= new Intent(MainActivity.this, DeportePantalla.class);
                        startActivity(i);

                    } else if (categoriaSelec.equals("Musica")) {

                        Intent i= new Intent(MainActivity.this, MusicaPantalla.class);
                        startActivity(i);
                    }else{
                        Intent i= new Intent(MainActivity.this, CinePantalla.class);
                        startActivity(i);
                    }

                    finish();

                }
            }
        });
    }



    private void GuardarForm() {



        String cat= this.getSharedPreferences(datacatCache,modo_private).getString("categoria","0");
        if ( !cat.equalsIgnoreCase("0")){

            if(cat.equals("Deportes")){
                Intent i= new Intent(MainActivity.this, DeportePantalla.class);
                startActivity(i);
            }else if(cat.equals("Música")){
                Intent i= new Intent(MainActivity.this, MusicaPantalla.class);
                startActivity(i);
            }else{
                Intent i= new Intent(MainActivity.this, CinePantalla.class);
                startActivity(i);
            }

            finish();


        }

    }

}
