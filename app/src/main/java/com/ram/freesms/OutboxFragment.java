package com.ram.freesms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutboxFragment extends Fragment {


    ListView listView;


    public OutboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_outbox, container, false);

        SingleSMSAdapter adapter = new SingleSMSAdapter(getActivity());


        listView = view.findViewById(R.id.message_list_id);
        listView.setAdapter(adapter);
        return view;
    }

}
