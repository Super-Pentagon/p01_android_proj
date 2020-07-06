package com.pentagon.p01_android_proj.mine;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pentagon.p01_android_proj.R;
import com.pentagon.p01_android_proj.login.LoginModel;
import com.pentagon.p01_android_proj.login.login.LoginActivity;
import com.pentagon.p01_android_proj.login.mime.GetUserResponse;
import com.pentagon.p01_android_proj.util.UserPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements View.OnClickListener, Callback<GetUserResponse> {

    private ConstraintLayout constraintLayout;
    private TextView userNameText;
    private TextView userSignatureText;
    private ImageView userHeadImage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initViews(view);
        initData();
        return view;
    }

    private void initData() {
        if (UserPreferenceUtil.isUserLogin(getActivity())) {
            new LoginModel().getUserById(UserPreferenceUtil.getUserId(getActivity()), this);
        }
    }

    private void initViews(View view) {
        constraintLayout = view.findViewById(R.id.constraintLayout2);
        userNameText = view.findViewById(R.id.textView);
        userSignatureText = view.findViewById(R.id.textView2);
        userHeadImage = view.findViewById(R.id.imageView3);

        constraintLayout.setOnClickListener(this);
        userNameText.setOnClickListener(this);
        userSignatureText.setOnClickListener(this);
        userHeadImage.setOnClickListener(this);
    }


    public void checkLog(View view) {
        if (!UserPreferenceUtil.isUserLogin(getActivity())) {
            LoginActivity.actionStart(getActivity());
        } else {
            initData();
        }
    }

    @Override
    public void onClick(View v) {
        checkLog(v);
    }

    @Override
    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
        if (response.body().isSuccess()) {
            GetUserResponse getUserResponse = response.body();
            userNameText.setText(getUserResponse.getData().getBuyer().getUsername());
            userSignatureText.setText(getUserResponse.getData().getBuyer().getTel() == null ||
                    getUserResponse.getData().getBuyer().getTel().equals("") ?
                    getUserResponse.getData().getBuyer().getTel() : "发个签名记录一下");
            if (getUserResponse.getData().getBuyer().getAvatar() != null || !getUserResponse.getData().getBuyer().getAvatar().equals("")) {
                Glide.with(this).load(getUserResponse.getData().getBuyer().getAvatar()).into(userHeadImage);
                Log.d("MineFragment", getUserResponse.getData().getBuyer().getAvatar());
            }
        } else {
            Log.e("MineFragment", response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<GetUserResponse> call, Throwable t) {

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}