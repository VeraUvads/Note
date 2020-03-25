package com.example.notedelete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTexttitle;
    private EditText editTextdescription;
    private Spinner spinnerdaysOfWeek;
    private RadioGroup radioGroudPriority;
    private NotesDBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        dbHelper = new NotesDBHelper(this);
        database = dbHelper.getWritableDatabase();
        editTexttitle = findViewById(R.id.editTextTitle);
        editTextdescription = findViewById(R.id.editTextDescription);
        spinnerdaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        radioGroudPriority = findViewById(R.id.radioGroupPriority);
    }

    public void onClickSaveNote(View view) {
        String title = editTexttitle.getText().toString();
        String description = editTextdescription.getText().toString();
        int data = spinnerdaysOfWeek.getSelectedItemPosition();
        int radioButtonId = radioGroudPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        if (isField(title, description)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, title);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, description);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, data + 1);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority);
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.warming_empty_fields, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isField(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }

}

