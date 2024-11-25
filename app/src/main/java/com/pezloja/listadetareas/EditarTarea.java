package com.pezloja.listadetareas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditarTarea extends AppCompatActivity {

    EditText str_nombre, str_fechaEditar, str_horaEditar;
    TextView str_descripcion;
    RadioGroup rg_1, rg_2;
    RadioButton rb_ocio, rb_deporte, rb_trabajo, rb_estudios, rb_familia, rb_amigos;
    Button btn_guardar, btn_cancelar;
    ImageButton btn_fecha, btn_hora;

    int posTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);

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

        Intent recuperarTarea = getIntent();
        posTarea = recuperarTarea.getIntExtra("posTarea", 0);
        Tarea t = ControladorTarea.getTarea(posTarea);
        str_nombre.setText(t.getNomTarea());
        str_fechaEditar.setText(t.getFecha());
        str_horaEditar.setText(t.getHora());
        str_descripcion.setText(t.getDescripcion());

        if (t.getCategoria() == 0) {
            rb_ocio.setChecked(true);
        } else if (t.getCategoria() == 1) {
            rb_deporte.setChecked(true);
        } else if (t.getCategoria() == 2) {
            rb_trabajo.setChecked(true);
        } else if (t.getCategoria() == 3) {
            rb_estudios.setChecked(true);
        } else if (t.getCategoria() == 4) {
            rb_familia.setChecked(true);
        } else if (t.getCategoria() == 5) {
            rb_amigos.setChecked(true);
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
            guardarEditado(t);
            finish();
        });

        btn_cancelar.setOnClickListener(view -> finish());
    }

    public void guardarEditado(Tarea t) {
        t.setNomTarea(str_nombre.getText().toString());
        t.setFecha(str_fechaEditar.getText().toString());
        t.setHora(str_horaEditar.getText().toString());
        t.setDescripcion(str_descripcion.getText().toString());
        if (rb_ocio.isChecked()) {
            t.setCategoria(0);
        } else if (rb_deporte.isChecked()) {
            t.setCategoria(1);
        } else if (rb_trabajo.isChecked()) {
            t.setCategoria(2);
        } else if (rb_estudios.isChecked()) {
            t.setCategoria(3);
        } else if (rb_familia.isChecked()) {
            t.setCategoria(4);
        } else if (rb_amigos.isChecked()) {
            t.setCategoria(5);
        }
        ControladorTarea.editTarea(posTarea, t);
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