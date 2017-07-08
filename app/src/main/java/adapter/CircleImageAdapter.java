package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.llq.zhihu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Editor;
import util.GlideUtil;

/**
 * Created by lenovo on 2016/10/9.
 */

public class CircleImageAdapter extends
        RecyclerView.Adapter<CircleImageAdapter.CircleImageViewHolder> {

    private Context context;
    private List<Editor> editors;

    public CircleImageAdapter(Context context, List<Editor> editors) {
        this.context = context;
        this.editors = editors;
    }

    @Override
    public CircleImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CircleImageViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item_theme_head, parent,false));
    }

    @Override
    public void onBindViewHolder(CircleImageViewHolder holder, int position) {

       GlideUtil.setCircleImage(context, editors.get(position).getAvatar(),
               holder.circleImage);
    }

    @Override
    public int getItemCount() {

        return editors.size();
    }

    class CircleImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.editor_imageView)
        ImageView circleImage;


        public CircleImageViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
