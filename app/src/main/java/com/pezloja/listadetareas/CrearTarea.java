package com.pezloja.listadetareas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class CrearTarea extends AppCompatActivity {

    EditText str_nombre, str_fechaEditar, str_horaEditar, str_descripcion;
    RadioGroup rg_1, rg_2;
    RadioButton rb_ocio, rb_deporte, rb_trabajo, rb_estudios, rb_familia, rb_amigos;
    Button btn_guardar, btn_cancelar;
    ImageButton btn_fecha, btn_hora;
    int categoria;
    Tarea t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        str_nombre = findViewById(R.id.str_nombre);
        str_fechaEditar = findViewById(R.id.str_fechaEditar);
        str_horaEditar = findViewById(R.id.str_horaEditar);
        str_descripcion = findViewById(R.id.str_descripcion);
        rg_1 = findViewById(R.id.rg_1);
        rg_2 = findViewById(R.id.rg_2);
        rb_ocio = findViewById(R.id.rb_ocio);
        rb_deporte = findViewById(R.id.rb_deporte);
        rb_trabajo = findViewById(R.id.rb_trabajo);
        rb_estudios = findViewById(R.id.rb_estudios);
        rb_familia = findViewById(R.id.rb_familia);
        rb_amigos = findViewById(R.id.rb_amigos);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_fecha = findViewById(R.id.btn_fecha);
        btn_hora = findViewById(R.id.btn_hora);

        if (rb_ocio.isChecked()) {
            categoria = 0;
        } else if (rb_deporte.isChecked()) {
            categoria = 1;
        } else if (rb_trabajo.isChecked()) {
            categoria = 2;
        } else if (rb_estudios.isChecked()) {
            categoria = 3;
        } else if (rb_familia.isChecked()) {
            categoria = 4;
        } else if (rb_amigos.isChecked()) {
            categoria = 5;
        }

        rg_1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) { // Solo actúa si hay una selección
                rg_2.setOnCheckedChangeListener(null); // Desactiva listener en rg_2
                rg_2.clearCheck(); // Limpia selección de rg_2
                rg_2.setOnCheckedChangeListener((g, id) -> marcarNuevaCat(1)); // Reactiva listener
            }
        });

        rg_2.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) { // Solo actúa si hay una selección
                rg_1.setOnCheckedChangeListener(null); // Desactiva listener en rg_1
                rg_1.clearCheck(); // Limpia selección de rg_1
                rg_1.setOnCheckedChangeListener((g, id) -> marcarNuevaCat(2)); // Reactiva listener
            }
        });

        btn_fecha.setOnClickListener(view -> cambiarFecha());
        btn_hora.setOnClickListener(view -> cambiarHora());

        btn_guardar.setOnClickListener(view -> {
            t = new Tarea(str_nombre.getText().toString(), str_fechaEditar.getText().toString(), str_horaEditar.getText().toString(), str_descripcion.getText().toString(), categoria);
            ControladorTarea.addTarea(t);
            finish();
        });

        btn_cancelar.setOnClickListener(view -> finish());
    }

    private void marcarNuevaCat(int groupToClear) {
        if (groupToClear == 1) {
            rg_1.setOnCheckedChangeListener(null); // Desactiva listener
            rg_1.clearCheck();
            rg_1.setOnCheckedChangeListener((group, checkedId) -> marcarNuevaCat(2)); // Reactiva listener
        } else if (groupToClear == 2) {
            rg_2.setOnCheckedChangeListener(null); // Desactiva listener
            rg_2.clearCheck();
            rg_2.setOnCheckedChangeListener((group, checkedId) -> marcarNuevaCat(1)); // Reactiva listener
        }
    }

    public void cambiarFecha() {
        int actAno, actMes, actDia;
        if (str_fechaEditar.getText().toString().length() > 0) {
            String[] texto = str_fechaEditar.getText().toString().split("/");
            actAno = Integer.parseInt(texto[2]);
            actMes = Integer.parseInt(texto[1]);
            actDia = Integer.parseInt(texto[0]);
        } else {
            Calendar c = Calendar.getInstance();
            actAno = c.get(Calendar.YEAR);
            actMes = c.get(Calendar.MONTH);
            actDia = c.get(Calendar.DAY_OF_MONTH);
        }
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) ->
                str_fechaEditar.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year),
                actAno, actMes, actDia
        ).show();
    }

    public void cambiarHora() {
        int actHora, actMin;

        if (str_horaEditar.getText().toString().length() > 0) {
            String[] texto = str_horaEditar.getText().toString().split(":");
            actHora = Integer.parseInt(texto[0]);
            actMin = Integer.parseInt(texto[1]);
        } else {
            Calendar c = Calendar.getInstance();
            actHora = c.get(Calendar.HOUR_OF_DAY);
            actMin = c.get(Calendar.MINUTE);
        }

        new TimePickerDialog(this, (view, hourOfDay, minute) ->
                str_horaEditar.setText(hourOfDay + ":" + minute),
                actHora, actMin, false).show();
    }
}