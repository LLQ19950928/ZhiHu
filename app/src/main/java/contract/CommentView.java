package contract;

import java.util.List;

import model.Comment;
import view.BaseView;

/**
 * Created by lenovo on 2016/10/15.
 */

public interface CommentView extends BaseView {

    int getNewsId();
    void setLongComments(List<Comment> longComments);
    void setShortComments(List<Comment> shortComments);
}
