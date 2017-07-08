package presenter;

import contract.CommentView;
import model.Comments;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by lenovo on 2016/10/15.
 */

public class CommentPresenterImpl implements Presenter {

    private CommentView commentView;

    public CommentPresenterImpl(CommentView commentView) {

        this.commentView = commentView;
        commentView.setPresenter(this);
    }

    @Override
    public void start() {

        handlerLongComments();

    }

    private void handlerLongComments() {

        HttpUtil.getInfoFromHttp(Constant.COMMENTS
                + commentView.getNewsId()
                + Constant.LONG_COMMENTS, new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof Comments) {
                    commentView.setLongComments(((Comments) o).getComments());
                    handlerShortComments();
                }
            }
        }, Comments.class);
    }

    private void handlerShortComments() {

        HttpUtil.getInfoFromHttp(Constant.COMMENTS
                        + commentView.getNewsId() 
                + Constant.SHORT_COMMENTS
                , new HttpHandler() {
                    @Override
                    public void onFinish(Object o) {

                        if(o instanceof Comments) {
                            commentView.setShortComments(((
                                    Comments) o).getComments());
                        }
                    }
                }, Comments.class);
    }
}
