package io.sevenluck.client.chat2me.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by loki on 6/12/16.
 */
public class Chat2MeDataHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chat2me.db";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT);

    public Chat2MeDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ChatEntity.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ChatEntity.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addChat(Chat chat) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(ChatEntity.COLUMN_CREATED, toString(chat.getCreated()));
            values.put(ChatEntity.COLUMN_ENTRY_ID, chat.getEntityId());
            values.put(ChatEntity.COLUMN_NAME, chat.getName());

            db.insert(ChatEntity.TABLE_NAME, null, values);
        } finally {
            closeSafely(db);
        }
    }

    public void closeSafely(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }

    public Cursor getChatCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ChatEntity.TABLE_NAME, null);
    }

    public List<Chat> getChats() {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + ChatEntity.TABLE_NAME, null);

            List<Chat> results = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    Chat chat = new Chat();
                    chat.setName(cursor.getString(cursor.getColumnIndexOrThrow(ChatEntity.COLUMN_NAME)));
                    chat.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ChatEntity._ID)));
                    Date createdDate = null;
                    try {
                        createdDate = sd.parse(cursor.getString(cursor.getColumnIndexOrThrow(ChatEntity.COLUMN_CREATED)));
                    } catch (ParseException e) {}
                    chat.setCreated(createdDate);
                    chat.setEntityId(cursor.getLong(cursor.getColumnIndexOrThrow(ChatEntity.COLUMN_ENTRY_ID)));
                    results.add(chat);
                } while (cursor.moveToNext());
            }

            return results;
        } finally {
            closeSafely(db);
        }

    }

    public String toString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static final class ChatEntity implements BaseColumns {

        public ChatEntity() {}

        public static final String COLUMN_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATED = "create_date";

        public static final String TABLE_NAME = "chats";
        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "("
                + _ID                   + " INTEGER PRIMARY KEY, "
                + COLUMN_ENTRY_ID       + " INTEGER, "
                + COLUMN_NAME           + " TEXT, "
                + COLUMN_CREATED        + " TEXT )";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }






}
