package jp.techacademy.toshinori.suzuki.qa_app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tony on 2017/05/28.
 */

public class Question implements Serializable {
    private String mTitle;
    private String mBody;
    private String mName;
    private String mUid;
    private String mQuestionUid;

    public boolean isLike() {
        return mLike;
    }

    private boolean mLike;
    private int mGenre;
    private byte[] mBitmapArray;
    private ArrayList<Answer> mAnswerArrayList;

    public void setLike(boolean mLike) {
        this.mLike = mLike;
    }

    public Question(String mTitle, String mBody, String mName, String mUid, String mQuestionUid, int mGenre, boolean mLike, byte[] mBitmapArray, ArrayList<Answer> mAnswerArrayList) {
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mName = mName;
        this.mUid = mUid;
        this.mQuestionUid = mQuestionUid;
        this.mGenre = mGenre;
        this.mLike = mLike;
        this.mBitmapArray = mBitmapArray;
        this.mAnswerArrayList = mAnswerArrayList;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public String getName() {
        return mName;
    }

    public String getUid() {
        return mUid;
    }

    public String getQuestionUid() {
        return mQuestionUid;
    }

    public int getGenre() {
        return mGenre;
    }

    public byte[] getImageBytes() {
        return mBitmapArray;
    }

    public ArrayList<Answer> getAnswers() {
        return mAnswerArrayList;
    }
}

