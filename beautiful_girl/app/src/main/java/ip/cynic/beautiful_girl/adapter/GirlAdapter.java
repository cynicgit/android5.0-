package ip.cynic.beautiful_girl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.bean.GankGirl;

/**
 * Created by cynic on 2016/6/2.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlHolder>{

    private List<GankGirl> mGankGirls;

    private List<Integer> mHeights;


    public GirlAdapter() {
        setHasStableIds(true);
    }

    //需要设置Id
    @Override
    public long getItemId(int position) {
        return mGankGirls.get(position).id;
    }

    public void setBeautyGirls(List<GankGirl> beautyGirls,int currentPosition) {
        mGankGirls = beautyGirls;
        getRandomHeight(beautyGirls);
        if(currentPosition == 0){
            notifyDataSetChanged();
        } else {
            notifyItemRangeChanged(currentPosition, mGankGirls.size());
        }
    }




    private void getRandomHeight(List lists){//得到随机item的高度

        if(lists == null) {
            return;
        }

        mHeights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            mHeights.add((int)(200+Math.random()*400));
        }
    }

    @Override
    public GirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        return new GirlHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlHolder holder, int position) {
        holder.refreshDataUI(position);
    }

    @Override
    public int getItemCount() {

        if(mGankGirls!=null) {
            return mGankGirls.size();
        }

        return 0;
    }

    public class GirlHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;

        public GirlHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.grid_des);
            mImageView = (ImageView) itemView.findViewById(R.id.grid_img);
        }

        public void refreshDataUI(int position) {
            GankGirl gankGirl = mGankGirls.get(position);
            ViewGroup.LayoutParams params = mImageView.getLayoutParams();
            params.height = mHeights.get(position);
            mImageView.setLayoutParams(params);
            mTextView.setText(gankGirl.time);
            Glide.with(itemView.getContext()).load(gankGirl.image_url).into(mImageView);
        }

    }

}
