package com.huhuijia.copydemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mTextView2;
    private EditText mEditText;
    private RelativeLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mEditText = (EditText) findViewById(R.id.editText);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
       //第一个文字的长按监听
        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showMenu(mTextView);
                return true;
            }
        });
        //第二个文字的长按监听
        mTextView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showMenu(mTextView2);
                return true;
            }
        });
    }


    public void showMenu(final TextView textView) {
        //可以按照需求随意添加 中心显示的Dialog
        CenterMenuDialog centerMenuDialog = new CenterMenuDialog(MainActivity.this);
        //第一个选择条目
        Menu copyMenu = new Menu.Builder().setCaption("复制").setMenuCommand(new MenuCommand() {
            @Override
            public void onClick() {
                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                myClipboard.setPrimaryClip(ClipData.newPlainText("content", textView.getText()));
                Toast.makeText(MainActivity.this, "内容已经复制", Toast.LENGTH_SHORT).show();
            }
        }).build();
        centerMenuDialog.addMenu(copyMenu);

        //第二个选择条目
        Menu themeMenu = new Menu.Builder().setCaption("取消").setMenuCommand(new MenuCommand() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "已经取消", Toast.LENGTH_SHORT).show();
            }
        }).build();
        centerMenuDialog.addMenu(themeMenu);

        //显示Dialog
        centerMenuDialog.show();
    }


}
