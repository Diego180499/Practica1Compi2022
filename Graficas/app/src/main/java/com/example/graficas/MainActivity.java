package com.example.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graficas.codigo.GraficaBarra;
import com.example.graficas.codigo.GraficaPie;
import com.example.graficas.codigo.Lexer;
import com.example.graficas.codigo.LexerCup;
import com.example.graficas.codigo.Sintax;
import com.example.graficas.codigo.Tokens;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Array;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private TextView etiquetaResultado;


    private int[] colores = {Color.BLUE};
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        texto = (EditText) findViewById(R.id.texto);
        etiquetaResultado = (TextView) findViewById(R.id.labelResultado);
        layout = (LinearLayout) findViewById(R.id.layout1);

    }

    private Chart getChart(Chart chart, String description, int textColor, int backgroundColor, int animateY, String[] campos) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(backgroundColor);
        chart.animateY(animateY);
        legend(chart,campos);
        return chart;
    }

    private void legend(Chart chart, String[] campos) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < campos.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = Color.GREEN;
            entry.label = campos[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry> getBarEntries(int [] valores) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < valores.length; i++) {
            entries.add(new BarEntry(i, valores[i]));
        }
        return entries;
    }

    private void ejeX(XAxis x, String[] campos) {
        x.setGranularityEnabled(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setValueFormatter(new IndexAxisValueFormatter(campos));
    }

    private void ejeY(YAxis y) {
        y.setSpaceTop(30); //esto es para agregarle mas espacio en la parte de arriba de la gráfica
        y.setAxisMinimum(0);
    }

    private void axisRigth(YAxis y) {
        y.setEnabled(false);
    }



    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(colores);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(int [] valores) {
        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(valores), ""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.25f);

        return barData;
    }

    /*
    * AQUI VA TODA LA LOGICA DE LA GRAFICA DE PIE
    * */
    private ArrayList<PieEntry> getPieEntries(int [] valores ){
        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < valores.length; i++) {
            entries.add(new PieEntry(valores[i]));
        }
        return entries;
    }

    private PieData getPieData(int valores[]){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(valores),""));
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }

    public void crearGraficaPie(PieChart pieChart,GraficaPie graficaPie){

        String [] datos = graficaPie.getEtiquetas();
        Double [] valoresDouble = graficaPie.getValores();
        int [] valores = new int[valoresDouble.length];

        for(int i=0; i< valores.length; i++){
            double d = valoresDouble[i];
           int numero = (int)d;
           valores[i] = numero;
        }

        int tamanio = graficaPie.getUniones().size();
        String [] datosEtiquetas = new String[tamanio];
        int [] datosCantidad = new int [tamanio];

        for(int i=0; i<tamanio; i++){
            double x = graficaPie.getUniones().get(i)[0];
            double y = graficaPie.getUniones().get(i)[1];

            int posicion1 = (int)x;
            int posicion2 = (int)y;

            datosEtiquetas[i] = datos[posicion1];
            datosCantidad[i] = valores[posicion2];
        }




        pieChart=(PieChart)getChart(pieChart,graficaPie.getTitulo(),Color.GRAY,Color.MAGENTA,3000,datosEtiquetas);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData(datosCantidad));
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);

        layout.addView(pieChart);
        pieChart.getLayoutParams().height = 600;
        pieChart.getLayoutParams().width = 700;

    }

    //ACÁ TERMINA LA LOGICA DE LA GRAFICA DE PIE

    /*
    * Creamos una gráfica de barras
    * */
    public void crearGrafica(BarChart barra, GraficaBarra graficaBarra) {

        String tituloGrafica =graficaBarra.getTitulo();
        String[] ejex = graficaBarra.getEjeX();
        Double[] valoresDouble = graficaBarra.getEjeY();
        int tamanio = graficaBarra.getUniones().size();


        int [] valores = new int[valoresDouble.length];
        for(int i=0; i<valores.length; i++){
            double d = valoresDouble[i];
            int numero = (int)d;
            valores[i] = numero;
        }

        String [] datosX = new String[tamanio];
        int [] datosY = new int[tamanio];

        for(int i=0; i<tamanio; i++){
            double  x = graficaBarra.getUniones().get(i)[0];
            double y = graficaBarra.getUniones().get(i)[1];

            int posicionX = (int)x;
            int posicionY = (int)y;

            datosX[i] = ejex[posicionX];
            datosY[i] = valores[posicionY];
        }



        barra = (BarChart) getChart(barra, tituloGrafica, Color.RED, Color.CYAN, 3000,datosX);
        barra.setDrawGridBackground(true);
        barra.setData(getBarData(datosY));
        ejeX(barra.getXAxis(),datosX);
        ejeY(barra.getAxisLeft());
        axisRigth(barra.getAxisRight());

        layout.addView(barra);
        barra.getLayoutParams().height = 600;
        barra.getLayoutParams().width = 700;

    }

    public void analizar(View view) {
        String resultado;
        if(LexerCup.getErroresLexicos().size() != 0){
            resultado = "Hay Errores Léxicos";
            etiquetaResultado.setText(resultado);
            return;
        }
        String st = String.valueOf(texto.getText());
        Sintax s = new Sintax(new LexerCup(new StringReader(st)));

        try {
            s.parse();
            resultado = "¡Análisis exitoso!";
            String [] datosPie = {"dato1","dato2"};
            int [] valoresPie = {40,20};
            etiquetaResultado.setText(resultado);
            mostrarGraficas();
            //crearGrafica();
        } catch (Exception e) {
            Symbol simbolo = s.getS();
            resultado = "Hay errores Sintácticos";
            etiquetaResultado.setText(resultado);
        }
    }

    public GraficaBarra obtenerGraficaBarra(String nombre){
        GraficaBarra grafica;
        ArrayList<GraficaBarra> graficas = Sintax.getGraficaBarras();
        for(int i=0; i<graficas.size(); i++){
            if(graficas.get(i).getTitulo().equals(nombre)){
                grafica = graficas.get(i);
                return grafica;
            }
        }
        return null;
    }

    public GraficaPie obtenerGraficaPie(String nombre){
        GraficaPie graficaPie;
        ArrayList<GraficaPie> graficas = Sintax.getGraficasPie();

        for(int i=0; i<graficas.size(); i++){
            if(graficas.get(i).getTitulo().equals(nombre)){
                graficaPie = graficas.get(i);
                return graficaPie;
            }
        }
        return null;
    }

    public void mostrarGraficas(){
        ArrayList<GraficaBarra> graficasBarra = new ArrayList<>();
        ArrayList<GraficaPie> graficasPie = new ArrayList<>();


        for(int i=0; i<Sintax.getGraficasEjecutadas().length; i++){
            String tituloEjecutada = Sintax.getGraficasEjecutadas()[i];
            GraficaBarra grafica = obtenerGraficaBarra(tituloEjecutada);
            if(grafica == null){
                continue;
            }
            graficasBarra.add(grafica);
        }

        for(int i=0; i<graficasBarra.size(); i++){
            BarChart barra = new BarChart(this);
            crearGrafica(barra,graficasBarra.get(i));
        }

        //obtenemos las graficas de pie que se ejecutaron...
        for(int i=0; i<Sintax.getGraficasEjecutadas().length; i++){
            String tituloEjecutada = Sintax.getGraficasEjecutadas()[i];
            GraficaPie grafica = obtenerGraficaPie(tituloEjecutada);
            if(grafica == null){
                continue;
            }
            graficasPie.add(grafica);
        }

        for(int i=0; i<graficasPie.size(); i++){
            PieChart pieChart = new PieChart(this);
            crearGraficaPie(pieChart,graficasPie.get(i));
        }

    }

    public void irReportes(View view){
        Intent intent = new Intent(this,ReportesActivity.class);
        startActivity(intent);
    }
}