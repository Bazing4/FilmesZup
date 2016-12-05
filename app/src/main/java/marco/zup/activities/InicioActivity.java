package marco.zup.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import marco.zup.R;
import marco.zup.adapter.TabsPagerAdapter;
import marco.zup.fragment.CadastroFragment;
import marco.zup.fragment.InicioFragment;

public class InicioActivity extends AppCompatActivity {

    Toolbar toolbar;

    TabLayout tabLayout;

    ViewPager viewPager;

    TabsPagerAdapter tabsPagerAdapter;

    //Implementando futura de uma ViewPager para a app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        tabsPagerAdapter.addFragments(new InicioFragment(), "Filmes");
        tabsPagerAdapter.addFragments(new CadastroFragment(), "CadastroFragment");
        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}