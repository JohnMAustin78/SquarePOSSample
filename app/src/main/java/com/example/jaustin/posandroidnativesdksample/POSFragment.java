package com.example.jaustin.posandroidnativesdksample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link POSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link POSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class POSFragment extends Fragment {
    private static final String ARG_ITEMID = "item id";
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_CATALOG_VARIATION_ID = "Catalog_Item_Variation_ID";
    private String mItemId;
    private String mCatalogVariationID;
    private OnFragmentInteractionListener mListener;
    private Button mChargeButton;

    public POSFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1             Parameter 1.
     * @param catalogVariationID
     * @return A new instance of fragment POSFragment.
     */
    public static POSFragment newInstance(String param1, String catalogVariationID) {
        POSFragment fragment = new POSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEMID, param1);
        args.putString(ARG_CATALOG_VARIATION_ID, catalogVariationID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getString(ARG_ITEMID);
            mCatalogVariationID = getArguments().getString(ARG_CATALOG_VARIATION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_pos, container, false);
        mChargeButton = fragmentView.findViewById(R.id.addCharge_Button);
        mChargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(MainActivity.chargeActions.ADD_CHARGE);
            }
        });
        return fragmentView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(MainActivity.chargeActions.ADD_CHARGE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(MainActivity.chargeActions actionEnum);

    }
}
