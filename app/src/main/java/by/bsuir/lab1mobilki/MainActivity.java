package by.bsuir.lab1mobilki;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import by.bsuir.lab1mobilki.model.User;

public class MainActivity extends AppCompatActivity {
    private long counter = 0;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_about_dev:
                Intent intent = new Intent(this,AboutDeveloperActivity.class);
                System.out.println(1);
                startActivity(intent);
                return true;
            case R.id.item_achievements:
                Intent intent2 =new Intent(this,AchievementActivity.class);
                startActivity(intent2);
                return true;

            default:return super.onOptionsItemSelected(item);
        }

    }
    private void animate() {
        if (counter % 15 == 0) {
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.cat_animation);
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
            frameAnimation.start();
            executorService.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frameAnimation.stop();
            });


        }
    }


    public void sendMessage(View view) {
        counter++;
        animate();
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(counter));

    }


    public void share(MenuItem item) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody=String.valueOf(counter);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myIntent,"Share Using"));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void save(View view){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, score INTEGER, created_at DATETIME)");
        db.execSQL("INSERT INTO users (name,score,created_at)\n" +
                "VALUES ('admin',?,?);",new Object[]{counter, ZonedDateTime.now()});
    }
}