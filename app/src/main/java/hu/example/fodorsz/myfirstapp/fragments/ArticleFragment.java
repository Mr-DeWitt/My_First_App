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


public class ArticleFragment extends Fragment {
    OnArticleTouchedListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                listener.articleTouched();
                return true;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnArticleTouchedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " + OnArticleTouchedListener.class.getName());
        }
    }

    public interface OnArticleTouchedListener {
        public void articleTouched();
    }
}
