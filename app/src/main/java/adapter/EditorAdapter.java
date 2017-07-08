package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.llq.zhihu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Editor;
import util.GlideUtil;

/**
 * Created by lenovo on 2016/10/14.
 */

public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.ItemViewHolder> {

    private Context mContext;
    private List<Editor> editors;

    public EditorAdapter(Context mContext, List<Editor> editors) {
        this.mContext = mContext;
        this.editors = editors;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_activity_editor, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        GlideUtil.setCircleImage(mContext,
                editors.get(position).getAvatar(), holder.editorImageView);
        holder.editor.setText(editors.get(position).getName());
        holder.job.setText(editors.get(position).getBio());
    }

    @Override
    public int getItemCount() {

        return editors.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.editor_imageView)
        ImageView editorImageView;
        @BindView(R.id.editor_text_view)
        TextView editor;
        @BindView(R.id.job_text_view)
        TextView job;

        public ItemViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
