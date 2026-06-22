package com.sena.actividadandroidstudio.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sena.actividadandroidstudio.model.User;

public class UserRepository {
    private final DatabaseHelper databaseHelper;

    public UserRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long save(String name, String email, String password, String createdAt) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_CREATED_AT, createdAt);
        return db.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    public boolean existsByEmail(String email) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try (Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_ID},
                DatabaseHelper.COLUMN_EMAIL + " = ?",
                new String[]{email},
                null,
                null,
                null
        )) {
            return cursor.moveToFirst();
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try (Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COLUMN_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?",
                new String[]{email, password},
                null,
                null,
                null
        )) {
            if (!cursor.moveToFirst()) {
                return null;
            }

            return new User(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATED_AT))
            );
        }
    }
}
