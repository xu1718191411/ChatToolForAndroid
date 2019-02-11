package xuzhongwei.ttchat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import xuzhongwei.ttchat.dummy.Chat;
import xuzhongwei.ttchat.dummy.Group;
import xuzhongwei.ttchat.view.ChatInputView;

public class ChatActivity extends AppCompatActivity implements ChatContentFragment.OnListFragmentInteractionListener{

    private ChatInputView mChatInputView;
    private Activity mActivity;
    private ChatContentFragment mChatContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        loadChat();
        initUI();
    }

    private void loadChat(){
        mChatContentFragment = new ChatContentFragment();
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 新しく追加を行うのでaddを使用します
        // 他にも、よく使う操作で、replace removeといったメソッドがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.chatContent, mChatContentFragment);
        // 最後にcommitを使用することで変更を反映します
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Chat.ChatItem item) {

    }


    private void initUI(){
        mChatInputView = (ChatInputView) findViewById(R.id.inputArea);
        mChatInputView.setmChatInputViewListener(new ChatInputView.ChatInputViewListener() {
            @Override
            public void onInputText(String text) {
                String s = text;

                if(mChatContentFragment != null){
                    mChatContentFragment.addNewChatContent(s);
                }

                hideKeyboard(mActivity);
            }
        });

        mActivity = this;
    }

    public static void hideKeyboard( Activity activity ) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService( Context.INPUT_METHOD_SERVICE );
        View f = activity.getCurrentFocus();
        if( null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom( f.getClass() ) )
            imm.hideSoftInputFromWindow( f.getWindowToken(), 0 );
        else
            activity.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
    }
}
