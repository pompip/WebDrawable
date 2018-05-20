package cn.pompip.webdrawable;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import cn.pompip.webdrawable.swipecard.CardConfig;
import cn.pompip.webdrawable.swipecard.OverLayCardLayoutManager;
import cn.pompip.webdrawable.swipecard.RenRenCallback;


public class BlankFragment extends Fragment {

    private ArrayList<WebPageInfo> list;
    private RecyclerView recycler_view;
    private RecyclerAdapter adapter;

    public static BlankFragment newInstance(ArrayList<WebPageInfo> pairList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("list", pairList);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getArguments().getParcelableArrayList("list");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_view = view.findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new OverLayCardLayoutManager());
        adapter = new RecyclerAdapter();
        recycler_view.setAdapter(adapter);
        CardConfig.initConfig(getContext());
//        ItemTouchHelper.Callback callback = new RenRenCallback(recycler_view, adapter, list);
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                 ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public int getDragDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getDragDirs(recyclerView, viewHolder);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recycler_view);



    }

    class RecyclerAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_stack, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            WebPageInfo item = list.get(position);
            TextView tv_item_title = holder.itemView.findViewById(R.id.tv_item_title);
            ImageView iv_item_img = holder.itemView.findViewById(R.id.iv_item_img);

            tv_item_title.setText(item.title);
            Glide.with(getActivity()).load(item.bitmap).into(iv_item_img);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
