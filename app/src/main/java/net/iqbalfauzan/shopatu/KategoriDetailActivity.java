package net.iqbalfauzan.shopatu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import net.iqbalfauzan.shopatu.R;
import net.iqbalfauzan.shopatu.fragment.Fragment_Beranda;
import net.iqbalfauzan.shopatu.fragment.Fragment_JualBeli;
import net.iqbalfauzan.shopatu.fragment.Fragment_JualBeli_Kategori;
import net.iqbalfauzan.shopatu.fragment.Fragment_RawatSepatu;
import net.iqbalfauzan.shopatu.fragment.Fragment_TitipJual;
import net.iqbalfauzan.shopatu.fragment.Fragment_TitipJual_Kategori;

import java.util.ArrayList;
import java.util.List;

public class KategoriDetailActivity extends AppCompatActivity {
    Bundle bundle;
    String kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_detail);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Kategori");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        bundle = getIntent().getExtras();
        if (bundle != null) {
            kategori = bundle.getString("kategori");
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, bundle);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager, Bundle bundle) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment_JualBeli_Kategori fragment_jualBeli_kategori = new Fragment_JualBeli_Kategori();
        bundle.putString("kategori", kategori);
        fragment_jualBeli_kategori.setArguments(bundle);
        adapter.addFragment(fragment_jualBeli_kategori, "Jual Beli");

        /*Fragment_TitipJual_Kategori fragment_titipJual_kategori = new Fragment_TitipJual_Kategori();
        bundle.putString("kategori", kategori);
        fragment_jualBeli_kategori.setArguments(bundle);
        adapter.addFragment(fragment_titipJual_kategori, "Titip Jual");*/
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment,String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
