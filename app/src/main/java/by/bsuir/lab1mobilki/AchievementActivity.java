package by.bsuir.lab1mobilki;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import by.bsuir.lab1mobilki.model.User;

public class AchievementActivity extends AppCompatActivity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achive);
        tableLayout = findViewById(R.id.achievementTable);
        findAllUsers();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Achievements");

    }



    public List<User> findAllUsers() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, score INTEGER, created_at DATETIME)");

        @SuppressLint("Recycle")
        Cursor query = db.rawQuery("SELECT * FROM users limit 5;", null);
        if (query.getCount() > 0) {
            query.moveToFirst();
            do {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                TextView textView = new TextView(this);
                textView.setText(query.getString(0));
                TextView textView1 = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView1.setText("Score: " + query.getString(1));
                TextView textView2 = new TextView(this);
                textView2.setText(query.getString(2));
                if (tableRow != null) {
                    tableRow.addView(textView, 0);
                    tableRow.addView(textView1, 1);
                    tableRow.addView(textView2, 2);
                    tableLayout.addView(tableRow, 0);
                }
            } while (query.moveToNext());
            query.close();
        }


        return null;
    }
}