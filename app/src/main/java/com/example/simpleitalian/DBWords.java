package com.example.simpleitalian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBWords {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tableWords";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITALIAN = "italian";
    private static final String COLUMN_RUSSIAN = "russian";
    private static final String COLUMN_TRANSCRIPTION = "transcription";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_SPEECH = "speech";
    private static final String COLUMN_KNOWN = "known";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_ITALIAN = 1;
    private static final int NUM_COLUMN_RUSSIAN = 2;
    private static final int NUM_COLUMN_TRANSCRIPTION = 3;
    private static final int NUM_COLUMN_IMAGE = 4;
    private static final int NUM_COLUMN_SPEECH = 5;
    private static final int NUM_COLUMN_KNOWN = 6;

    private SQLiteDatabase mDataBase;

    public DBWords(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String italian, String russian, String transcription, int image, int speech) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITALIAN, italian);
        cv.put(COLUMN_RUSSIAN, russian);
        cv.put(COLUMN_TRANSCRIPTION, transcription);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_SPEECH, speech);
        cv.put(COLUMN_KNOWN, 0);

        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Word md) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITALIAN, md.getItalian());
        cv.put(COLUMN_RUSSIAN, md.getRussian());
        cv.put(COLUMN_TRANSCRIPTION, md.getTranscription());
        cv.put(COLUMN_IMAGE, md.getImage());
        cv.put(COLUMN_SPEECH, md.getSpeech());
        cv.put(COLUMN_KNOWN, md.getKnown() ? 1 : 0);

        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(md.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Word select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        String italian = mCursor.getString(NUM_COLUMN_ITALIAN);
        String russian = mCursor.getString(NUM_COLUMN_RUSSIAN);
        String transcription = mCursor.getString(NUM_COLUMN_TRANSCRIPTION);
        int image = mCursor.getInt(NUM_COLUMN_IMAGE);
        int speech = mCursor.getInt(NUM_COLUMN_SPEECH);
        boolean know = (mCursor.getInt(NUM_COLUMN_KNOWN) == 1);

        return new Word(id, italian, transcription, russian, speech, image, know);
    }

    public ArrayList<Word> selectAll(String italian) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ITALIAN + " = ?", new String[]{String.valueOf(italian)}, null, null, null);

        ArrayList<Word> arr = new ArrayList<Word>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String russian = mCursor.getString(NUM_COLUMN_RUSSIAN);
                String transcription = mCursor.getString(NUM_COLUMN_TRANSCRIPTION);
                int image = mCursor.getInt(NUM_COLUMN_IMAGE);
                int speech = mCursor.getInt(NUM_COLUMN_SPEECH);
                boolean know = (mCursor.getInt(NUM_COLUMN_KNOWN) == 1);
                arr.add(new Word(id, italian, transcription, russian, speech, image, know));
            } while (mCursor.moveToNext());
        }

        return arr;
    }

    public ArrayList<Word> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Word> arr = new ArrayList<Word>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String italian = mCursor.getString(NUM_COLUMN_ITALIAN);
                String russian = mCursor.getString(NUM_COLUMN_RUSSIAN);
                String transcription = mCursor.getString(NUM_COLUMN_TRANSCRIPTION);
                int image = mCursor.getInt(NUM_COLUMN_IMAGE);
                int speech = mCursor.getInt(NUM_COLUMN_SPEECH);
                boolean know = (mCursor.getInt(NUM_COLUMN_KNOWN) == 1);
                arr.add(new Word(id, italian, transcription, russian, speech, image, know));
            } while (mCursor.moveToNext());
        }

        return arr;
    }

    public ArrayList<Word> selectAllOrderBy(String orderBy) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, orderBy);

        ArrayList<Word> arr = new ArrayList<Word>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String italian = mCursor.getString(NUM_COLUMN_ITALIAN);
                String russian = mCursor.getString(NUM_COLUMN_RUSSIAN);
                String transcription = mCursor.getString(NUM_COLUMN_TRANSCRIPTION);
                int image = mCursor.getInt(NUM_COLUMN_IMAGE);
                int speech = mCursor.getInt(NUM_COLUMN_SPEECH);
                boolean know = (mCursor.getInt(NUM_COLUMN_KNOWN) == 1);
                arr.add(new Word(id, italian, transcription, russian, speech, image, know));
            } while (mCursor.moveToNext());
        }

        return arr;
    }

    public boolean isInDatabase(String italian, String russian) {
        ArrayList<Word> words = selectAll(italian);
        if (words.size() > 0) {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getRussian().equals(russian)) {
                    return true;
                }
            }
        }
        return false;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ITALIAN + " TEXT, " +
                    COLUMN_RUSSIAN + " TEXT, " +
                    COLUMN_TRANSCRIPTION + " TEXT, " +
                    COLUMN_IMAGE + " INT," +
                    COLUMN_SPEECH + " INT," +
                    COLUMN_KNOWN + " INT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}