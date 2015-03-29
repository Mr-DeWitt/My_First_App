package hu.example.fodorsz.myfirstapp;

import android.app.Activity;
import android.os.Bundle;

import hu.example.fodorsz.myfirstapp.fragments.ArticleFragment;
import hu.example.fodorsz.myfirstapp.fragments.HeadlineFragment;


public class FragmentActivity extends Activity implements HeadlineFragment.OnHeadlineTouchedListener, ArticleFragment.OnArticleTouchedListener {

    ArticleFragment articleFragment;
    HeadlineFragment headlineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (findViewById(R.id.fragment_container) != null) {
            headlineFragment = new HeadlineFragment();
            articleFragment = new ArticleFragment();

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, headlineFragment)
                    .commit();
        }
    }

    @Override
    public void touched() { //TODO: kiszervezni ezeket külön handler osztályokba, amiket screen size alapján lekérhetünk egy factory-tól => large handler, land handler, stb...
        if (articleFragment == null) return;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, articleFragment)
//                .addToBackStack(null) //TODO: most nem kell, de utánajárni!
                .commit();
    }

    @Override
    public void articleTouched() {
        if (headlineFragment == null) return;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, headlineFragment)
                .commit();
    }
}
