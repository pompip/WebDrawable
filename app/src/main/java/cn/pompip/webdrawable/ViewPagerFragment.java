package cn.pompip.webdrawable;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ViewPagerFragment extends Fragment {

    public static ViewPagerFragment newInstance(ArrayList<WebPageInfo> list) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("list", list);
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private ViewPager view_pager;
    ArrayList<WebPageInfo> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getArguments().getParcelableArrayList("list");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_pager = view.findViewById(R.id.view_pager);
        view_pager.setAdapter(new WebPagerAdapter(list));
//        view_pager.setPageTransformer(false,new MarginPageTransformer());

    }


    class WebPagerAdapter extends PagerAdapter{

        ArrayList<WebPageInfo> list;

        public WebPagerAdapter(ArrayList<WebPageInfo> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view ==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater from = LayoutInflater.from(container.getContext());
            View view = from.inflate(R.layout.item_stack, container, false);
            WebPageInfo item = list.get(position);
            TextView tv_item_title =   view.findViewById(R.id.tv_item_title);
            ImageView img = view.findViewById(R.id.iv_item_img);
            tv_item_title.setText(item.title);
            Glide.with(container).load(item.bitmap).into(img);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (object instanceof View){
                container.removeView((View) object);
            }
        }
    }
    public class MarginPageTransformer implements ViewPager.PageTransformer{
        @Override
        public void transformPage(View page, float position) {

            float abs = Math.abs(position);
            if (abs < 0.3f) {
                page.setScaleX(1);
                page.setScaleY(1);
            } else if (abs >= 0.3 && abs <= 0.5) {
                page.setScaleX(1 - abs + 0.3f);
                page.setScaleY(1 - abs + 0.3f);
            } else {
                page.setScaleX(0.8f);
                page.setScaleY(0.8f);
            }
        }
    }

}
