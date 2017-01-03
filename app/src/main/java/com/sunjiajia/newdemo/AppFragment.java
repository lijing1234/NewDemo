/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.newdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunjiajia.newdemo.adapter.MyRecyclerViewAdapter;
import com.sunjiajia.newdemo.adapter.MyStaggeredViewAdapter;
import com.sunjiajia.newdemo.utils.SnackbarUtil;

public class AppFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener,
		MyStaggeredViewAdapter.OnItemClickListener{
	private RecyclerView.LayoutManager mLayoutManager;
	private View mView;

	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerView mRecyclerView;
	private MyRecyclerViewAdapter mRecyclerViewAdapter;
	private MyStaggeredViewAdapter mStaggeredAdapter;

	private static final int VERTICAL_LIST = 0;
	private static final int HORIZONTAL_LIST = 1;
	private static final int VERTICAL_GRID = 2;
	private static final int HORIZONTAL_GRID = 3;
	private static final int STAGGERED_GRID = 4;

	private static final int SPAN_COUNT = 2;
	private int flag = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.frag_main, container, false);
		return mView;
    }

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.id_swiperefreshlayout);
		mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);

		configRecyclerView();

		// 刷新时，指示器旋转后变化的颜色
		mSwipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
		mSwipeRefreshLayout.setOnRefreshListener(this);
	}
	private void configRecyclerView() {

		mLayoutManager =
				new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

		mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
		mRecyclerViewAdapter.setOnItemClickListener(this);
		mRecyclerView.setAdapter(mRecyclerViewAdapter);
		mRecyclerView.setLayoutManager(mLayoutManager);
	}

	@Override
	public void onRefresh() {
		// 刷新时模拟数据的变化
		new Handler().postDelayed(new Runnable() {
			@Override public void run() {
				mSwipeRefreshLayout.setRefreshing(false);
				int temp = (int) (Math.random() * 10);

					mRecyclerViewAdapter.mDatas.add(0, "new" + temp);
					mRecyclerViewAdapter.notifyDataSetChanged();

			}
		}, 1000);
	}

	@Override
	public void onItemClick(View view, int position) {
		SnackbarUtil.show(mRecyclerView, getString(R.string.item_clicked), 0);
	}

	@Override
	public void onItemLongClick(View view, int position) {
		SnackbarUtil.show(mRecyclerView, getString(R.string.item_longclicked), 0);
	}
}
