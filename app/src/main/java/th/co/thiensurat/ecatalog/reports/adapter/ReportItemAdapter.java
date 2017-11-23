package th.co.thiensurat.ecatalog.reports.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.utils.ChangeTintColor;

/**
 * Created by teerayut.k on 11/8/2017.
 */

public class ReportItemAdapter extends RecyclerView.Adapter<ReportItemAdapter.ViewHolder> {

    private Context context;
    private List<AllItem> allItemList = new ArrayList<AllItem>();
    private ChangeTintColor changeTintColor;

    public ReportItemAdapter(FragmentActivity activity) {
        this.context = activity;
        changeTintColor = new ChangeTintColor(context);
    }

    public void setReportItem(List<AllItem> allItemList) {
        this.allItemList = allItemList;
    }

    @Override
    public ReportItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_all_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportItemAdapter.ViewHolder holder, int position) {
        AllItem item = allItemList.get(position);
        SimpleDateFormat formatinput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatoutput = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = null;
            if (item.getOrderstatus().equals("01")) {
                holder.statusColor.setBackgroundColor(context.getResources().getColor(R.color.LimeGreen));
                holder.textViewStatusDetail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_black_18dp, 0, 0, 0);
                changeTintColor.setTextViewDrawableColor(holder.textViewStatusDetail, R.color.LimeGreen);
                date = formatinput.parse(item.getClosedate());
                holder.textViewStatusDate.setText(formatoutput.format(date));
            } else if (item.getOrderstatus().equals("90") || item.getOrderstatus().equals("91")) {
                holder.statusColor.setBackgroundColor(context.getResources().getColor(R.color.Chocolate));
                holder.textViewStatusDetail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cancel_black_18dp, 0, 0, 0);
                changeTintColor.setTextViewDrawableColor(holder.textViewStatusDetail, R.color.Chocolate);
                date = formatinput.parse(item.getCanceldate());
                holder.textViewStatusDate.setText(formatoutput.format(date));
            } else if (item.getOrderstatus().equals("00")) {
                holder.statusColor.setBackgroundColor(context.getResources().getColor(R.color.White));
                holder.textViewStatusDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                date = formatinput.parse(item.getCheckdate());
                holder.textViewStatusDate.setText(formatoutput.format(date));
            } else {
                holder.statusColor.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.textViewStatusDetail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_watch_later_black_18dp, 0, 0, 0);
                changeTintColor.setTextViewDrawableColor(holder.textViewStatusDetail, R.color.colorPrimary);
                date = formatinput.parse(item.getCheckdate());
                holder.textViewStatusDate.setText(formatoutput.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String fullname = item.getTitle() + "" + item.getFirstname() + " " + item.getLastname();

        holder.textViewNumber.setText((position + 1) + ".");
        holder.textViewOrderID.setText(item.getOrderid());
        holder.textViewProductName.setText(item.getProductname());
        holder.textViewFullname.setText(fullname);
        holder.textViewProvince.setText(item.getProvince());
        holder.textViewStatusDetail.setText(item.getStatusdetail());
        changeTintColor.setTextViewDrawableColor(holder.textViewStatusDate, R.color.Crimson);
    }

    @Override
    public int getItemCount() {
        return allItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.status_color) View statusColor;
        @BindView(R.id.number_list) TextView textViewNumber;
        @BindView(R.id.order_id) TextView textViewOrderID;
        @BindView(R.id.status_date) TextView textViewStatusDate;
        @BindView(R.id.product_name) TextView textViewProductName;
        @BindView(R.id.customer_fullname) TextView textViewFullname;
        @BindView(R.id.customer_province) TextView textViewProvince;
        @BindView(R.id.status_detail) TextView textViewStatusDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
