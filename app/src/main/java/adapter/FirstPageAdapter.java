package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.llq.zhihu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Story;
import util.FormatUtil;
import util.GlideUtil;

/**
 * Created by Administrator on 2016/9/29.
 */

public class FirstPageAdapter extends
        RecyclerView.Adapter<FirstPageAdapter.FirstPageViewHolder> {

    private Context context;
    private List<Story> storyList = new ArrayList<>();
    private View view;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private OnClickedListener onClickListener;

    public FirstPageAdapter(Context context) {
        this.context = context;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
        notifyItemInserted(0);
    }

    public void addData(List<Story> stories) {

        storyList.addAll(stories);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickedListener onClickListener) {

        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        return position == TYPE_HEADER ? TYPE_HEADER : TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {

        int position = holder.getLayoutPosition();
        return view == null ? position : position - 1;
    }

    @Override
    public FirstPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER && view != null)
          return new FirstPageViewHolder(view);
        return new FirstPageViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item_firstpage, parent, false));
    }

    @Override
    public void onBindViewHolder(final FirstPageViewHolder holder, final int position) {

          if (position == TYPE_HEADER)
              return;
          else {

              final Story story = storyList.get(getRealPosition(holder));
              List<String> imageUrls
                      = story.getImages();
              holder.newsTextView.setText(story.getTitle());
              if (story.getDate() != null) {

                  if(holder.dateTextView.getVisibility() == View.GONE)
                      holder.dateTextView.setVisibility(View.VISIBLE);
                  holder.dateTextView.setText(
                          FormatUtil.formatDate(story.getDate()));
              }
              else
                 holder.dateTextView.setVisibility(View.GONE);
              holder.newsTextView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      onClickListener.onClicked(story.getId());
                  }
              });
              if (imageUrls != null)
               GlideUtil.setImageBackground(context,
                        imageUrls.get(0),
                       holder.newsImageView);

          }
    }

    @Override
    public int getItemCount() {

        return view == null ? storyList.size() : storyList.size() + 1;
    }

    class FirstPageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_textView)
        TextView newsTextView;
        @BindView(R.id.news_imageView)
        ImageView newsImageView;
        @BindView(R.id.date_textView)
        TextView dateTextView;

        public FirstPageViewHolder(View itemView) {
            super(itemView);

            if (itemView == view)
                return;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickedListener {

        void onClicked(int id);
    }
}
