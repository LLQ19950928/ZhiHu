package adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.llq.zhihu.R;

import java.util.List;

import model.Comment;
import util.GlideUtil;

/**
 * Created by lenovo on 2016/10/14.
 */

public class CommentAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<List<Comment>> commentList;
    private String[] countOfComments;

    public CommentAdapter(Context mContext,
                          List<List<Comment>> commentList,
                          String[] countOfComments) {

        this.mContext = mContext;
        this.commentList = commentList;
        this.countOfComments = countOfComments;
    }

    @Override
    public int getGroupCount() {

        return countOfComments.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (commentList.get(groupPosition) != null)
          return commentList.get(groupPosition).size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return countOfComments[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return commentList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        GroupViewHolder groupViewHolder = null;
        if(convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_group, parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.countOfComments = (TextView)convertView.findViewById(
                    R.id.count_of_long_comments);
            convertView.setTag(groupViewHolder);
        }
        groupViewHolder = (GroupViewHolder) convertView.getTag();
        groupViewHolder.countOfComments.setText(countOfComments[groupPosition]);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder = null;
        if(convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_child, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.countOfThumb = (TextView)convertView.findViewById(
                    R.id.count_of_thumb);
            childViewHolder.authorTextView = (TextView)convertView.findViewById(
                    R.id.author_textView);
            childViewHolder.contentTextView
                    = (TextView)convertView.findViewById(R.id.content_textView);
            childViewHolder.avatarImageView
                    = (ImageView)convertView.findViewById(R.id.avatar_imageView);
            convertView.setTag(childViewHolder);
        }
        childViewHolder = (ChildViewHolder) convertView.getTag();
        Comment comment = commentList.get(
                groupPosition).get(childPosition);

        GlideUtil.setCircleImage(mContext, comment.getAvatar(),
                childViewHolder.avatarImageView);
        childViewHolder.authorTextView.setText(comment.getAuthor());
        childViewHolder.contentTextView.setText(comment.getContent());
        childViewHolder.countOfThumb.setText(comment.getLikes() + "");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

    class GroupViewHolder {

         TextView countOfComments;

    }

    class ChildViewHolder {

         ImageView avatarImageView;
         TextView authorTextView;
         TextView contentTextView;
         TextView countOfThumb;
    }
}
