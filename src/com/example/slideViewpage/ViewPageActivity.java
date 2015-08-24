package com.example.slideViewpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewPageActivity extends FragmentActivity {
  private ViewPager mViewPager;
  private TextView mTextView1,mTextView2,mTextView3;
   private ArrayList<ListView> arrayList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTextView1=getViewById(R.id.tab1);
        mTextView2=getViewById(R.id.tab2);
        mTextView3=getViewById(R.id.tab3);
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });

        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });

        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });

        for(int i=0;i<3;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.item,null);
            ListView listView=(ListView)view.findViewById(R.id.listview1);
            arrayList.add(listView);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showToast("intem=" + i);
                }
            });

        }

        mViewPager=getViewById(R.id.viewpager_listview);
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter();
        mViewPager.setAdapter(myPagerAdapter);
        final int pageMargin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);

        mViewPager.setCurrentItem(1);
        requestData(1);

        mViewPager.setOnPageChangeListener(new PageListener());

    }
    private void setAdapterData(int positon){
        switch (positon){
            case 0:
                arrayList.get(0).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        Arrays.asList("ding1", "ding2", "ding3")));
                mTextView1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                mTextView2.setTextColor(getResources().getColor(android.R.color.white));
                mTextView3.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 1:
                arrayList.get(1).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        Arrays.asList("ding4", "ding5", "ding6")));
                mTextView1.setTextColor(getResources().getColor(android.R.color.white));
                mTextView2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                mTextView3.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 2:
                arrayList.get(2).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        Arrays.asList("ding7", "ding8", "ding9")));
                mTextView1.setTextColor(getResources().getColor(android.R.color.white));
                mTextView2.setTextColor(getResources().getColor(android.R.color.white));
                mTextView3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                break;

        }

    }
    private  <T extends View>T getViewById(int rId){
        return (T)findViewById(rId);
    }
    private int mCurrentPosition=0;
    private float currentPositionOffset = 0f;

    private class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             mCurrentPosition=position;
             currentPositionOffset=positionOffset;
        }

        @Override
        public void onPageSelected(int i) {
            requestData(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }

    }
    private void requestData(int select){
        switch (select){
            case 0:
                setListData(0);
                break;
            case 1:
                setListData(1);
                break;
            case 2:
                setListData(2);
                break;
        }

    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void setListData(int select){
        switch (select){
            case 0:
                setAdapterData(0);
                break;
            case 1:
                setAdapterData(1);
                break;
            case 2:
                setAdapterData(2);
                break;
        }
    }
    private class MyPagerAdapter extends PagerAdapter {
        public MyPagerAdapter(){
            super();
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(arrayList.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(container!=null&&container.getChildCount()>2){
                container.removeViewAt(position);
            }
            container.addView(arrayList.get(position));
            return arrayList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }


    };
}
