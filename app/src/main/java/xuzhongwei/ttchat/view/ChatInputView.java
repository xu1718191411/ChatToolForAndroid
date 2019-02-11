package xuzhongwei.ttchat.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import xuzhongwei.ttchat.R;

public class ChatInputView extends ConstraintLayout {

    private EditText chat_input;
    private ChatInputViewListener mChatInputViewListener;

    public interface ChatInputViewListener{
        void onInputText(String text);
    }

    public void setmChatInputViewListener(ChatInputViewListener mChatInputViewListener) {
        this.mChatInputViewListener = mChatInputViewListener;
    }

    public ChatInputView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.chat_input_view,this,true);
        init();
    }

    public ChatInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.chat_input_view,this,true);
        init();
    }

    public ChatInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.chat_input_view,this,true);
        init();
    }

    private void init(){
        chat_input = (EditText) findViewById(R.id.chat_input);
        chat_input.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String content = chat_input.getText().toString();
                    if(content != null && content.trim().length() > 0){
                        if(mChatInputViewListener != null){
                            mChatInputViewListener.onInputText(content);
                            chat_input.setText("");
                        }
                    }
                    handled = true;
                }
                return handled;
            }
        });
    }


}
