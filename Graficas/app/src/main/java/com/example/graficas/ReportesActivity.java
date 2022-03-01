package com.example.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.graficas.codigo.GraficaBarra;
import com.example.graficas.codigo.GraficaPie;
import com.example.graficas.codigo.LexerCup;
import com.example.graficas.codigo.Sintax;

import java.util.ArrayList;

public class ReportesActivity extends AppCompatActivity {

    EditText textoErrores;
    TextView labelCantidadBarras;
    TextView labelCantidadPie;
    EditText textoGraficasDefinidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
        erroresLexicos();
        verCantidadGrafica();
        mostrarGraficasDefinidas();
    }


    public void regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    public void erroresLexicos(){
        if(!(LexerCup.getErroresLexicos().size() == 0)){
            String errores = "";
            for(int i=0; i< LexerCup.getErroresLexicos().size(); i++){
                errores += LexerCup.getErroresLexicos().get(i)+" Símbolo no reconocido\n";
            }

            textoErrores = (EditText) findViewById(R.id.textoErrores);
            textoErrores.setText(errores);
        }
    }

    public void verCantidadGrafica(){
        int cantidadBarras = Sintax.getCantidadBarras();
        int cantidadPie = Sintax.getCantidadPie();

        labelCantidadBarras = (TextView) findViewById(R.id.labelCantidadBarra);
        labelCantidadPie = (TextView) findViewById(R.id.labelCantidadPie);

        labelCantidadBarras.setText(""+cantidadBarras);
        labelCantidadPie.setText(""+cantidadPie);
    }


    public void mostrarGraficasDefinidas(){
        ArrayList<GraficaBarra> graficasBarra = Sintax.getGraficaBarras();
        ArrayList<GraficaPie> graficasPie = Sintax.getGraficasPie();

        String resultado = "*GRAFICAS DE BARRA*\n";
        for(int i=0; i<graficasBarra.size(); i++){
            resultado += graficasBarra.get(i).getTitulo()+"\n";
        }

        resultado += "*GRAFICAS DE PIE*\n";
        for(int i=0; i<graficasPie.size(); i++){
            resultado += graficasPie.get(i).getTitulo()+"";
        }

        textoGraficasDefinidas = (EditText) findViewById(R.id.textoGraficasDefinidas);
        textoGraficasDefinidas.setText(resultado);


    }


}