package net.iqbalfauzan.shopatu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.iqbalfauzan.shopatu.fragment.Fragment_Beranda;
import net.iqbalfauzan.shopatu.fragment.Fragment_Kategori;
import net.iqbalfauzan.shopatu.fragment.Fragment_Keranjang;
import net.iqbalfauzan.shopatu.fragment.Fragment_Profil;
import net.iqbalfauzan.shopatu.fragment.Fragment_Transaksi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        selectedFragment = Fragment_Beranda.newInstance();
                        break;
                    case R.id.menu_kategori:
                        selectedFragment = Fragment_Kategori.newInstance();
                        break;
                    case R.id.menu_keranjang:
                        selectedFragment = Fragment_Keranjang.newInstance();
                        break;
                    case R.id.menu_transaksi:
                        selectedFragment = Fragment_Transaksi.newInstance();
                        break;
                    case R.id.menu_profil:
                        selectedFragment = Fragment_Profil.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, Fragment_Beranda.newInstance());
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_pengaturan:
                startActivity(new Intent(MainActivity.this, NotifikasiActivity.class));
                return true;
            case R.id.action_chatingan:
                startActivity(new Intent(MainActivity.this, ListChatActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
