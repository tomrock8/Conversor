package com.converter.agc.converter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/*
    Ampliacion:
        - Binario coma fija.
        - Operador logica. Suma binaria, Resta Binaria.
        - Mejoras visuales
 */
public class MainActivity extends AppCompatActivity {

    private Button btn0;
    private Button btn1;
    private Button btnDel;

    private Button sumar;
    private Button restar;
    private TextView datoBIN;
    private TextView resultadoDec;
    private TextView resultadoOct;
    private TextView resultadoHex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btnDel = (Button) findViewById(R.id.btnDel);

        sumar = (Button) findViewById(R.id.btnSuma);
        restar = (Button) findViewById(R.id.btnResta);

        datoBIN = (TextView) findViewById(R.id.editText1);
        resultadoDec = (TextView) findViewById(R.id.resultadoDecimal);
        resultadoOct = (TextView) findViewById(R.id.resultadoOctal);
        resultadoHex = (TextView) findViewById(R.id.resultadoHexadecimal);


        btn0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datoBIN.setText(datoBIN.getText().toString() + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datoBIN.setText(datoBIN.getText().toString() + "1");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String txt = datoBIN.getText().toString();
                if (txt.length() > 0)
                    datoBIN.setText(txt.substring(0, txt.length() - 1));
            }
        });


        sumar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });

        datoBIN.setSelected(true);
        datoBIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String decimal = convertirADecimal();
                if (decimal.contains("ERROR")) {
                    resultadoDec.setText("Decimal: " + decimal);
                    resultadoOct.setText("Octal: " + decimal);
                    resultadoHex.setText("Hexadecimal: " + decimal);
                } else {

                    String Octal = Integer.toOctalString(Integer.parseInt(convertirADecimal()));
                    String Hexadecial = Integer.toHexString(Integer.parseInt(convertirADecimal())).toUpperCase();
                    resultadoDec.setText("Decimal: " + decimal);
                    resultadoOct.setText("Octal: " + Octal);
                    resultadoHex.setText("Hexadecimal: " + Hexadecial);

                }
            }
        });


    }

    private String convertirADecimal() {
        int dato1 = 0;
        boolean error = false;
        // almacenamos en un string el dato introducido en editText1
        String datoleido = datoBIN.getText().toString();

        // Calculamos su longitud
        int longitud = datoleido.length();
        int potencia = longitud - 1;
        try {
            for (int i = 0; i < longitud; i++) {
                if (datoleido.charAt(i) == '1' && !error) {
                    dato1 += (int) Math.pow(2, potencia);
                } else {
                    if (datoleido.charAt(i) != '0') {
                        return "ERROR: El numero no es binario";
                    }
                }
                potencia--;
            }

        } catch (Exception e) {
            resultadoDec.setText("Error");
        }
        return "" + dato1;
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
}
