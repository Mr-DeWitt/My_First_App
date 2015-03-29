package hu.example.fodorsz.myfirstapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import hu.example.fodorsz.myfirstapp.R;


public class HeadlineFragment extends Fragment {

    OnHeadlineTouchedListener callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                callback.touched();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (OnHeadlineTouchedListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement " + OnHeadlineTouchedListener.class.getName());
        }
    }

    public interface OnHeadlineTouchedListener {
        public void touched();
    }
}
