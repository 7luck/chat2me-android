package io.sevenluck.client.chat2me.data;

import android.provider.BaseColumns;

/**
 * Created by loki on 6/12/16.
 */
public final class ChatEntity {

    public ChatEntity() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "chats";
        public static final String COLUMN_NAME_ENTRY_ID = "chat_id";
        public static final String COLUMN_NAME = "chat_name";
        public static final String COLUMN_NAME_CREATED = "created";
    }
}




