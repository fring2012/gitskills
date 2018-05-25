package com.example.administrator.iot_lib.view.junit;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import com.example.administrator.iot_lib.R;
import com.example.administrator.iot_lib.view.activity.MainActivity;

public class MyFirstTestActivityTest extends ActivityUnitTestCase<MainActivity> {
    private MainActivity mainActivity;
    private TextView mFirstTestText;

    /**
     * 构造函数是由测试用的Runner调用，用于初始化测试类的
     */
    public MyFirstTestActivityTest() {
        super(MainActivity.class);
    }

    /**
     * 把需要重复写的代码写在这个类似初始化的方法中
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity(); //获取正在测试的Activity的引用
        mFirstTestText = (TextView) mainActivity.findViewById(R.id.t1); //获取布局
    }

    public void testPreconditons(){
        assertNotNull("activity is null",mainActivity); //断言是否为空
        assertNotNull("mFirstTestText is null",mFirstTestText);
    }

    /**
     * 验证text的文本
     * 当命名测试方法时，我们可以使用下划线将被测试的内容与测试用例区分开。这种风格使得我们可以更容易分清哪些是测试用例。
     */
    public void testMyFirstTestTextView_labelText(){
        String expected = mainActivity.getString(R.string.app_name);
        //String expected = "sdad";  //这个估计搞的错的
        String actual = mFirstTestText.getText().toString();
        assertEquals(expected,actual);
    }
}
