package com.example.rvcontentproviderwithsql;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public abstract class CursorRecycleViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;

    private Cursor mCursor;

    private boolean mDataValid;

    private int mRowIDColumn;

    private DataSetObserver mDataSetObserver;

    public CursorRecycleViewAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mDataValid = cursor != null;
        mRowIDColumn = mDataValid ? mCursor.getColumnIndex("_id") : -1;
        mDataSetObserver = new NotifyiringDataObserver(this);
    }

    public Cursor getmCursor() {

        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (mDataValid && mCursor != null) {
            return mCursor.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (mDataValid && mCursor != null && mCursor.moveToPosition(position)) {
            return mCursor.getLong(mRowIDColumn);
        }
        return 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    public static final String TAG = CursorRecycleViewAdapter.class.getSimpleName();

    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't nove cursor to position" + position);
        }
        onBindViewHolder(viewHolder, mCursor);
    }

    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        final Cursor oldCursor = mCursor;
        if (oldCursor != null && mDataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (mCursor != null) {
            if (mDataSetObserver != null) {
                mCursor.registerDataSetObserver(mDataSetObserver);
            }
            mRowIDColumn = newCursor.getColumnIndexOrThrow("_ID");
            mDataValid = true;
            notifyDataSetChanged();
        }else {
            mRowIDColumn = -1;
            mDataValid = false;
            notifyDataSetChanged();
    }
        return  oldCursor;
    }
    public void setDataValid(boolean mDataValid) {

        this.mDataValid = mDataValid;
}

}

class NotifyiringDataObserver extends DataSetObserver {
    private RecyclerView.Adapter adapter;

    public NotifyiringDataObserver(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        ((CursorRecycleViewAdapter) adapter).setDataValid(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onInvalidated() {
        super.onInvalidated();
        ((CursorRecycleViewAdapter) adapter).setDataValid(false);
    }
}