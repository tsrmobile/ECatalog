package th.co.thiensurat.ecatalog.registration.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;


/**
 * Created by teera-s on 11/11/2016 AD.
 */

public class SpinnerTypeAdapter extends ArrayAdapter<String> {

    private Context activity;
    private List data;
    public Resources res;
    LayoutInflater inflater;
    DataItem model;

    /*************  CustomAdapter Constructor *****************/
    public SpinnerTypeAdapter(FragmentActivity activitySpinner, int textViewResourceId, List objects, Resources resLocal) {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;
        res      = resLocal;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_item, parent, false);

        /***** Get each Model object from Arraylist ********/
        model = null;
        model = (DataItem) data.get(position);

        TextView textView = (TextView) row.findViewById(R.id.row_item);

        if(position == 0) {
            textView.setText("กรุณาเลือกประเภทของผู้สมัคร");
            textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            textView.setText(model.getDataName());
            textView.setTextColor(activity.getResources().getColor(R.color.colorAccent));
        }
        return row;
    }
}
