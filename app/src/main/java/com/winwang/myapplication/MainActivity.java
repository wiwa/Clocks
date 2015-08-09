package com.winwang.myapplication;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Vector;


public class MainActivity extends ActionBarActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEventsList();

        initCreateButton();

        Log.d(TAG, "Main Activity Created :3");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    public void initEventsList(){
        ListView list = (ListView) findViewById(R.id.EventsList);
        list.setClickable(true);

        final List<Event> eventsList= new Vector<Event>();
        eventsList.add(new Event());
        eventsList.add(new Event("Test1", "1234455"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));
        eventsList.add(new Event("Test2", "00000"));

        EventsAdapter adapter = new EventsAdapter(this, R.id.EventsList, eventsList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                System.out.println("sadsfsf");
                showToast(eventsList.get(position).getName() + ", " + String.valueOf(index));
            }
        });

        list.setAdapter(adapter);
    }

    public int pxToDp(int pix){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pix, getResources().getDisplayMetrics());
    }

    public void initCreateButton(){
        final Button btnCreateEvent, btnCreateConfirm, btnCreateCancel;
        final LinearLayout popupLayout;


        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
  //      btnCreateConfirm = (Button) popupView.findViewById(R.id.btnCreateConfirm);
  //      btnCreateCancel = (Button) popupView.findViewById(R.id.btnCreateCancel);


        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Creation Button!! :  btnCreateEvent");
                //popupCreateEvent.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                // Use the Builder class for convenient dialog construction

                LinearLayout dialogLayout = (LinearLayout) findViewById(R.id.linlayCreate);
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.creation_popup, dialogLayout, true);

                final EditText eventName = (EditText) dialogView.findViewById(R.id.etCreateEventName);
                final EditText eventDescription = (EditText) dialogView.findViewById(R.id.etCreateEventDescription);
//                final DatePicker eventDate = (DatePicker) dialogView.findViewById(R.id.CreateEventDatePicker);
//                final TimePicker eventTime = (TimePicker) dialogView.findViewById(R.id.CreateEventTimePicker);
                eventName.setText("Sample Name");


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("New Event")
                .setMessage("Name and Date are required fields.")
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Create Confirm, Name: " + eventName.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Create Cancel");
                        dialog.cancel();
                    }
                });
                //dialog.getWindow().setLayout(800, 1400);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = pxToDp(800);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialog.show();

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                /*
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                CreateEventDialogFragment frag = new CreateEventDialogFragment();
                frag.show(ft, "txn_tag");
                */

            }
        });

    }
}
