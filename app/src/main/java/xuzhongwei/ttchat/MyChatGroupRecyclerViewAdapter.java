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

import java.util.List;

import xuzhongwei.ttchat.ChatGroupFragment.OnListFragmentInteractionListener;
import xuzhongwei.ttchat.dummy.Group.GroupItem;


/**
 * {@link RecyclerView.Adapter} that can display a {@link GroupItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyChatGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyChatGroupRecyclerViewAdapter.ViewHolder> {

    private final List<GroupItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public MyChatGroupRecyclerViewAdapter(List<GroupItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_chatgroup, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mDetailView.setText(mValues.get(position).details);

        Bitmap bitmap = BitmapFactory.decodeResource(holder.mView.getResources(),R.drawable.cat);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(holder.mView.getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        holder.profilePicture.setImageDrawable(roundedBitmapDrawable);

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
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mDetailView;
        public final ImageView profilePicture;
        public GroupItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mDetailView =  (TextView) view.findViewById(R.id.detail);
            profilePicture = (ImageView) view.findViewById(R.id.profilePicture);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
