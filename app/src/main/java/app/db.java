package app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class db extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, app.TAG_DB, factory, app.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        app.l("onCreate");
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase get() {
        return this.getWritableDatabase();
    }

    public static class tables {
        public static final String SALAVAT = "SALAVAT";
    }

    public static class SALAVAT {
        public static final String ID = "ID";
        public static final String NUMBER = "NUMBER";
        public static final String DATE = "DATE";
    }
}
