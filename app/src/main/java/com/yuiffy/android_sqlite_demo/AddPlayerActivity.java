package com.yuiffy.android_sqlite_demo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class AddPlayerActivity extends AppCompatActivity {

    List<String> players;
    PlayerDbHelper mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        mDbHelper = new PlayerDbHelper(AddPlayerActivity.this);
        db = mDbHelper.getWritableDatabase();

        ListView lv = (ListView) findViewById(R.id.add_player_list_view);
        players = mDbHelper.getAllPlayerName(db);
        final ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(AddPlayerActivity.this, android.R.layout.simple_expandable_list_item_1, players);
        lv.setAdapter(theAdapter);

        findViewById(R.id.btn_add_player).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameTV = (TextView) findViewById(R.id.edit_add_player_name);
                String playerName = nameTV.getText().toString();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(MyContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME, playerName);
                values.put(MyContract.PlayerEntry.COLUMN_NAME_SEX, "Male");
                values.put(MyContract.PlayerEntry.COLUMN_NAME_AGE, "22");

                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(MyContract.PlayerEntry.TABLE_NAME, null, values);

                players.add(0, playerName);
                theAdapter.notifyDataSetChanged();
                System.out.println(players);
            }
        });
    }
}
