package xuzhongwei.ttchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import xuzhongwei.ttchat.dummy.Group;
import xuzhongwei.ttchat.view.ChatInputView;

public class MainActivity extends AppCompatActivity implements ChatGroupFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadChatGroup();

    }

    private void loadChatGroup(){
        ChatGroupFragment fragment = new ChatGroupFragment();
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 新しく追加を行うのでaddを使用します
        // 他にも、よく使う操作で、replace removeといったメソッドがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.chatGroup, fragment);
        // 最後にcommitを使用することで変更を反映します
        transaction.commit();
    }



    @Override
    public void onListFragmentInteraction(Group.GroupItem item) {
        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        startActivity(intent);
    }

}
