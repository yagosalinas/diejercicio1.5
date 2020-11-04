package com.example.diejercicio1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    private ArrayList<String> listatareas= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            FloatingActionButton btAdd = (FloatingActionButton) this.findViewById( R.id.btAdd );
            listatareas = new ArrayList<String>();
            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setLongClickable( true );
            adapter = new RecyclerViewAdapter(this, listatareas);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);


            btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onAdd();
            }
        });
    }
    EditText tarea;
    EditText fecha;

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + (position+1), Toast.LENGTH_SHORT).show();
    }
    private void onAdd() {
        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View alertd=getLayoutInflater().inflate(R.layout.alert_dialog, null );


        builder.setView(alertd);
        tarea = alertd.findViewById(R.id.tarea);
        fecha = alertd.findViewById(R.id.fecha);
        fecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.fecha:
                        showDatePickerDialog(fecha);
                        break;
                }
            }
            public void showDatePickerDialog(EditText v) {
                // Para crear una instancia de nuestro datePicker
                // pasándole el EditText debemos usar el método
                // estático que definimos como "newInstance"
                // en lugar de usar el constructor por defecto
                DialogFragment newFragment = DatePickerFragment.newInstance(v);
                // Mostrar el datePicker
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }

        });
        fecha.setFocusable(false);

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String txtarea =tarea.getText().toString();
                String txfecha =fecha.getText().toString();
                listatareas.add(txtarea+", "+txfecha);
                adapter.notifyDataSetChanged();
    }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();

    }

}