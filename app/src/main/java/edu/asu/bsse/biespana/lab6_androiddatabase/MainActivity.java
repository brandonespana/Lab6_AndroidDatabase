package edu.asu.bsse.biespana.lab6_androiddatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String temp = "";
        WaypointDB waypointDB = new WaypointDB(this);
        try{
            waypointDB.copyDB();
            SQLiteDatabase accessDB = waypointDB.openDB();
            Cursor cursor = accessDB.rawQuery("SELECT * FROM waypoint",null);
            temp = "";
            while(cursor.moveToNext()){
                String waypointName = cursor.getString(0);
                temp += "\n Waypoint: "+waypointName;
            }

        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        TextView view = (TextView) findViewById(R.id.theView);
        view.setText(temp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
