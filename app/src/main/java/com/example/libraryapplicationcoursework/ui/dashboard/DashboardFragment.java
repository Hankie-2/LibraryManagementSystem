package com.example.libraryapplicationcoursework.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.libraryapplicationcoursework.adapter.ViewPagerAdapter;
import com.example.libraryapplicationcoursework.databinding.FragmentDashboardBinding;
import com.example.libraryapplicationcoursework.helper.GeneralData;
import com.example.libraryapplicationcoursework.profile.BoughtFragment;
import com.example.libraryapplicationcoursework.profile.FavoriteFragment;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView tv_fullname;
    private TextView tv_wallet;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabLayout = binding.tabLayoutProfile;
        viewPager = binding.viewPager;

        tv_fullname = binding.fullnameProfile;
        tv_wallet = binding.walletProfile;

        tv_wallet.setText("$"+GeneralData.getLoggedUser().getWallet());
        tv_fullname.setText(GeneralData.getLoggedUser().getFullname());

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new BoughtFragment(),"Купленные книги");
        viewPagerAdapter.addFragment(new FavoriteFragment(),"Любимые книги");
        viewPager.setAdapter(viewPagerAdapter);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}