package xiazhenjie.tingting.sqlitedemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private SQLiteHelper mSQLiteHelper;
    private String tableName = "STUDENT";
    private Context mContext;

    public StudentDao(Context context){
        mContext = context;
        mSQLiteHelper = SQLiteHelper.getInstance(context);
    }

    /**
     * 新增一条数据
     * @param studentInfoBean
     * @return
     */
    public long addData(StudentInfoBean studentInfoBean) {
        // 增删改查每一个方法都要得到数据库，然后操作完成后一定要关闭
        // getWritableDatabase(); 执行后数据库文件才会生成
        // 数据库文件利用DDMS可以查看，在 data/data/包名/databases 目录下即可查看
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", studentInfoBean.getName());
        contentValues.put("age", studentInfoBean.getAge());
        contentValues.put("sex", studentInfoBean.getSex());
        // 返回,显示数据添加在第几行
        // 加了现在连续添加了3行数据,突然删掉第三行,然后再添加一条数据返回的是4不是3
        // 因为自增长
        long rowid = sqLiteDatabase.insert(tableName, null, contentValues);
        //也可以如下
        //sqLiteDatabase.execSQL("insert into STUDENT(name,age,sex) values('111',1,'男')");
        sqLiteDatabase.close();
        return rowid;
    }


    /**
     *  删除一条数据
     *
     * 第一个参数String：需要操作的表名
     * 第二个参数String：where选择语句, 选择哪些行要被删除, 如果为null, 就删除所有行;
     * 第三个参数String[]： where语句的参数, 逐个替换where语句中的 "?" 占位符;
     * @return 返回
     */
    public int deleteData(String name) {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();

        int deleteResult = sqLiteDatabase.delete(tableName, "name=?", new String[]{name});
        sqLiteDatabase.close();
        return deleteResult;
    }


    /**
     *  更改一条数据
     *
     * 第一个参数是表名，在这里指定去更新哪张表里的数据
     * 第二个参数是ContentValues对象，要把更新数据在这里组装进去。
     * 第三个参数String：WHERE表达式，where选择语句, 选择那些行进行数据的更新, 如果该参数为 null, 就会修改所有行;？号是占位符
     * 第四个参数String[]：where选择语句的参数, 逐个替换 whereClause 中的占位符;
     **/
    public int updateData(StudentInfoBean studentInfoBean) {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("age", studentInfoBean.getAge());
        contentValues.put("sex", studentInfoBean.getSex());

        int rowcount = sqLiteDatabase.update(tableName, contentValues, "name=?", new String[]{studentInfoBean.getName()});
        sqLiteDatabase.close();
        return rowcount;
    }

    /**
     * 查询STUDENT表的所有数据
     * @return
     */
    public List<StudentInfoBean> queryData() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;

    }

    /**
     * 查询名字叫李四的人
     * @return
     */
    public List<StudentInfoBean> queryData1() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT where name='李四'", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 查询名字中包含李四的所有人
     * @return
     */
    public List<StudentInfoBean> queryData2() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT where name like '%李四%'", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 查询年龄大于20的所有人
     * @return
     */
    public List<StudentInfoBean> queryData3() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT where age>20", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 查询20岁以上的男性
     * @return
     */
    public List<StudentInfoBean> queryData4() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT where age>20 and sex='男'", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 按年龄从小到大排序显示所有人
     * @return
     */
    public List<StudentInfoBean> sortStudentByAgeAsc() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT order by age asc", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 按年龄从大到小排序显示所有人
     * @return
     */
    public List<StudentInfoBean> sortStudentByAgeDesc() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From STUDENT order by age desc", null);

        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 求所有男性的年龄之和
     * @return
     */
    public int querySumAgeGentle() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select sum(age) as sumvalue from STUDENT where sex='男'", null);
        while (cursor.moveToNext()) {
            int anInt = cursor.getInt(0);
            return anInt;
        }
        return 0;
    }

    /**
     * 求所有女性的年龄平均值
     * @return
     */
    public int queryAveAgeWomen() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select avg(age) as avgvalue from STUDENT where sex='女'", null);
        while (cursor.moveToNext()) {
            int anInt = cursor.getInt(0);
            return anInt;
        }
        return 0;
    }

    /**
     * 谁的年龄最大
     * @return
     */
    public List<StudentInfoBean> queryMaxAgeUserinfo() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from STUDENT where age = (select max(age) from STUDENT)", null);
        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }

    /**
     * 谁的年龄最小
     * @return
     */
    public List<StudentInfoBean> queryMinAgeUserinfo() {
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from STUDENT where age = (select min(age) from STUDENT)", null);
        List<StudentInfoBean> studentInfoBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfoBean studentInfoBean = new StudentInfoBean();
            studentInfoBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            studentInfoBean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            studentInfoBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            studentInfoBeans.add(studentInfoBean);
        }
        sqLiteDatabase.close();
        return studentInfoBeans;
    }



    /**
     * 删除数据库
     * deleteDatabase是Context的方法
     */
    public void deleteDatabase(){
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();
        mContext.deleteDatabase("user_sql.db");
    }

    /**
     * 清空某一个表
     * @param tableName
     */
    public void clearTable(String tableName){
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + tableName);
        sqLiteDatabase.close();
    }

    /**
     * 删除某一个表
     * @param tableName
     */
    public void dropTable(String tableName){
        SQLiteDatabase sqLiteDatabase = mSQLiteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("drop table  "+tableName);
        sqLiteDatabase.close();
    }



}
