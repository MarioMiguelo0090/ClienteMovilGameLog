package com.example.gamelog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.databinding.ActivityReporteTendenciasBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ReporteTendencias extends AppCompatActivity {
    ActivityReporteTendenciasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReporteTendenciasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.rb_revival_retro){
                binding.txvRevivalRetro.setVisibility(View.VISIBLE);
                binding.txvJuegosMasReseniado.setVisibility(View.GONE);
            }else if (checkedId == R.id.rb_juego_mas_reseniado) {
                binding.txvRevivalRetro.setVisibility(View.GONE);
                binding.txvJuegosMasReseniado.setVisibility(View.VISIBLE);
            }
        });
        binding.txtFechaInicio.setOnClickListener(v->{
            mostrarDatePicker(binding.txtFechaInicio);
        });

        binding.txtFechaFin.setOnClickListener(v->{
            mostrarDatePicker(binding.txtFechaFin);
        });
        binding.btnMostrarGrafica.setOnClickListener(v->{
            verificarCampos();
        });
    }

    private void verificarCampos(){
        String fechaInicio = binding.txtFechaInicio.getText().toString();
        String fechaFin = binding.txtFechaFin.getText().toString();
        int radioButtonId = binding.radioGroup.getCheckedRadioButtonId();

        boolean fechaInicioValida = !fechaInicio.equals(getString(R.string.msj_seleccionar_fecha));
        boolean fechaFinValida = !fechaFin.equals(getString(R.string.msj_seleccionar_fecha));
        boolean radioSeleccionado = radioButtonId != -1;
        binding.txtFechaInicio.setError(null);
        binding.txtFechaFin.setError(null);
        if (fechaInicioValida && fechaFinValida && radioSeleccionado) {
            if (!esFechaInicioAnterior(fechaInicio, fechaFin)) {
                Toast.makeText(this, getString(R.string.msj_fechas_incorrectas), Toast.LENGTH_SHORT).show();
                return;
            }
            mostrarGrafica();
        } else {
            if (!fechaInicioValida) {
                binding.txtFechaInicio.setError("");
            }
            if (!fechaFinValida) {
                binding.txtFechaFin.setError("");
            }
            if (!radioSeleccionado) {
                Toast.makeText(this, getString(R.string.msj_seleccion_tendencia_error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean esFechaInicioAnterior(String inicio, String fin) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return sdf.parse(inicio).before(sdf.parse(fin));
        } catch (ParseException excepcion) {
            return false;
        }
    }

    private void mostrarDatePicker(TextView textView) {
        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    textView.setText(fecha);
                },
                anio, mes, dia
        );
        datePickerDialog.show();
    }


    private void mostrarGrafica(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 10f));
        entries.add(new BarEntry(1f, 20f));
        entries.add(new BarEntry(2f, 15f));
        entries.add(new BarEntry(3f, 30f));
        entries.add(new BarEntry(4f, 25f));

        BarDataSet dataSet = new BarDataSet(entries, "Tendencias");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData(dataSet);

        binding.barChart.setData(barData);
        binding.barChart.setFitBars(true);
        binding.barChart.invalidate();

        XAxis xAxis = binding.barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Fortnite", "Halo", "The last of us", "Age of empires", "God of war"}));
    }

}