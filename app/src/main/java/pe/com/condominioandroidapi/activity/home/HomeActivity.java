package pe.com.condominioandroidapi.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.login.LoginActivity;
import pe.com.condominioandroidapi.activity.main.Menumain;
import pe.com.condominioandroidapi.activity.proyecto.ProyectoFragment;
import pe.com.condominioandroidapi.activity.setting.SettingFragment;
import pe.com.condominioandroidapi.activity.principal.PrincipalFragment;
import pe.com.condominioandroidapi.util.CircleTransform;
import pe.com.condominioandroidapi.util.Constant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.vwCircular)
    ImageView vwCircular;
    @BindView(R.id.btnCloseSession)
    Button btnCloseSession;
    @BindView(R.id.usuario)
    TextView usuario;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_proyectos,
            R.drawable.icon_casa_gris,
           // R.drawable.icon_lupa_gris,
            R.drawable.icon_tuerca_gris
    };
    private int[] tabIconsSelect = {
            R.drawable.ic_proyectos_azul,
            R.drawable.icon_casa_azul,
            //R.drawable.icon_lupa_azul,
            R.drawable.icon_tuerca_azul
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_home);
        setupViews();


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorSeparator));
            drawable.setSize(1, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    tabLayout.getTabAt(0).setIcon(tabIconsSelect[0]);
                } else if (tab.getPosition() == 1) {
                    tabLayout.getTabAt(1).setIcon(tabIconsSelect[1]);

                } else if (tab.getPosition() == 2) {
                    tabLayout.getTabAt(2).setIcon(tabIconsSelect[2]);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                } else if (tab.getPosition() == 1) {
                    tabLayout.getTabAt(1).setIcon(tabIcons[1]);

                } else if (tab.getPosition() == 2) {
                    tabLayout.getTabAt(2).setIcon(tabIcons[2]);

                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item != null && item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                Log.d("FOTO_RED_SOCIAL", "FOTO_RED_SOCIAL");

                if(Constant.FOTO_RED_SOCIAL != "")
                {
                    Log.d("FOTO_RED_SOCIAL", Constant.FOTO_RED_SOCIAL.toString());
                    usuario.setText("Hola "+ Constant.NOMBRE_RED_SOCIAL);
                    Picasso.with(this).load(Constant.FOTO_RED_SOCIAL).transform(new CircleTransform()).into(vwCircular);

                   // Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
                if(Constant.NOMBRE_RED_SOCIAL != "")
                {
                    usuario.setText("Hola "+ Constant.NOMBRE_RED_SOCIAL);

                    //Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
                if(Constant.get(Constant.NOMBRE)!="")
                {
                    usuario.setText("Hola "+ Constant.get(Constant.NOMBRE));

                    //Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
                Log.d("FOTO_RED_SOCIAL", "FOTO_RED_SOCIAL");

                if(Constant.FOTO_RED_SOCIAL != "")
                {
                    Log.d("FOTO_RED_SOCIAL", Constant.FOTO_RED_SOCIAL.toString());
                    usuario.setText("Hola "+ Constant.NOMBRE_RED_SOCIAL);
                    Picasso.with(this).load(Constant.FOTO_RED_SOCIAL).transform(new CircleTransform()).into(vwCircular);

                    //Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
                if(Constant.NOMBRE_RED_SOCIAL != "")
                {
                    usuario.setText("Hola "+ Constant.NOMBRE_RED_SOCIAL);

                    //Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
                if(Constant.get(Constant.NOMBRE)!="")
                {
                    usuario.setText("Hola "+ Constant.get(Constant.NOMBRE));

                    //Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setupViews() {
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //toolbar.setTitle(getResources().getString(R.string.app_name));
        //toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

    }
    @OnClick(R.id.btnCloseSession)
    void onClickBtnCerrar() {
        closeSession();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void closeSession() {
        Constant.set(Constant.KEY_ACCESS_TOKEN, "");
        Constant.set(Constant.ID_PERSONA,"");
        Constant.set(Constant.EMAIL,"");
        Constant.set(Constant.NOMBRE,"");
        startActivity(getIntent());
        finish();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIconsSelect[0]).setText("Proyectos");
        tabLayout.getTabAt(1).setIcon(tabIcons[1]).setText("Inmuebles");
        tabLayout.getTabAt(2).setIcon(tabIcons[2]).setText("Ajustes");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ProyectoFragment(), null);

        if (!Objects.equals(Constant.get(Constant.KEY_ACCESS_TOKEN), "")) {
            adapter.addFrag(new PrincipalFragment(), null);
            adapter.addFrag(new SettingFragment(), null);

        }
        else {
            adapter.addFrag(new LoginActivity(), null);
            adapter.addFrag(new LoginActivity(), null);

        }


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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}


