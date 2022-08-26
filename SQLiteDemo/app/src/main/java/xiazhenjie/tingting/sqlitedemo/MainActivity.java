package xiazhenjie.tingting.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 插入三条数据
     * @param view
     */
    public void insertData(View view){
        try {
            StudentDao studentDao = new StudentDao(this);

            StudentInfoBean studentInfoBean = new StudentInfoBean("张三",24,"男");
            StudentInfoBean studentInfoBean2 = new StudentInfoBean("李四",20,"女");
            StudentInfoBean studentInfoBean3 = new StudentInfoBean("李四喜",16,"男");
            StudentInfoBean studentInfoBean4 = new StudentInfoBean("杨敏",28,"女");

            studentDao.addData(studentInfoBean);
            studentDao.addData(studentInfoBean2);
            studentDao.addData(studentInfoBean3);
            studentDao.addData(studentInfoBean4);
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 查询所有数据
     * @param view
     */
    public void queryAllData(View view){
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryData();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     * @param view
     */
    public void deleteUser(View view){
        try {
            StudentDao studentDao = new StudentDao(this);
            int num = studentDao.deleteData("张三");
            Toast.makeText(this, "删除了"+num+"条", Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 修改用户信息
     * @param view
     */
    public void updateLisiAge(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            StudentInfoBean studentInfoBean = new StudentInfoBean("李四",100,"男");
            int num = studentDao.updateData(studentInfoBean);
            Toast.makeText(this, "本次修改影响了"+num+"条", Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 清空一个表
     * @param view
     */
    public void clearStudentTable(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            studentDao.clearTable("STUDENT");
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 删除一个表
     * @param view
     */
    public void dropStudentTable(View view) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("删除表格会导致无法再次调用,除非重新初始化，确定要删除吗？？")
                .setPositiveButton("确定", (dialog, which) -> {
                    try {
                        StudentDao studentDao = new StudentDao(MainActivity.this);
                        studentDao.dropTable("STUDENT");
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }catch (SQLiteException e){
                        Toast.makeText(MainActivity.this, "数据库异常", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> {

                })
                .show();

    }

    /**
     * 查询名字叫李四的人
     * @param view
     */
    public void queryData1(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryData1();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 查询名字中包含李四的所有人
     * @param view
     */
    public void queryData2(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryData2();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 查询年龄大于20的所有人
     * @param view
     */
    public void queryData3(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryData3();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 查询20岁以上的男性
     * @param view
     */
    public void queryData4(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryData4();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 按年龄从小到大排序显示所有人
     * @param view
     */
    public void sortStudentByAgeAsc(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.sortStudentByAgeAsc();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 按年龄从大到小排序显示所有人
     * @param view
     */
    public void sortStudentByAgeDesc(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.sortStudentByAgeDesc();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 求所有男性的年龄之和
     * @param view
     */
    public void querySumAgeGentle(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            int sum = studentDao.querySumAgeGentle();
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(sum+"")
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 求所有女性的年龄平均值
     * @param view
     */
    public void queryAveAgeWomen(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            int avgAge = studentDao.queryAveAgeWomen();
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(avgAge+"")
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 谁的年龄最大
     * @param view
     */
    public void queryMaxAgeUserinfo(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryMaxAgeUserinfo();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 是的年龄最小
     * @param view
     */
    public void queryMinAgeUserinfo(View view) {
        try {
            StudentDao studentDao = new StudentDao(this);
            List<StudentInfoBean> studentInfoBeans = studentDao.queryMinAgeUserinfo();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < studentInfoBeans.size(); i++) {
                StudentInfoBean studentInfoBean = studentInfoBeans.get(i);
                String item = "name= "+ studentInfoBean.getName()
                        +", age= "+studentInfoBean.getAge()
                        +", sex= "+studentInfoBean.getSex()
                        +"\r\n\r\n";
                result.append(item);
            }
            new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result.toString())
                    .setPositiveButton("关闭", null).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "数据库异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}