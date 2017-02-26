package pl.com.kamilwrobel.czater.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pl.com.kamilwrobel.czater.R;
import pl.com.kamilwrobel.czater.dto.Sentence;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SpeachViewHolder>{

    private List<Sentence> sentenceList;
    private LayoutInflater inflater;
    private Context context;
    private String userId;

    public ChatAdapter(List<Sentence> sentenceList, Context context, String userId) {
        this.sentenceList = sentenceList;
        this.context = context;
        this.userId = userId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SpeachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.speach, parent, false);
        return new SpeachViewHolder(view, userId);
    }

    @Override
    public void onBindViewHolder(SpeachViewHolder holder, int position) {
        holder.onViewBind(sentenceList.get(position));
    }

    @Override
    public int getItemCount() {
        return sentenceList.size();
    }

    public static class SpeachViewHolder extends RecyclerView.ViewHolder{

        TextView speach;
        String userId;

        public SpeachViewHolder(View itemView, String userId) {
            super(itemView);
            this.userId = userId;
            this.speach = (TextView) itemView.findViewById(R.id.speach);
        }

        public void onViewBind(Sentence sentence){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)speach.getLayoutParams();
            speach.setText(sentence.getContent());

            if(sentence.getUser_id().equals(userId)){
                speach.setBackgroundResource(R.color.colorSentenceUser);
                params.setMargins(100, 10, 10, 10);
                speach.setLayoutParams(params);
            }else{
                speach.setBackgroundResource(R.color.colorSentenceOther);
                params.setMargins(10, 10, 100, 10); //left, top, right, bottom
                speach.setLayoutParams(params);
            }
        }
    }
}
