package cn.pompip.webdrawable;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.pompip.webdrawable.R;


public class StackFragment extends Fragment {
    List<WebPageInfo> list ;
    public static StackFragment newInstance(ArrayList<WebPageInfo> pairList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("list", pairList);
        StackFragment fragment = new StackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getArguments().getParcelableArrayList("list");
    }

    StackView stackView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stack, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stackView =   view.findViewById(R.id.stack_view);

    }


    @Override
    public void onResume() {
        super.onResume();
        stackView.setAdapter(new StackAdapter(list));
    }

    class StackAdapter extends BaseAdapter{
        List<WebPageInfo> list;

        public StackAdapter(List<WebPageInfo> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_stack,parent,false);
            }
       TextView tv_item_title =   convertView.findViewById(R.id.tv_item_title);
            ImageView img = convertView.findViewById(R.id.iv_item_img);
            WebPageInfo item = list .get(position);
            tv_item_title.setText(item.title);
            Glide.with(parent).load(item.bitmap).into(img);
            return convertView;
        }
    }
}
