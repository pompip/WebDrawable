package cn.pompip.webdrawable;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().addToBackStack("hello")
                .add(R.id.container, WebFragment.newInstance())
                .add(R.id.container, WebFragment.newInstance())
                .add(R.id.container, WebFragment.newInstance())
                .add(R.id.container, WebFragment.newInstance())
                .commit();
    }


    private ArrayList<WebPageInfo> generatedList() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        ArrayList<WebPageInfo> list = new ArrayList<>();
        for (Fragment fragment : fragments) {
            if (fragment instanceof WebFragment) {
                Bitmap cacheBitmap = ((WebFragment) fragment).getCacheBitmap();
                WebPageInfo webPageInfo = new WebPageInfo();
                webPageInfo.bitmap = cacheBitmap;
                webPageInfo.title = "hello";
                list.add(webPageInfo);
            }
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                StackFragment fragment = StackFragment.newInstance(generatedList());
                getSupportFragmentManager().beginTransaction().addToBackStack("hello").add(R.id.container, fragment).commit();
                break;

            case R.id.button2:
                getSupportFragmentManager().beginTransaction().addToBackStack("hello").add(R.id.container, BlankFragment.newInstance(generatedList())).commit();
                break;

            case R.id.button3:
                getSupportFragmentManager().beginTransaction().addToBackStack("hello").add(R.id.container, ViewPagerFragment.newInstance(generatedList())).commit();
                break;
        }
    }
}
