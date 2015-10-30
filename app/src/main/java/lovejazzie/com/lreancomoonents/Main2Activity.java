package lovejazzie.com.lreancomoonents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private LinearLayout root;
    private Button btnChooseData;
    private Button btnChooseTime;
    private Button btnSubmit;
    private RadioButton rb[];
    private CheckBox[] cb;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        root = (LinearLayout) findViewById(R.id.root);
        btnChooseData = (Button) findViewById(R.id.btnChooseData);
        btnChooseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String timedata = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
                                System.out.println(timedata);
                        btnChooseData.setText(timedata);

                    }
                },2015,10,8).show();
                //下面解释这个方法
            }
        });
        btnChooseTime = new Button(this);
        btnChooseTime.setText("00:00");
        btnChooseTime.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        root.addView(btnChooseTime);
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String theTime = String.format("%d:%d", hourOfDay, minute);
                        btnChooseTime.setText(theTime);
                        System.out.println(theTime);
                    }
                }, 0, 0, true).show();
                //创建一个时间控件对话框(参数1:context对象 ,
                // 参数2:创建一个设置时间按钮的监听方法,这个方法会传回刚刚设置的时间参数,执行按下按钮后的逻辑
                // 参数3:默认时间
                // 参数4:默认分钟
                // 参数5:是否24小时制)
            }
        });
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb[1].isChecked()){
                    Toast.makeText(Main2Activity.this, "good!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main2Activity.this, "wrong!", Toast.LENGTH_SHORT).show();

                }
            }
        });

            int[] id=new int[]{R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,};
            rb = new RadioButton[4];
        for (int i = 0; i < id.length; i++) {

            rb[i] = (RadioButton) findViewById(id[i]);

        }

        String[] name = new String[]{"太平洋","大西洋","印度洋","北冰洋"};
        for (int i = 0; i < name.length; i++) {
            rb[i].setText(name[i]);

        }

        String[] food = new String[]{"苹果","紫菜","蒸鱼","麻辣烫"};

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity= Gravity.TOP;
             tv.setLayoutParams(lp);


        tv =new TextView(this);
        tv.setText("喜欢哪些食物呢?");
        root.addView(tv, lp);

                /*
                 * 一个重大错误
                 * 这里正确的用法应该是这样的
                 * 新建一个成员数组
                 * 数组开辟堆内存存放数据 new
                 * 利用数组下标访问数组并且初始化
                 * 这个才是真正的checkbox对象
                 * 才可以设置监听*/
                cb= new CheckBox[4];

        for (int i = 0; i < 4; i++) {
                cb[i]=new CheckBox(Main2Activity.this);
                cb[i].setText(food[i]);
                cb[i].setOnCheckedChangeListener(this);
                root.addView(cb[i],lp);
        }
     }

    private String str = "";
    /* 这种方法绘制的字符串每一次都需要重现初始化
     * 因此显示的字符串的排列是按照写代码的从上到下的
      * 顺序排列的
      * eg.cb3的get text()得到的字符串
      * 总是排在第三的位置 而且由于初始化的存在
      * 都不需要else去处理去掉字符串
      * 每次点击控件 都会重现执行一次方法
      * 就会重现初始化字符串*/


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e("ok",str);//输出为err后 需要过滤一下 带tag的信息
        //至少到这里 字符串还是上次显示的字符串
         str = "你喜欢的";//这里开始初始化
//        if (cb[0].isChecked()){
//            str += cb[0].getText() + ",";
//        }
//        if (cb[1].isChecked()){
//            str += cb[1].getText() + ",";
//        }
//        if (cb[2].isChecked()){
//            str += cb[2].getText() + ",";
//        }
//        if (cb[3].isChecked()){
//            str += cb[3].getText() + ",";
//        }
        for (int i = 0; i < 4; i++) {
            if (cb[i].isChecked()){
                str+=cb[i].getText()+",";
            }
        }
        
        
        tv.setText(str);

    }




//        if (isChecked){
//            if (buttonView.getId()==1f) {
//                str += buttonView.getText() + ",";
//            }else
//            if (buttonView.getId()==2f){
//                str += buttonView.getText() + ",";
//            }
//            else
//            if (buttonView.getId()==3f){
//                str += buttonView.getText() + ",";
//            }else
//            if (buttonView.getId()==4f){
//                str += buttonView.getText() + ",";
//            }
//        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}