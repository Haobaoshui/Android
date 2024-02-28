package cn.edu.bistu.se.android.dialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 演示各种对话框
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 普通对话框，有确定、取消两个按钮
     *
     * @param view
     */
    public void onNormalDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //设置对框框属性
        builder.setTitle("普通对话框")
                .setMessage("普通对话框的内容")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "普通对话框 取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "普通对话框 确定", Toast.LENGTH_SHORT).show();
                    }
                });

        //创建并显示对话框
        builder.create().show();
    }

    /**
     * 普通对话框，有确定、取消和忽略3个按钮
     *
     * @param view
     */
    public void onNormal3DialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //设置对框框属性
        builder.setTitle("普通对话框")
                .setMessage("普通对话框的内容")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "普通对话框 取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "普通对话框 确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "普通对话框 忽略", Toast.LENGTH_SHORT).show();
                    }
                });

        //创建并显示对话框
        builder.create().show();
    }

    /**
     * 单选对话框
     *
     * @param view
     */
    public void onSingleChoiceDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final String[] items = {"男", "女"};// 选项数组,该数组也可以在资源文件中定义
        final boolean[] checkedItems = {true, false};// 存放选中状态，true为选中

        builder.setTitle("单选对话框")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                        }
                        checkedItems[which] = true;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                str = items[i];
                            }
                        }
                        Toast.makeText(MainActivity.this, "选择了" + str, Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }

    /**
     * 多选对话框
     *
     * @param view
     */
    public void onMultipleChoiceDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final String[] items = {"数学", "语文", "英语", "政治"};// 存放选项的数组
        final boolean[] checkedItems = {false, true, false, false};
        builder.setTitle("多选对话框")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                            checkedItems[which] = true;
                        else
                            checkedItems[which] = false;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                str = str + items[i] + " ";
                            }
                        }

                        Toast.makeText(MainActivity.this, "选择了" + str, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    /**
     * 列表对话框
     *
     * @param view
     */
    public void onListDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final String[] items = {"数学", "语文", "英语", "政治"};// 存放选项的数组
        builder.setTitle("列表对话框")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "选择了" + items[which], Toast.LENGTH_LONG).show();
                    }
                });
        builder.create().show();
    }


    /**
     * 用户自定义对话框
     *
     * @param view
     */
    public void onCustomDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // 创建一个view，并且将布局加入view中
        final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.customdialog, null, false);
        builder.setTitle("自定义对话框")
                .setView(viewDialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取得用户输入内容，注意findViewById前面的viewDialog，表示在该view里面进行查找
                        EditText editTextUserId = viewDialog.findViewById(R.id.edittext_user_id);
                        EditText editTextUserPassword = viewDialog.findViewById(R.id.edittext_user_password);
                        Toast.makeText(MainActivity.this, "UserId：" + editTextUserId.getText().toString()
                                + "UserPassword：" + editTextUserPassword.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    /**
     * 简单输入对话框
     *
     * @param view
     */
    public void onInputBoxDialogButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText editText = new EditText(MainActivity.this);

        builder.setTitle("自定义对话框")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String string = editText.getText().toString();
                        Toast.makeText(MainActivity.this, "输入内容：" + string, Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }
}