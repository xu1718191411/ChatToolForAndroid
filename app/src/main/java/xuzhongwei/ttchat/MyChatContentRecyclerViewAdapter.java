package xuzhongwei.ttchat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import xuzhongwei.ttchat.ChatContentFragment.OnListFragmentInteractionListener;
import xuzhongwei.ttchat.dummy.Chat;
import xuzhongwei.ttchat.dummy.Group.GroupItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GroupItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyChatContentRecyclerViewAdapter extends RecyclerView.Adapter<MyChatContentRecyclerViewAdapter.GeneralViewHolder> {

    private  List<Chat.ChatItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyChatContentRecyclerViewAdapter(List<Chat.ChatItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public GeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == Chat.ChatIdentity.ISME.ordinal()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chat_me_content,parent,false);
            return new MeViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_chatcontent, parent, false);
        return new OppositeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GeneralViewHolder holder, int position) {


        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);

        if(holder instanceof OppositeViewHolder){
            Bitmap bitmap = BitmapFactory.decodeResource(holder.mView.getResources(),R.drawable.puppy);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(holder.mView.getResources(),bitmap);
            roundedBitmapDrawable.setCircular(true);
            holder.chatProfilePicture.setImageDrawable(roundedBitmapDrawable);
        }



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mValues.get(position).identity.ordinal();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class GeneralViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Chat.ChatItem mItem;
        public final ImageView chatProfilePicture;

        public GeneralViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            chatProfilePicture = (ImageView) view.findViewById(R.id.chatProfilePicture);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    public void update(List<Chat.ChatItem> items){
        mValues = items;
        notifyDataSetChanged();
    }


    public class OppositeViewHolder extends GeneralViewHolder{
        public OppositeViewHolder(View view) {
            super(view);
        }
    }

    public class MeViewHolder extends GeneralViewHolder {

        public MeViewHolder(View view) {
            super(view);
        }

    }

}
