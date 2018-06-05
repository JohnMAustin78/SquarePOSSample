package com.example.jaustin.posandroidnativesdksample;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.sdk.pos.ChargeRequest;
import com.squareup.sdk.pos.CurrencyCode;
import com.squareup.sdk.pos.PosClient;
import com.squareup.sdk.pos.PosSdk;

public class MainActivity extends AppCompatActivity implements POSFragment.OnFragmentInteractionListener {


    public static enum chargeActions {
        ADD_CHARGE,
        REVERSE_CHARGE;
    }

    private static final int CHARGE_REQUEST_CODE = 1;
    private PosClient mPosClient;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mPosClient = PosSdk.createClient(this, getString(R.string.applicationId_Square));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    @Override
    public void onFragmentInteraction(chargeActions actionEnum) {
        switch (actionEnum) {
            case ADD_CHARGE:
                startTransaction();
                break;
            case REVERSE_CHARGE:
                break;
            default:
                break;

        }
    }

    public void startTransaction() {
        try {
            ChargeRequest request = new ChargeRequest
                    .Builder(
                    100,
                    CurrencyCode.USD)
                    .build();
            Intent intent = mPosClient.createChargeIntent(request);
            startActivityForResult(intent, CHARGE_REQUEST_CODE);

        } catch (ActivityNotFoundException e) {
            ShowAlertFragment dialogFragment = new ShowAlertFragment();
            Dialog alertDialog = dialogFragment.getDialog(
                    this,
                    "Error",
                    "Square Point of Sale is not installed");
            alertDialog.show();


        } catch (Exception e) {
            ShowAlertFragment dialogFragment = new ShowAlertFragment();
            Dialog alertDialog = dialogFragment.getDialog(
                    this,
                    "Error on starting a transaction",
                    e.getLocalizedMessage());
            alertDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CHARGE_REQUEST_CODE || data == null) {
            ShowAlertFragment dialogFragment = new ShowAlertFragment();
            Dialog alertDialog = dialogFragment.getDialog(
                    this,
                    "Error: unknown",
                    "Square Point of Sale was uninstalled or stopped working");
            alertDialog.show();
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            ChargeRequest.Success success = mPosClient.parseChargeSuccess(data);
            ShowAlertFragment dialogFragment = new ShowAlertFragment();
            Dialog alertDialog = dialogFragment.getDialog(
                    this,
                    "Success",
                    "Client transaction ID: "
                            + success.clientTransactionId);
            alertDialog.show();

        } else {
            ChargeRequest.Error error = mPosClient.parseChargeError(data);
            ShowAlertFragment dialogFragment = new ShowAlertFragment();
            Dialog alertDialog = dialogFragment.getDialog(
                    this,
                    "Error" + error.code,
                    "Client transaction ID: "
                            + error.debugDescription);
            alertDialog.show();

        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return POSFragment.newInstance("Charge fragment", position + 1);
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


}