package ip.cynic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ip.cynic.bean.BeautyGirl;
import ip.cynic.rxjavademo.R;

/**
 * Created by cynic on 2016/5/30.
 */
public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapHolder>{

    private List<BeautyGirl> mBeautyGirls;

    private List<Integer> heights;

    public void setBeautyGirls(List<BeautyGirl> beautyGirls) {
        mBeautyGirls = beautyGirls;
        getRandomHeight(beautyGirls);
        notifyDataSetChanged();
    }

    private void getRandomHeight(List lists){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int)(200+Math.random()*400));
        }
    }

    @Override
    public MapHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_map, parent, false);
        return new MapHolder(view);
    }

    @Override
    public void onBindViewHolder(MapHolder holder, int position) {

        BeautyGirl beautyGirl = mBeautyGirls.get(position);
        holder.randRomImgHegiht(position);
        holder.refreshUI(beautyGirl);

    }

    @Override
    public int getItemCount() {

        if(mBeautyGirls!=null) {
            return mBeautyGirls.size();
        }

        return 0;
    }

    public class MapHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTvDesc;

        public MapHolder(View itemView) {
            super(itemView);
            mTvDesc = (TextView) itemView.findViewById(R.id.grid_des);
            mImageView = (ImageView) itemView.findViewById(R.id.grid_img);
        }

        public void refreshUI(BeautyGirl beautyGirl) {
            mTvDesc.setText(beautyGirl.description);
            Glide.with(itemView.getContext()).load(beautyGirl.image_url).into(mImageView);
        }

        public void randRomImgHegiht(int position) {
            ViewGroup.LayoutParams params = mImageView.getLayoutParams();//得到item的LayoutParams布局参数

            params.height = heights.get(position);//把随机的高度赋予itemView布局

            mImageView.setLayoutParams(params);//把params设置给itemView布局
        }

    }

}
