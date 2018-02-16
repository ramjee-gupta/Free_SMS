package com.ram.freesms;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by RAMJEE on 07-02-2018.
 */

public class ContactsAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;
    //String contactName;
    Context context;

    ContactsAdapter(Context context){
         list = new ArrayList<SingleRow>();
        this.context = context;

        String sorting = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
        Cursor c = context.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, null, null, sorting);

        while (c.moveToNext()) {



             String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String  phonenumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(new SingleRow(contactName,phonenumber));


        }//end loop
        c.close();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View row = inflater.inflate(R.layout.single_contact_layout,viewGroup,false);
        TextView name = row.findViewById(R.id.name);
        TextView number = row.findViewById(R.id.number);

        SingleRow temp = list.get(i);

        name.setText(temp.name);
        number.setText(temp.contacts);


        return row;
    }
}

class SingleRow{

    String name,contacts;

    public SingleRow(String name, String contacts) {
        this.name = name;
        this.contacts = contacts;
    }
}
