package xiazhenjie.tingting.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String db_name = "user_sql.db";
    private static final int version = 1;
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static SQLiteHelper sqLiteHelper;


    public SQLiteHelper(Context context) {
        //第一个参数 上下文
        //第二个参数 数据库的名称
        //第三个参数 null代表默认的游标工厂
        //第四个参数 数据库的版本号，数据库只能升级不能降级
        //version比原先大就会调用onUpgrade
        super(context, db_name, factory, version);
    }

    public static SQLiteHelper getInstance(Context context) {
        if (sqLiteHelper == null) {
            sqLiteHelper = new SQLiteHelper(context);
        }
        return sqLiteHelper;
    }


    /**
     * onCreate是在数据库创建的时候调用的，主要用来初始化数据表结构和插入数据初始化的记录
     * <p>
     * 1.当数据库第一次被创建的时候调用的方法,适合在这个方法里面把数据库的表结构定义出来.
     * 所以只有程序第一次运行的时候才会执行
     * 如果想再看到这个函数执行，必须写在程序然后重新安装这个app
     * 2.数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
     * 所以是调用以上方法的时候才会回调该方法
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS STUDENT(id integer primary key autoincrement, name varchar(20)," +
                "age int,sex varchat(10))");
        Log.e("CMW:TAG", "创建了表");
    }


    /**
     *
     * @param db  数据库
     * @param oldVersion 老版本
     * @param newVersion 新版本
     * 这里的删除等操作必须要保证新的版本必须要比旧版本的版本号要大才行。[即 Version 2.0 > Version 1.0 ] 所以这边我们不需要对其进行操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (version > 1) {
            db.execSQL("alter table STUDENT add address varchar(60)");
        }
    }
}
