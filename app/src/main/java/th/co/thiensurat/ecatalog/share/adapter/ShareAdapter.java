package th.co.thiensurat.ecatalog.share.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.catalog.adapter.CatalogAdapter;
import th.co.thiensurat.ecatalog.share.item.BannerItem;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ViewHolder> {

    private Context context;
    private ClickListener clickListener;
    private List<BannerItem> bannerItems = new ArrayList<BannerItem>();

    public ShareAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    public void setBannerItems(List<BannerItem> bannerItems) {
        this.bannerItems = bannerItems;
    }

    @Override
    public ShareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_share, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShareAdapter.ViewHolder holder, int position) {
        BannerItem item = bannerItems.get(position);
        holder.bannerHeader.setText(item.getBannerHeader());
        holder.bannerDetail.setText(item.getBannerDetail());
        Glide.with(context)
                .load("http://app.thiensurat.co.th/TSROnlineMarketing/" + item.getBannerImage())
                .placeholder(R.drawable.no_image)
                .into(holder.bannerImage);
    }

    @Override
    public int getItemCount() {
        return bannerItems.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner_header) TextView bannerHeader;
        @BindView(R.id.banner_image) ImageView bannerImage;
        @BindView(R.id.banner_detail) TextView bannerDetail;
        @BindView(R.id.share_facebook) ImageView shareFacebook;
        @BindView(R.id.share_google_plus) ImageView shareGoogle;
        @BindView(R.id.share_twitter) ImageView shareTwitter;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            shareFacebook.setOnClickListener( onFacebook() );
            shareGoogle.setOnClickListener( onGoogle() );
            shareTwitter.setOnClickListener( onTwitter() );
        }

        private View.OnClickListener onFacebook() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.shareFacebook(view, getPosition());
                    }
                }
            };
        }

        private View.OnClickListener onGoogle() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.shareGoogle(view, getPosition());
                    }
                }
            };
        }

        private View.OnClickListener onTwitter() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.shareTwitter(view, getPosition());
                    }
                }
            };
        }
    }

    public interface ClickListener {
        void shareFacebook(View view, int position);
        void shareGoogle(View view, int position);
        void shareTwitter(View view, int position);
    }
}
