package hu.example.fodorsz.myfirstapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import hu.example.fodorsz.myfirstapp.fragments.ArticleFragment;
import hu.example.fodorsz.myfirstapp.fragments.HeadlineFragment;


public class FragmentActivity extends Activity implements HeadlineFragment.OnHeadlineTouchedListener, ArticleFragment.OnArticleTouchedListener {

    ArticleFragment articleFragment;
    HeadlineFragment headlineFragment;
    Fragment actualFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (findViewById(R.id.fragment_container) != null) {
            articleFragment = new ArticleFragment();
            headlineFragment = new HeadlineFragment();

            if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.article_fragment)).commit();
            }
            if (getFragmentManager().findFragmentById(R.id.headline_fragment) != null) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.headline_fragment)).commit();
            }

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, headlineFragment)
                    .commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void touched() { //TODO: kiszervezni ezeket külön handler osztályokba, amiket screen size alapján lekérhetünk egy factory-tól => large handler, land handler, stb...
        if (articleFragment == null || articleFragment.isAdded())
            return; //TODO: is added kényszer megoldás, és igazából nem is megoldás => tehát csak kényszer :)

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, articleFragment)
//                .addToBackStack(null) //TODO: most nem kell, de utánajárni!
                .commit();
    }

    @Override
    public void articleTouched() {
        if (headlineFragment == null || headlineFragment.isAdded()) return;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, headlineFragment)
                .commit();
    }
}
