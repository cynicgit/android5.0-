package ip.cynic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ip.cynic.bean.ZhuangBi;
import ip.cynic.rxjavademo.R;

/**
 * Created by cynic on 2016/5/29.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {


    private Context mContext;
    private List<ZhuangBi> mLists;

    public GridAdapter(Context context, List<ZhuangBi> lists) {

        mContext = context;
        mLists = lists;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);

        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {
        ZhuangBi zhuangBi = mLists.get(position);
        holder.refreshUI(zhuangBi);
    }

    @Override
    public int getItemCount() {
        if (mLists != null) {
            return mLists.size();
        }
        return 0;
    }

    public void setZhuangBis(List<ZhuangBi> lists) {
        mLists = lists;
        notifyDataSetChanged();
    }

    public class GridHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTvDesc;

        public GridHolder(View itemView) {
            super(itemView);
            mTvDesc = (TextView) itemView.findViewById(R.id.grid_des);
            mImageView = (ImageView) itemView.findViewById(R.id.grid_img);
        }


        public void refreshUI(ZhuangBi zhuangBi) {
            mTvDesc.setText(zhuangBi.description);
            Glide.with(mContext).load(zhuangBi.image_url).into(mImageView);
        }

    }


}
