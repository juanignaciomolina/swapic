package com.droidko.swapic.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.droidko.swapic.R;
import com.droidko.swapic.adapters.HomeImageAdapter;
import com.droidko.swapic.databinding.FragmentHomeBinding;
import com.droidko.swapic.viewmodels.HomeViewModel;

import rx.Observer;

public class HomeFragment extends BaseFragment {

    // Vars
    private FragmentHomeBinding mBinding;
    private HomeViewModel mViewModel;
    private HomeImageAdapter mHomeImageAdapter;

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int onLayoutRequested() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitialize(View rootView) {
        mViewModel = new HomeViewModel(getContext());
        mHomeImageAdapter = new HomeImageAdapter(getContext());
    }

    @Override
    protected void onDataBinding(View rootView) {
        mBinding = DataBindingUtil.setContentView(getActivity(), onLayoutRequested());

        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onPrepareUi(View rootView) {
        mBinding.picturesViewPager.setAdapter(mHomeImageAdapter);

        startListeningForNewPictures();
    }

    private void startListeningForNewPictures() {
        mViewModel
                .startFetching()
                .subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("HomeFragment", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("HomeFragment", "onError: " + e);
            }

            @Override
            public void onNext(String imageUrl) {
                Log.d("HomeFragment", "onNext: " + imageUrl);
                mHomeImageAdapter.addPicture(imageUrl);
            }
        });
    }
}
