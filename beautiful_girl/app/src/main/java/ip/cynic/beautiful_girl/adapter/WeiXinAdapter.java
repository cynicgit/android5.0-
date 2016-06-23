package ip.cynic.beautiful_girl.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.bean.WeiXinTopic;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by cynic on 2016/6/7.
 */
public class WeiXinAdapter extends RecyclerView.Adapter<WeiXinAdapter.WeiXinHolder> {

    private Fragment mFragment;

    private List<WeiXinTopic> mLists;

    public WeiXinAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    public void setLists(List<WeiXinTopic> lists) {
        mLists = lists;
        notifyDataSetChanged();
    }

    @Override
    public WeiXinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weixin, parent, false);
        return new WeiXinHolder(view);
    }

    @Override
    public void onBindViewHolder(WeiXinHolder holder, int position) {
        holder.refreshDataUI(position);
    }

    @Override
    public int getItemCount() {
        if (mLists != null) {
            return mLists.size();
        }
        return 0;
    }

    public class WeiXinHolder extends ViewHolder {

        TextView mTvTime;
        TextView mTvTitle;
        ImageView mImageView;

        public WeiXinHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_img);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        protected void refreshDataUI(int position) {
            WeiXinTopic weiXinTopic = mLists.get(position);
            mTvTime.setText(weiXinTopic.time);
            mTvTitle.setText(weiXinTopic.title);
            if (TextUtils.isEmpty(weiXinTopic.imgUrl)) {
                mImageView.setVisibility(View.GONE);
            } else {
                System.out.println(weiXinTopic.imgUrl);
                mImageView.setVisibility(View.VISIBLE);
                Glide.with(mFragment).load(weiXinTopic.imgUrl).into(mImageView);
            }
        }

    }

}
