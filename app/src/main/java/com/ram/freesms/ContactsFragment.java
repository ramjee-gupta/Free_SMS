package com.ram.freesms;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {



    //private View view;

    ListView listView;
    String contactName;
    //Cursor cursor;

    public  static final int RequestPermissionCode  = 1 ;
    public ContactsFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        EnableRuntimePermission();


        listView = view.findViewById(R.id.contact_list_id);

        final ContactsAdapter ca = new ContactsAdapter(getActivity());
        listView.setAdapter(ca);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


               String number =((TextView)view.findViewById(R.id.number)).getText().toString();

               Intent smsActivity = new Intent(getActivity(),SmsActivity.class);

               smsActivity.putExtra("number",number);
               startActivity(smsActivity);
           }
       });

        return view;
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( //Method of Fragment
                    new String[]{Manifest.permission.READ_CONTACTS},
                    RequestPermissionCode
            );
        } else {

            //ActivityCompat.requestPermissions(getActivity(),new String[]{
            //        Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
             //new ContactsAdapter(getActivity());






        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestPermissionCode) {
            if (permissions[0].equals(Manifest.permission.READ_CONTACTS)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //new ContactsAdapter(getActivity());


            }
        }
    }




}
