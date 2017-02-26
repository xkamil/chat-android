package pl.com.kamilwrobel.czater;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.kamilwrobel.czater.adapters.ChatAdapter;
import pl.com.kamilwrobel.czater.dto.Sentence;

public class ChatActivity extends BaseActivity {

    @BindView(R.id.activity_chat_rc_chat)
    RecyclerView rcChat;

    @BindView(R.id.activity_chat_et_new_sentence)
    EditText etNewSentence;

    private List<Sentence> sentences;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        addFakeSentenceList();

        rcChat.setLayoutManager(new LinearLayoutManager(this));

        chatAdapter = new ChatAdapter(sentences, this, getToken());

        rcChat.setAdapter(chatAdapter);

    }

    @OnClick(R.id.activity_chat_btn_add_sentence)
    public void addNewSentence(View view) {
        showToast("adding sentence");
        String text = etNewSentence.getText().toString().trim();

        if (text.isEmpty()) {
            return;
        }

        Sentence sentence = new Sentence();
        //TODO wywalic to randomowe generowanie idkow
        String userId = Math.random() * 2 > 1 ? getToken() : "jakisinnyczeresnaik";
        sentence.setContent(text);
        sentence.setUser_id(userId);

        sentences.add(sentence);
        rcChat.swapAdapter(chatAdapter, false);
        rcChat.scrollToPosition(sentences.size() - 1);
    }

    public void addFakeSentenceList() {
        List<Sentence> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Sentence sentence = new Sentence();
            if (i % 2 == 0) {
                sentence.setUser_id(getUserId());
            } else {
                sentence.setUser_id(getUserId() + "asdf");
            }

            sentence.setContent("Jakis tam tekst lorem ipsum dolor sit amnet amegsfasd" + Math.random());
            list.add(sentence);
        }

        this.sentences = list;
    }
}
