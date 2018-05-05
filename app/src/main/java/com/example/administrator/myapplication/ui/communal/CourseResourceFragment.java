package com.example.administrator.myapplication.ui.communal;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.config.Consant;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.BaseRecycleViewAdapter;
import com.example.administrator.myapplication.adapter.CourseResourceAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseResource;
import com.example.administrator.myapplication.model.DownloadModel;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.utils.JsonUtils;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.util.List;

import library.http.BaseDownloader;
import library.http.BaseRequest;
import library.http.HttpStateUtils;

import static android.app.Activity.RESULT_OK;
import static cn.jpush.im.android.api.jmrtc.JMRTCInternalUse.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseResourceFragment extends Fragment {

    private Context context;

    private View mainView;

    private RecyclerView rvCourseResource;

    private CourseResourceAdapter adapter;

    private Button mBtnUpload;

    private User user;

    private Course course;

    private String filename;

    public CourseResourceFragment() {
        // Required empty public constructor

        // Rescources
    }

    public static CourseResourceFragment newInstance() {
        Bundle args = new Bundle();
        CourseResourceFragment fragment = new CourseResourceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        user = UserModel.getInstance().getUser();
        course = CourseModel.getInstance().getCourse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_course_resource, container, false);
            initView();
        }
        initData();
        initListener();
        return mainView;
    }

    private void initListener() {
        mBtnUpload.setOnClickListener(view -> {
            if (filename == null) {
                new LFilePicker()
                        .withActivity(getActivity())
                        .withRequestCode(Consant.REQUEST_CODE_FROM_ACTIVITY)
                        .withIconStyle(Constant.ICON_STYLE_GREEN)
                        .withMutilyMode(false) //false单选 true多选
                        .start();
            } else {
                CourseModel.getInstance().uploadCourseResource(course.getId(), new File(filename), new BaseRequest.OnRequestListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(Response response) {
                        Log.i("sss", "onCompleted: " + response.toString());
                        HttpStateUtils.showRequestMsg(context, response);
                        Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
            }
        });

        //todo 点击下载
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object _data) {
                CourseResource data = (CourseResource) _data;
                Toast.makeText(context, data.getFileUrl(), Toast.LENGTH_SHORT).show();
                DownloadModel.getInstance().downloadByGet(data.getFileUrl(), null, new BaseDownloader.OnDownLoadListener() {
                    @Override
                    public void onCompleted(int position, String filepath) {
                        Toast.makeText(context,filepath,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
            }
        });
    }

    private void initView() {
        rvCourseResource = mainView.findViewById(R.id.rv_course_resource);
        rvCourseResource.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CourseResourceAdapter(context);
        rvCourseResource.setAdapter(adapter);

        mBtnUpload = mainView.findViewById(R.id.btn_teacher_add_resource);
        // 学生隐藏上传公共资源按钮
        if (user.getType() == 2) {
            mBtnUpload.setVisibility(View.INVISIBLE);
        }

    }

    private void initData() {
        CourseModel.getInstance().getCourseResources(course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<CourseResource> resources = JsonUtils.parseArray(response.get().toString(), "body", CourseResource.class);
                adapter.refreshData(resources);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Consant.REQUEST_CODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                filename = list.get(0);
                Log.i("sss", "initListener: " + filename);
                mBtnUpload.setText("确认提交");
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
