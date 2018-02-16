package com.ram.freesms;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RAMJEE on 09-02-2018.
 */

public class SingleSMSAdapter extends BaseAdapter {

    ArrayList<SingleSmsRow> mList;
    Context context;

    public SingleSMSAdapter(Context context){
         mList = new ArrayList<>();
         this.context = context;
      //  DatabaseAdapter helper = new DatabaseAdapter(context);

        DatabaseAdapter da = new DatabaseAdapter(context);

        mList =   da.getData(mList);


    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
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

        SingleSmsRow temp = mList.get(i);

        name.setText(temp.contact);
        number.setText(temp.message);


        return row;
    }
}

  class SingleSmsRow{

      String contact, message;

      public SingleSmsRow(String contact, String message) {
          this.message = message;
          this.contact = contact;
      }

}
