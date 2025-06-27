package com.example.ejercicios11;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Resultado extends AppCompatActivity {

    private TextView textViewResultado;
    private Button btnRegresar;
    public static final String EXTRA_RESULTADO_OPERACION = "resultado_operacion";
    public static final String EXTRA_TIPO_OPERACION = "tipo_operacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_main);

        textViewResultado = findViewById(R.id.textViewResultado);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Obtener los datos pasados desde MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double resultado = extras.getDouble(EXTRA_RESULTADO_OPERACION, 0.0);
            String tipoOperacion = extras.getString(EXTRA_TIPO_OPERACION, "Operación");


            textViewResultado.setText(String.valueOf(resultado));
            // Actualizar el título con el tipo de operación
            TextView textViewOperacionTitulo = findViewById(R.id.textViewOperacionTitulo);
            textViewOperacionTitulo.setText("El resultado de la " + tipoOperacion.toLowerCase() + " es:");

        } else {
            Toast.makeText(this, "No se recibió ningún resultado.", Toast.LENGTH_SHORT).show();
            textViewResultado.setText("Error");
        }


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
