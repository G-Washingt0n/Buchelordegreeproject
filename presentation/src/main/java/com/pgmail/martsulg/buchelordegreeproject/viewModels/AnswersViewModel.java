package com.pgmail.martsulg.buchelordegreeproject.viewModels;

//import io.reactivex.observers.DisposableObserver;
//import p.martsulg.data.models.AnswersFeed;
//import p.martsulg.data.models.AnswersParams;
//import p.martsulg.data.models.CommentFeed;
//import p.martsulg.data.models.ListAnswers;
//import p.martsulg.domain.AddAnswerUseCase;
//import p.martsulg.domain.GetAnswersListUseCase;

/**
 * Created by g_washingt0n on 07.02.2018.
 */

public class AnswersViewModel implements MyViewModel {
    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void getRequest() {

    }

    @Override
    public void delRequest() {

    }


    @Override
    public void addRequest() {

    }

//    private Activity activity;
//    public AnswersAdapter adapter;
//    public ObservableField<String> answer2send = new ObservableField<>();
//    public ObservableField<String> commentTitle = new ObservableField<>();
//    public ObservableField<String> commentAuth = new ObservableField<>();
//    public ObservableField<String> commentText = new ObservableField<>();
//    public ObservableField<String> commentCreated = new ObservableField<>();
//
//    private AddAnswerUseCase addUseCase = new AddAnswerUseCase();
//    private GetAnswersListUseCase listUseCase = new GetAnswersListUseCase();
//
//    private int currPage = 1;
//    private ArrayList<AnswersFeed> adapterList;
//    private CommentFeed comment;
//
//    public AnswersViewModel(Activity activity, CommentFeed comment) {
//        this.activity = activity;
//        this.comment = comment;
//        adapter = new AnswersAdapter(activity, comment);
//    }
//
//    @Override
//    public void init() {
//    }
//
//
//    @Override
//    public void resume() {
//        getRequest(currPage);
//        commentTitle.set(comment.getTitle());
//        commentAuth.set(comment.getUser().getName());
//        commentText.set(comment.getMessage());
//        commentCreated.set(comment.getCreated_at());
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//
//    @Override
//    public void release() {
//        listUseCase.dispose();
//    }
//
//    public void onSendClick() {
//        if (!answer2send.equals(null))
//            addRequest();
//    }
//
//    @Override
//    public void getRequest(int requiredPage) {
//        AnswersParams params = new AnswersParams();
//        params.setToken(EntryActivity.preferences.getString("Token", null));
//        params.setCommentId(comment.getComment_id());
//        params.setMessage(answer2send.get());
//        listUseCase.execute(params, new DisposableObserver<ListAnswers>() {
//
//            @Override
//            public void onNext(ListAnswers listAnswers) {
//                adapterList = listAnswers.getData();
//                adapter.dataChanged(adapterList, comment);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }
//
//    @Override
//    public void delRequest(int cummentID) {
//    }
//
//    @Override
//    public void addRequest() {
//        AnswersParams params = new AnswersParams();
//        params.setMessage(answer2send.get());
//        params.setToken(EntryActivity.preferences.getString("Token", null));
//        params.setCommentId(comment.getComment_id());
//
//        addUseCase.execute(params, new DisposableObserver<Object>() {
//            @Override
//            public void onNext(Object o) {
//                getRequest(currPage);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Toast.makeText(activity, "Only admin can add answers!",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onComplete() {
//                addUseCase.dispose();
//                answer2send.set(null);
//            }
//        });
//    }

}
