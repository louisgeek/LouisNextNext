package com.louisgeek.louisnextnext;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.id_vp_view)
    ViewPager viewPager;
    List<View> viewList = new ArrayList<>();
    @Bind(R.id.id_tv_up)
    TextView idTvUp;
    @Bind(R.id.id_tv_down)
    TextView idTvDown;
    @Bind(R.id.id_tv_title)
    TextView id_tv_title;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

        viewPager.setAdapter(new MyPagerAdapter(viewList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dealShowUpOrDown(position);
                id_tv_title.setText("信息填写" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dealShowUpOrDown(0);
        id_tv_title.setText("信息填写" + 0);
    }

    private void dealShowUpOrDown(int position) {
        if (position == 0) {
            idTvUp.setVisibility(View.INVISIBLE);
        } else {
            idTvUp.setVisibility(View.VISIBLE);
        }
        if (position == viewList.size() - 1) {
            idTvDown.setText("完成");
        } else {
            idTvDown.setText("下一步");
        }
    }

    private void initData() {
        List<List<Map<String, Object>>> mapList_List = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (int j = 0; j < 11; j++) {
                TextoreInfo textoreInfo = new TextoreInfo(j, j + "", j, (j + 1), "说明" + j, "key_" + j, null, null, i);
                Map<String, Object> map = new HashMap<>();

                map.put("textoreInfo", textoreInfo);
                mapList.add(map);
            }
            mapList_List.add(mapList);
        }


        // View  view=null;
        for (int i = 0; i < mapList_List.size(); i++) {
           /*  ViewHolder viewHolder=null;
           if (view==null){
                viewHolder=new ViewHolder();
                view=LayoutInflater.from(this).inflate(R.layout.vp_content,null,false);

                view.setTag(viewHolder);
            }else
            {
                viewHolder= (ViewHolder) view.getTag();
            }*/

            // viewHolder.id_et= ButterKnife.findById(view, R.id.id_et);
            //viewHolder.id_et.setHint("输入"+i);

            View view = LayoutInflater.from(this).inflate(R.layout.vp_content, null, false);

            LinearLayout id_ll_view_layout = ButterKnife.findById(view, R.id.id_ll_view_layout);

            for (int j = 0; j < mapList_List.get(i).size(); j++) {
                TextoreInfo textoreInfo = (TextoreInfo) mapList_List.get(i).get(j).get("textoreInfo");

                switch (textoreInfo.getTypeCode()) {
                    case TextoreInfo.TYPE_CODE_EditText:
                        EditText et = new EditText(this);
                        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        et.setLayoutParams(vlp);
                        et.setHint("输入" + i + "的et");
                        id_ll_view_layout.addView(et);
                        break;
                    case TextoreInfo.TYPE_CODE_CheckBox:
                        CheckBox cb = new CheckBox(this);
                        ViewGroup.LayoutParams vlp_cb = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        cb.setLayoutParams(vlp_cb);
                        cb.setText("cb");
                        id_ll_view_layout.addView(cb);
                        break;
                    case TextoreInfo.TYPE_CODE_RadioButton:
                        ViewGroup.LayoutParams vlp_rg_rb = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        RadioGroup rg = new RadioGroup(this);
                        //rg.setOrientation();
                        rg.setLayoutParams(vlp_rg_rb);
                        RadioButton rb1 = new RadioButton(this);
                        rb1.setLayoutParams(vlp_rg_rb);
                        rb1.setText("rb1");
                        rg.addView(rb1);
                        RadioButton rb2 = new RadioButton(this);
                        rb2.setLayoutParams(vlp_rg_rb);
                        rb2.setText("rb2");
                        rg.addView(rb2);
                        id_ll_view_layout.addView(rg);
                        break;
                    case TextoreInfo.TYPE_CODE_Switch:
                        Switch swi = new Switch(this);
                        ViewGroup.LayoutParams vlp_swi = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        swi.setLayoutParams(vlp_swi);
                        swi.setText("Switch");
                        id_ll_view_layout.addView(swi);
                        break;
                    case TextoreInfo.TYPE_CODE_Spinner:
                        String[] strs = new String[5];
                        for (int k = 0; k < strs.length; k++) {
                            strs[k] = "选项" + k;
                        }
                        Spinner spi = new Spinner(this);
                        ViewGroup.LayoutParams vlp_spi = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        spi.setLayoutParams(vlp_spi);
                        spi.setPadding(20, 5, 5, 20);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, strs);
                        spi.setAdapter(arrayAdapter);
                        id_ll_view_layout.addView(spi);
                        break;
                    case TextoreInfo.TYPE_CODE_TextView:
                        TextView tv = new TextView(this);
                        ViewGroup.LayoutParams vlp_tv = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        tv.setLayoutParams(vlp_tv);
                        tv.setText("tv");
                       // tv.setVisibility(View.GONE);
                        id_ll_view_layout.addView(tv);
                        break;
                    case TextoreInfo.TYPE_CODE_CheckedTextView:
                        CheckedTextView ctv = new CheckedTextView(this);
                        ViewGroup.LayoutParams vlp_ctv = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        ctv.setLayoutParams(vlp_ctv);
                        ctv.setText("ctv");
                       // ctv.setVisibility(View.GONE);
                        id_ll_view_layout.addView(ctv);
                        break;
                    case TextoreInfo.TYPE_CODE_SeekBar:
                        SeekBar sb = new SeekBar(this);
                        ViewGroup.LayoutParams vlp_sb = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        sb.setLayoutParams(vlp_sb);
                       // sb.setVisibility(View.GONE);
                        id_ll_view_layout.addView(sb);
                        break;
                    case TextoreInfo.TYPE_CODE_NumberPicker:
                        NumberPicker np = new NumberPicker(this);
                        ViewGroup.LayoutParams vlp_np = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        np.setLayoutParams(vlp_np);
                        np.setMinValue(0);
                        np.setMaxValue(10);
                       // np.setVisibility(View.GONE);
                        id_ll_view_layout.addView(np);
                        break;
                    case TextoreInfo.TYPE_CODE_DatePicker:
                        Log.d(TAG, "initData: TYPE_CODE_DatePicker");
                        //code方式不能设置spinner
                        View spinner_data_picker_view=LayoutInflater.from(this).inflate(R.layout.spinner_data_picker,null,false);
                        DatePicker dp=ButterKnife.findById(spinner_data_picker_view,R.id.id_spinner_datePicker);
                        Calendar calendar = Calendar.getInstance();
                        int year=calendar.get(Calendar.YEAR);
                        int monthOfYear=calendar.get(Calendar.MONTH);
                        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);

                        dp.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
                            //该事件只在 android:datePickerMode="spinner" 时候有效
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Toast.makeText(MainActivity.this, "您选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日!", Toast.LENGTH_SHORT).show();
                            }
                        });
                       // dp.setVisibility(View.GONE);
                        id_ll_view_layout.addView(dp);
                        break;
                    case TextoreInfo.TYPE_CODE_TimePicker:
                        //code方式不能设置spinner
                        View spinner_time_picker_view=LayoutInflater.from(this).inflate(R.layout.spinner_time_picker, null, false);
                        TimePicker tp=ButterKnife.findById(spinner_time_picker_view, R.id.id_spinner_timePicker);
                        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                            @Override
                            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(MainActivity.this, "您选择的时间是：" + hourOfDay + "时" + minute + "分!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        tp.setIs24HourView(true);
                        id_ll_view_layout.addView(tp);
                        break;
                   /* case TextoreInfo.TYPE_CODE_CalendarView:
                        View calendar_view=LayoutInflater.from(this).inflate(R.layout.calendar_view, null, false);
                        CalendarView cv=ButterKnife.findById(calendar_view, R.id.id_calendarView);
                        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                Toast.makeText(MainActivity.this,"您选择的时间是："+ year + "年" + month + "月" + dayOfMonth + "日",Toast.LENGTH_SHORT).show();
                            }
                        });
                        id_ll_view_layout.addView(cv);
                        break;*/

                }
            }


            viewList.add(view);
        }


    }


    @OnClick({R.id.id_tv_up, R.id.id_tv_down})
    public void onClick(View view) {
        int nowIndex = viewPager.getCurrentItem();
        switch (view.getId()) {
            case R.id.id_tv_up:
                //Toast.makeText(MainActivity.this, "id_tv_up", Toast.LENGTH_SHORT).show();
                if (nowIndex > 0) {
                    nowIndex--;
                    viewPager.setCurrentItem(nowIndex, true);
                }
                break;
            case R.id.id_tv_down:
                // Toast.makeText(MainActivity.this, "id_tv_down", Toast.LENGTH_SHORT).show();
                int count = viewList.size();
                if (nowIndex < count - 1) {
                    nowIndex++;
                    viewPager.setCurrentItem(nowIndex, true);
                }
                break;
        }
    }

    class ViewHolder {
        private EditText id_et;
    }
}
