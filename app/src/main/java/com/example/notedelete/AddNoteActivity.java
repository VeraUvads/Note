package com.example.notedelete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTexttitle;
    private EditText editTextdescription;
    private Spinner spinnerdaysOfWeek;
    private RadioGroup radioGroudPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTexttitle = findViewById(R.id.editTextTitle);
        editTextdescription = findViewById(R.id.editTextDescription);
        spinnerdaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        radioGroudPriority= findViewById(R.id.radioGroupPriority);
    }

    public void onClickSaveNote(View view) {
        String title = editTexttitle.getText().toString();
        String description = editTextdescription.getText().toString();
        String data = spinnerdaysOfWeek.getSelectedItem().toString();
        int radioButtonId = radioGroudPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        Note note = new Note(title, description, priority, data);
        MainActivity.notesList.add(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
