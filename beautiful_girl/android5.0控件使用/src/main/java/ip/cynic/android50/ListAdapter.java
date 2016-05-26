package ip.cynic.android50;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public  class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {


        private Context context;
        private List<String> lists;

        public ListAdapter(Context context, List<String> lists) {
            this.context = context;
            this.lists = lists;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(context, R.layout.simple_list_item_1, null);

            return new ListHolder(view);
        }

        @Override
        public int getItemCount() {
            if (lists != null) {
                return lists.size();
            }
            return 0;
        }

        public interface ItemClickListener {
            void OnItemClick(View v, int position);
        }

        ItemClickListener mItemClickListener;

        public void setOnItemClickListener(ItemClickListener itemClickListener) {
            mItemClickListener = itemClickListener;
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {
            holder.refreshDataUI(lists.get(position));
            holder.itemView.setTag(position);
        }

        public class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView textView;

            public ListHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
                itemView.setOnClickListener(this);
            }

            public void refreshDataUI(String data) {
                textView.setText(data);
            }

            @Override
            public void onClick(View v) {
                Snackbar.make(v, textView.getText(), Snackbar.LENGTH_LONG).show();
                if (mItemClickListener != null) {
                    mItemClickListener.OnItemClick(v, (Integer) itemView.getTag());
                }

            }
        }
    }