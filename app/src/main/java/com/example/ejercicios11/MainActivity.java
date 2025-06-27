package com.example.ejercicios11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
// import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button btnsuma;
    private Button btnresta;
    private Button btndivision;
    private Button btnmult;

    private EditText txtnum1;
    private EditText txtnum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnum1 = findViewById(R.id.txtnum1);
        txtnum2 = findViewById(R.id.txtnum2);


        btnsuma = findViewById(R.id.btnsuma);
        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("suma");
            }
        });


        btndivision = findViewById(R.id.btndivision);
        btndivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("division");
            }
        });

        btnmult = findViewById(R.id.btnmult);
        btnmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("multiplicacion");
            }
        });

        btnresta = findViewById(R.id.btnresta);
        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("resta");
            }
        });
    }
    public double Suma (double a, double b){ // Cambiado a double
        return a+b;
    }

    public double Resta (double a, double b){
        return a-b;
    }

    public double Multiplicacion (double a, double b){
        return a*b;
    }

    public double Division (double a, double b){
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero.");
        }
        return a / b;
    }

    private void realizarOperacion(String tipoOperacion) {
        String sNum1 = txtnum1.getText().toString();
        String sNum2 = txtnum2.getText().toString();


        if (sNum1.isEmpty() || sNum2.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos números.", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1, num2;
        try {
            num1 = Double.parseDouble(sNum1);
            num2 = Double.parseDouble(sNum2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos.", Toast.LENGTH_SHORT).show();
            return;
        }

        double resultado = 0;
        try {
            // 3. Realizar la operación según el tipo
            switch (tipoOperacion) {
                case "suma":
                    resultado = Suma(num1, num2);
                    break;
                case "resta":
                    resultado = Resta(num1, num2);
                    break;
                case "multiplicacion":
                    resultado = Multiplicacion(num1, num2);
                    break;
                case "division":
                    resultado = Division(num1, num2);
                    break;
            }


            Intent intent = new Intent(MainActivity.this, Resultado.class);

            intent.putExtra(Resultado.EXTRA_RESULTADO_OPERACION, resultado);
            intent.putExtra(Resultado.EXTRA_TIPO_OPERACION, tipoOperacion);

            startActivity(intent);

        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Ocurrió un error inesperado en la operación.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}