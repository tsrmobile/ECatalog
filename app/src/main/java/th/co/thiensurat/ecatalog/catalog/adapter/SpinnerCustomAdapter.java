package th.co.thiensurat.ecatalog.catalog.adapter;

import android.content.Context;
import android.content.res.Resources;
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

public class SpinnerCustomAdapter extends ArrayAdapter<String> {

    private Context activity;
    private List data;
    private String header;
    public Resources res;
    LayoutInflater inflater;
    DataItem model;

    /*************  CustomAdapter Constructor *****************/
    public SpinnerCustomAdapter(Context activitySpinner, int textViewResourceId, List objects, Resources resLocal, String header) {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;
        this.header = header;
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
            switch (header) {
                case "province" :
                    textView.setText("กรุณาเลือกจังหวัด");
                    textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                    break;
                case "district" :
                    textView.setText("กรุณาเลือกเขต/อำเภอ");
                    textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                    break;
                case "subdistrict" :
                    textView.setText("กรุณาเลือกแขวง/ตำบล");
                    textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                    break;
                case "type" :
                    textView.setText("กรุณาเลือกประเภทของลูกค้า");
                    textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                    break;
                case "country" :
                    textView.setText("Please select your country");
                    textView.setTextColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                    break;
            }
        } else {
            textView.setText(model.getDataName());
            textView.setTextColor(activity.getResources().getColor(R.color.colorAccent));
        }
        return row;
    }
}
