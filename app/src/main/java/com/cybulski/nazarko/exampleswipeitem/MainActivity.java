package com.cybulski.nazarko.exampleswipeitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cybulski.nazarko.exampleswipeitem.fragment.OneFragment;
import com.cybulski.nazarko.exampleswipeitem.fragment.ThreeFragment;
import com.cybulski.nazarko.exampleswipeitem.fragment.TwoFragment;
import com.cybulski.nazarko.exampleswipeitem.views.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private TabLayout tabLayout;
  private NonSwipeableViewPager viewPager;

  private Spinner spinnerNav;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    spinnerNav = (Spinner)findViewById(R.id.spinner_nav);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("");
    toolbar.setSubtitle("");

    viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);

    initSpinner();
  }

  private void initSpinner() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("Top News");
    list.add("Politics");
    list.add("Business");
    list.add("Sports");
    list.add("Movies");

    String[] array = new String[list.size()];
    array = list.toArray(array);


    ArrayAdapter<String> adapter  =  new  ArrayAdapter<String>(
        this, android.R.layout.simple_list_item_1, array );
    spinnerNav.setAdapter(adapter);






  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.tab_menu, menu);
    return true;

  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new OneFragment(), "ONE");
    adapter.addFragment(new TwoFragment(), "TWO");
    adapter.addFragment(new ThreeFragment(), "THREE");
    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }
}
