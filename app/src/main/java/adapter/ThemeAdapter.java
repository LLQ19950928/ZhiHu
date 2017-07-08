package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.llq.zhihu.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Other;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ThemeAdapter extends
        RecyclerView.Adapter<ThemeAdapter.ItemViewHolder> {

    private List<Other> others;
    private Context context;


    public ThemeAdapter(List<Other> others, Context context) {
        this.others = others;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from(
                context).inflate(R.layout.adapter_theme, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

          holder.textView.setText(others.get(position).getName());
          holder.textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  EventBus.getDefault().post(others.get(position));

              }
          });
    }

    @Override
    public int getItemCount() {

        return others.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.item_text)
         TextView textView;

         public ItemViewHolder(View itemView) {
             super(itemView);

             ButterKnife.bind(this, itemView);
         }
     }

}
