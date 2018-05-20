package cn.pompip.webdrawable;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment implements View.OnClickListener {


    private WebView web_view;
    private ImageView img;

    public static WebFragment newInstance() {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        web_view = view.findViewById(R.id.web_view);
        web_view.setWebViewClient(new WebViewClient());
        WebSettings settings = web_view.getSettings();
        settings.setSupportMultipleWindows(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        web_view.loadUrl("http://baidu.com");
        web_view.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }
        });


        img = view.findViewById(R.id.img);
        img.setOnClickListener(this);
    }

    public Bitmap getCacheBitmap() {
        web_view.buildDrawingCache();

        return web_view.getDrawingCache().copy(Bitmap.Config.ARGB_8888, false);
    }

    @Override
    public void onClick(View v) {
        img.setImageBitmap(getCacheBitmap());
    }
}
