package com.example.tablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoriesAdapter extends FragmentPagerAdapter {

    Context m_context;

    public CategoriesAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    public CategoriesAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        m_context = context;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return m_context.getResources().getText(R.string.home_tab);
            case 1:
                return m_context.getResources().getText(R.string.ps4_tab);
            case 2:
                return m_context.getResources().getText(R.string.xbox_tab);
            case 3:
                return m_context.getResources().getText(R.string.ofertas_tab);
            case 4:
                return m_context.getResources().getText(R.string.carrito_tab);
        }
        return null;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new PS4Fragment();
            case 2:
                return new XboxFragment();
            case 3:
                return new OfertasFragment();
            case 4:
                return new CarritoCompraFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
