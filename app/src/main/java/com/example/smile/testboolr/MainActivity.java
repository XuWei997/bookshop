package com.example.smile.testboolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.smile.testboolr.fragment.FragmentBook;
import com.example.smile.testboolr.fragment.FragmentCar;
import com.example.smile.testboolr.fragment.FragmentCategory;
import com.example.smile.testboolr.fragment.FragmentHome;
import com.example.smile.testboolr.fragment.FragmentUser;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

/**
 *
 * @author yechao
 * @功能说明 自定义TabHost
 *
 */
public class MainActivity extends FragmentActivity {


    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    // 定义一个布局
    private LayoutInflater layoutInflater;

    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = { FragmentHome.class,
            FragmentCategory.class, FragmentBook.class, FragmentCar.class,
            FragmentUser.class };

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = { R.drawable.main_tab_item_home,
            R.drawable.main_tab_item_category, R.drawable.main_tab_item_book,
            R.drawable.main_tab_item_car, R.drawable.main_tab_item_user};

    // Tab选项卡的文字
    private String mTextviewArray[] = { "主页", "分类", "书籍", "购物车", "我的" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //启动数据库
        SQLiteStudioService.instance().start(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        Log.i("user: ",user+"");

        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.main_tab_item_bg);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}
