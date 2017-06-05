package jp.techacademy.toshinori.suzuki.qa_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class LikeListActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private DatabaseReference mLikeRef;

    private ListView mListView;
    private ArrayList<Question> mQuestionArrayList;
    private QuestionsListAdapter mAdapter;

    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            int genre = Integer.parseInt(dataSnapshot.getKey());
            for (Object key : map.keySet()) {
                HashMap temp = (HashMap) map.get((String) key);
                boolean like = ((String) temp.get("favorite")).equals("true");

                if(like){
                    String title = (String) temp.get("title");
                    String body = (String) temp.get("body");
                    String name = (String) temp.get("name");
                    String uid = (String) temp.get("uid");
                    String imageString = (String) temp.get("image");
                    byte[] bytes;
                    if (imageString != null) {
                        bytes = Base64.decode(imageString, Base64.DEFAULT);
                    } else {
                        bytes = new byte[0];
                    }

                    ArrayList<Answer> answerArrayList = new ArrayList<Answer>();
                    HashMap answerMap = (HashMap) map.get("answers");
                    if (answerMap != null) {
                        for (Object anskey : answerMap.keySet()) {
                            HashMap anstemp = (HashMap) answerMap.get((String) anskey);
                            String answerBody = (String) anstemp.get("body");
                            String answerName = (String) anstemp.get("name");
                            String answerUid = (String) anstemp.get("uid");
                            Answer answer = new Answer(answerBody, answerName, answerUid, (String) key);
                            answerArrayList.add(answer);
                        }
                    }

                    Question question = new Question(title, body, name, uid, dataSnapshot.getKey(), genre, like, bytes, answerArrayList);
                    mQuestionArrayList.add(question);
                }
            }
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_list);

        setTitle("お気に入り");

        // Firebase
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mLikeRef = mDatabaseReference.child(Const.ContentsPATH);
        mLikeRef.addChildEventListener(mEventListener);

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.likeListView);
        mAdapter = new QuestionsListAdapter(this);
        mQuestionArrayList = new ArrayList<Question>();
        mAdapter.notifyDataSetChanged();

        mAdapter.setQuestionArrayList(mQuestionArrayList);
        mListView.setAdapter(mAdapter);
    }
}
