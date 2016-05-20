package com.droidko.swapic.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.droidko.swapic.R;
import com.droidko.swapic.databinding.FragmentLoginBinding;
import com.droidko.swapic.utils.Colorify;
import com.droidko.swapic.viewmodels.LoginViewModel;

public class LoginFragment extends BaseFragment {

    // Vars
    private FragmentLoginBinding mBinding;
    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int onLayoutRequested() {
        return R.layout.fragment_login;
    }

    @Override
    protected void onInitialize(View rootView) {
        mViewModel = new LoginViewModel(getContext());
    }

    @Override
    protected void onDataBinding(View rootView) {
        mBinding = DataBindingUtil.setContentView(getActivity(), onLayoutRequested());

        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onPrepareUi(View rootView) {
        Colorify.setCyclicBackgroundColor(mBinding.rootLayout).start();
    }

    @Override
    public void onDestroy() {
        mViewModel.destroy();

        super.onDestroy();
    }
}
