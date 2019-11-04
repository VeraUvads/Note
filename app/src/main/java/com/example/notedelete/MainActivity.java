package com.example.notedelete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Note> notesList = new ArrayList<>();
    private NotesAdapter adapter;
    private RecyclerView recyclerViewNotes;
    private NotesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerView);
        dbHelper = new NotesDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        if (notesList.isEmpty()) {
            notesList.add(new Note("Парикмахер", "Сделать прическу", 2, "Понедельник"));
            notesList.add(new Note("Баскетбол", "Игра со школьной командой", 1, "Вторник"));
            notesList.add(new Note("Магазин", "Купить новые джинсы", 3, "Понедельник"));
            notesList.add(new Note("Стоматолог", "Вылечить зубы", 1, "Понедельник"));
            notesList.add(new Note("Парикмахер", "Сделать прическу к выпускному", 3, "Среда"));
            notesList.add(new Note("Баскетбол", "Игра со школьной командой", 2, "Вторник"));
            notesList.add(new Note("Магазин", "Купить новые джинсы", 3, "Понедельник"));
        }

        for (Note note : notesList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, note.getTitle());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, note.getData());
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, note.getPriority());
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
        }

        ArrayList<Note> notesFromDB = new ArrayList<>();
        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            String day_of_week = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));
            Note note = new Note(title, description, priority, day_of_week);
            notesFromDB.add(note);
        }
        cursor.close();
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(notesFromDB);
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {

            @Override
            public void onNoteClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notesList.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes); //применить к ресайклер вью

    }

    public void AddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}
