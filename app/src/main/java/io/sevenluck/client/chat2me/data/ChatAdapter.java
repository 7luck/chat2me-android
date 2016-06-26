package io.sevenluck.client.chat2me.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import io.sevenluck.client.chat2me.R;

/**
 * Created by loki on 6/26/16.
 */
public class ChatAdapter extends CursorAdapter {

    public ChatAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView chatTv = (TextView) view.findViewById(R.id.row_chat_tv);
        TextView chatCreatedTv = (TextView) view.findViewById(R.id.row_chat_created_tv);

        chatCreatedTv.setText(cursor.getString(cursor.getColumnIndexOrThrow(Chat2MeDataHelper.ChatEntity.COLUMN_CREATED)));
        chatTv.setText(cursor.getString(cursor.getColumnIndexOrThrow(Chat2MeDataHelper.ChatEntity.COLUMN_NAME)));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_chatroom_row, null);
    }

}
