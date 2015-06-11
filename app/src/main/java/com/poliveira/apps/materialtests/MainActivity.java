package com.poliveira.apps.materialtests;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {
    private TextView tCalenderView, textView_origincity,textview_destination ;
    static TextView textOriginDate;
    private Toolbar mToolbar;
    static String date;
    ImageView  navigator;

    String[] SrNo;
    private ArrayList<String> array_sort;
    int textlength=0;
    ArrayAdapter<String> dataAdapter = null;

    EditText autoCompleteOrigin, autoCompleteDest;
    ListView listOrigin, listDestination;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        textOriginDate = (TextView) findViewById(R.id.textView_origindate);
        textView_origincity = (TextView) findViewById(R.id.textView_origincity);
        listOrigin = (ListView) findViewById(R.id.listview);
        listDestination = (ListView) findViewById(R.id.listview_dest);
        autoCompleteDest = (EditText) findViewById(R.id.searchdest);
        autoCompleteOrigin = (EditText) findViewById(R.id.search);
        navigator=(ImageView)findViewById(R.id.imageView_navigation);
        textview_destination=(TextView) findViewById(R.id.textview_destination);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tCalenderView = (TextView) findViewById(R.id.textView_origindate);


        navigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        textview_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview_destination.setVisibility(View.GONE);
                autoCompleteDest.setVisibility(View.VISIBLE);
                listDestination.setVisibility(View.VISIBLE);
                displayListView_destination();
            }
        });
        textView_origincity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_origincity.setVisibility(View.GONE);
                autoCompleteOrigin.setVisibility(View.VISIBLE);
                listOrigin.setVisibility(View.VISIBLE);
                displayListView();

            }
        });






        tCalenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleCalendarDialogFragment d = new SimpleCalendarDialogFragment();

                new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "test-simple-calendar");
            }
        });


        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
      //  Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        // mNavigationDrawerFragment.closeDrawer();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    public static class SimpleCalendarDialogFragment extends DialogFragment implements OnDateChangedListener, View.OnClickListener {

        TextView textView, tCancel, tOk, textYeasr;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return inflater.inflate(R.layout.calender_view, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            textView = (TextView) view.findViewById(R.id.textView);
            tCancel = (TextView) view.findViewById(R.id.textView_cancel);
            tOk = (TextView) view.findViewById(R.id.textView_ok);
            textYeasr = (TextView) view.findViewById(R.id.textView_year);
            MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
            Calendar calendar = Calendar.getInstance();
          Date currentDate=  calendar.getTime();
            String CurDate= currentDate.toString();

            String arrayString[] = CurDate.split("\\s+");
            textYeasr.setText(arrayString[5]);
            textView.setText(arrayString[1]+","+arrayString[2]);
            widget.setBackgroundResource(R.drawable.rect_shadow);

            widget.setOnDateChangedListener(this);
            tCancel.setOnClickListener(this);
            tOk.setOnClickListener(this);
        }


        @Override
        public void onDateChanged(MaterialCalendarView widget, CalendarDay date) {

            String time = FORMATTER.format(date.getDate());

            String test = time;
            String str = time;

            String arrayString[] = str.split("\\s+");
            String dat = arrayString[2];
            textYeasr.setText(dat);


            textView.setText(arrayString[1] + " " + arrayString[0]);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.textView_ok) {
                date = textView.getText().toString();
                String year=textYeasr.getText().toString();
                System.out.println(date);
                this.getDialog().dismiss();
                textOriginDate.setText(date+ " "+ year);
            } else if (v.getId() == R.id.textView_cancel) {

                this.getDialog().dismiss();
            }
        }
    }
    private void displayListView( ) {

        //Array list of countries
        List<String> countryList = new ArrayList<String>();
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        countryList.add("Bahamas");
        countryList.add("Belize");
        countryList.add("Bermuda");
        countryList.add("Barbados");
        countryList.add("Canada");
        countryList.add("Costa Rica");
        countryList.add("Cuba");
        countryList.add("Cayman Islands");
        countryList.add("Dominica");
        countryList.add("Dominican Republic");
        countryList.add("Guadeloupe");
        countryList.add("Grenada");
        countryList.add("Greenland");
        countryList.add("Guatemala");
        countryList.add("Honduras");
        countryList.add("Haiti");
        countryList.add("Jamaica");

        //create an ArrayAdaptar from the String Array
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.listview_item,R.id.txtSrno, countryList);

        // Assign adapter to ListView
        listOrigin.setAdapter(dataAdapter);

        //enables filtering for the contents of the given ListView
        listOrigin.setTextFilterEnabled(true);

        listOrigin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                String data=(String)parent.getItemAtPosition(position);

                listOrigin.setVisibility(View.GONE);
                autoCompleteOrigin.setVisibility(View.GONE);
                textView_origincity.setVisibility(View.VISIBLE);
                textView_origincity.setText(data);
            }
        });


        autoCompleteOrigin.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });
    }
    private void displayListView_destination( ) {

        //Array list of countries
        List<String> countryList = new ArrayList<String>();
        countryList.add("Aruba");
        countryList.add("Anguilla");
        countryList.add("Netherlands Antilles");
        countryList.add("Antigua and Barbuda");
        countryList.add("Bahamas");
        countryList.add("Belize");
        countryList.add("Bermuda");
        countryList.add("Barbados");
        countryList.add("Canada");
        countryList.add("Costa Rica");
        countryList.add("Cuba");
        countryList.add("Cayman Islands");
        countryList.add("Dominica");
        countryList.add("Dominican Republic");
        countryList.add("Guadeloupe");
        countryList.add("Grenada");
        countryList.add("Greenland");
        countryList.add("Guatemala");
        countryList.add("Honduras");
        countryList.add("Haiti");
        countryList.add("Jamaica");

        //create an ArrayAdaptar from the String Array
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.listview_item,R.id.txtSrno, countryList);

        // Assign adapter to ListView
        listDestination.setAdapter(dataAdapter);

        //enables filtering for the contents of the given ListView
        listDestination.setTextFilterEnabled(true);

        listDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                String data=(String)parent.getItemAtPosition(position);

                listDestination.setVisibility(View.GONE);
                autoCompleteDest.setVisibility(View.GONE);
                textview_destination.setVisibility(View.VISIBLE);
                textview_destination.setText(data);
            }
        });


        autoCompleteDest.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });
    }
}

