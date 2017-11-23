package th.co.thiensurat.ecatalog.catalog.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import babushkatext.BabushkaText;
import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.catalog.item.ProductItem;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private Context context;
    private ClickListener clickListener;
    private List<ProductItem> productItemList = new ArrayList<ProductItem>();
    public CatalogAdapter(FragmentActivity context) {
        this.context = context;
    }

    public void setProductItemList(List<ProductItem> productItemList) {
        this.productItemList = productItemList;
    }

    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatalogAdapter.ViewHolder holder, int position) {
        ProductItem item = productItemList.get(position);
        Glide.with(context)
                .load(item.getProductImage())
                .placeholder(R.drawable.no_image)
                .into(holder.productImage);
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(String.format("%,.0f",Float.parseFloat(item.getProductPrice()))+".-");

        holder.textPrice.addPiece(new BabushkaText.Piece.Builder(String.format("%,.0f",Float.parseFloat(item.getProductPrice()))+"")
                .textColor(Color.parseColor("#03A9F4"))
                .strike()
                .textSizeRelative(0.8f)
                .style(Typeface.NORMAL)
                .build());
        holder.textPrice.display();

        holder.textSellPrice.addPiece(new BabushkaText.Piece.Builder(String.format("%,.0f",Float.parseFloat(item.getProductSellPrice()))+"")
                .textColor(Color.parseColor("#DC143C"))
                .textSizeRelative(0.9f)
                .style(Typeface.BOLD)
                .build());
        holder.textSellPrice.display();


        if (item.getPromotionCode().isEmpty()) {
            holder.promotionView.setVisibility(View.GONE);
            holder.promotionPrice.setVisibility(View.GONE);
            holder.normalPrice.setVisibility(View.VISIBLE);
        } else {
            holder.promotionView.setVisibility(View.VISIBLE);
            holder.promotionPrice.setVisibility(View.VISIBLE);
            holder.normalPrice.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return productItemList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image) ImageView productImage;
        @BindView(R.id.product_name) TextView productName;
        @BindView(R.id.normal_price) LinearLayout normalPrice;
        @BindView(R.id.product_price) TextView productPrice;
        @BindView(R.id.promotion_price) LinearLayout promotionPrice;
        @BindView(R.id.text_price) BabushkaText textPrice;
        @BindView(R.id.text_sellprice) BabushkaText textSellPrice;
        @BindView(R.id.promotion_view) ImageView promotionView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener( onClickListener() );
        }

        private View.OnClickListener onClickListener() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemclicked(view, getPosition());
                    }
                }
            };
        }
    }

    public interface ClickListener {
        void onItemclicked(View view, int position);
    }
}
