//package com.haiwai.housekeeper.view;
//
//import android.content.Context;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.haiwai.housekeeper.R;
//
//
///**
// * Created by Shenlong on 2015/7/2.
// */
//public class PullRefreshRecyclerView extends LinearLayout {
//    private RecyclerView mRecyclerView;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private RefreshLoadMoreListener mPullLoadMoreListener;
//    private boolean hasMore = true;
//    private boolean isRefresh = false;
//    private boolean isLoadMore = false;
//    private LinearLayout mFooterView;
//    private Context mContext;
//    private View mExceptView;
//    /**
//     * 异常图片控件
//     */
//    private static ImageView exceptIv;
//    /**
//     * 异常内容文本控件
//     */
//    private static TextView exceptTv;
//
//    public PullRefreshRecyclerView(Context context) {
//        super(context);
//        initView(context);
//    }
//
//    public PullRefreshRecyclerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initView(context);
//    }
//
//    private void initView(Context context) {
//        mContext = context;
//        View view = LayoutInflater.from(context).inflate(R.layout.pull_refresh_layout, null);
//
//        mExceptView = (LinearLayout) view.findViewById(R.id.exception_layout);
//        exceptIv = (ImageView) mExceptView.findViewById(R.id.excption_image);
//        exceptTv = (TextView) mExceptView.findViewById(R.id.excption_text);
//        exceptIv.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 点击图片刷新
//                if (!isRefresh) {
//                    mSwipeRefreshLayout.setRefreshing(true);
//                    isRefresh = true;
//                    refresh();
//                }
//            }
//        });
//        mExceptView.setVisibility(View.GONE);
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
//        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh(this));
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        mRecyclerView.setVerticalScrollBarEnabled(true);
//
//        mRecyclerView.setHasFixedSize(true);
////        setLinearLayout();
//        // 设置Item增加、移除动画
////        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加分割线
//        //mRecyclerView.addItemDecoration(new DividerItemDecoration(
//        //getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
//        mRecyclerView.setOnScrollListener(new RecyclerViewOnScroll(this));
//
//        mRecyclerView.setOnTouchListener(
//                new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (isRefresh) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//        );
//
//        mFooterView = (LinearLayout) view.findViewById(R.id.footer_linearlayout);
//        mFooterView.setVisibility(View.GONE);
//        this.addView(view);
//
//    }
//
//
//    /**
//     * 线性布局管理器
//     */
//    public void setLinearLayout() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//    }
//
//    /**
//     * 网格布局管理器
//     */
//
//    public void setGridLayout(int spanCount) {
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        // 设置布局管理器
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//    }
//
//
//    /**
//     * 交错网格布局管理器
//     */
//
//    public void setStaggeredGridLayout(int spanCount) {
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
//        // 设置布局管理器
//        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
//    }
//
//
//    public void setPullRefreshEnable(boolean enable) {
//        mSwipeRefreshLayout.setEnabled(enable);
//    }
//
//    public boolean getPullRefreshEnable() {
//        return mSwipeRefreshLayout.isEnabled();
//    }
//
//    public RecyclerView.LayoutManager getLayoutManager() {
//        return mRecyclerView.getLayoutManager();
//    }
//
//
//    public void loadMore() {
//        if (mPullLoadMoreListener != null && hasMore) {
//            mFooterView.setVisibility(View.VISIBLE);
//            mPullLoadMoreListener.onLoadMore();
//
//        }
//    }
//
//    /**
//     * 加载更多完毕,为防止频繁网络请求,isLoadMore为false才可再次请求更多数据
//     */
//
//    public void setPullLoadMoreCompleted() {
//        isRefresh = false;
//        mSwipeRefreshLayout.setRefreshing(false);
//
//        isLoadMore = false;
//        mFooterView.setVisibility(View.GONE);
//
//    }
//
//    /**
//     * 加载更多完毕,为防止频繁网络请求,isLoadMore为false才可再次请求更多数据
//     */
//    public void setLoadMoreCompleted() {
//        isLoadMore = false;
//        mFooterView.setVisibility(View.GONE);
//    }
//
//    public void stopRefresh() {
//        isRefresh = false;
//        mSwipeRefreshLayout.setRefreshing(false);
//    }
//
//    public void setRefreshing(final boolean isRefreshing) {
//        mSwipeRefreshLayout.post(new Runnable() {
//
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(isRefreshing);
//            }
//        });
//
//    }
//
//
//    public void setRefreshLoadMoreListener(RefreshLoadMoreListener listener) {
//        mPullLoadMoreListener = listener;
//    }
//
//    public void refresh() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//        mExceptView.setVisibility(View.INVISIBLE);
//        if (mPullLoadMoreListener != null) {
//            mPullLoadMoreListener.onRefresh();
//        }
//    }
//
//    /*
// * 无数据
// */
//    public void customExceptView(int drawableId, int exceptStr) {
//        mRecyclerView.setVisibility(View.INVISIBLE);
//        mExceptView.setVisibility(View.VISIBLE);
//        // exceptView(drawableId,exceptStr);
//        exceptIv.setImageResource(drawableId);
//        exceptTv.setText(exceptStr);
//    }
//
//    public void scrollToTop() {
//        mRecyclerView.scrollToPosition(0);
//    }
//
//
//    public void setAdapter(RecyclerView.Adapter adapter) {
//        if (adapter != null) {
//            mRecyclerView.setAdapter(adapter);
//        }
//    }
//
//    public boolean isLoadMore() {
//        return isLoadMore;
//    }
//
//    public void setIsLoadMore(boolean isLoadMore) {
//        this.isLoadMore = isLoadMore;
//    }
//
//    public boolean isRefresh() {
//        return isRefresh;
//    }
//
//    public void setIsRefresh(boolean isRefresh) {
//        this.isRefresh = isRefresh;
//    }
//
//    public boolean isHasMore() {
//        return hasMore;
//    }
//
//    public void setHasMore(boolean hasMore) {
//        this.hasMore = hasMore;
//    }
//
//    public interface RefreshLoadMoreListener {
//        public void onRefresh();
//
//        public void onLoadMore();
//    }
//}