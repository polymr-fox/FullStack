package com.develop.loginov.fullstack.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.develop.loginov.fullstack.R;
import com.develop.loginov.fullstack.adapter.CourseAdapter;
import com.develop.loginov.fullstack.listeners.OnItemClickListener;
import com.develop.loginov.fullstack.model.Course;

import java.util.Arrays;
import java.util.List;

public class CourseFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";

    private List<Course> list;
    private RecyclerView.Adapter adapter;
    private OnItemClickListener onCourseClickListener;

    public CourseFragment() {
    }

    public static CourseFragment newInstance(final int columnCount) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view instanceof RecyclerView) {
            final Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            list = getCourses();
            adapter = new CourseAdapter(list, onCourseClickListener);
            recyclerView.setAdapter(adapter);
        }
    }

    private List<Course> getCourses() {
        return Arrays.asList(Course.of("Spring", "Пётр Иванов", R.drawable.sample_image, 30, 12),
                             Course.of(getString(R.string.name_sample),
                                       "Пётр Иванов",
                                       R.drawable.sample_image,
                                       30,
                                       12),
                             Course.of("Spring", "Пётр Иванов", R.drawable.sample_image, 30, 12),
                             Course.of(getString(R.string.name_sample),
                                       "Пётр Иванов",
                                       R.drawable.sample_image,
                                       30,
                                       12),
                             Course.of("Android", "Пётр Иванов", R.drawable.sample_image, 25, 18));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            onCourseClickListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onCourseClickListener = null;
    }
}
