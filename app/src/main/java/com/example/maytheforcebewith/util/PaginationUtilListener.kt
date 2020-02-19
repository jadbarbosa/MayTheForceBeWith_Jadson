package com.example.maytheforcebewith.util

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager



abstract class PaginationUtilListener : AbsListView.OnScrollListener{
    val layoutManager: LinearLayoutManager;


    constructor(layoutManager: LinearLayoutManager){
    this.layoutManager = layoutManager;
    }

    override fun onScroll(
        view: AbsListView?,
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ) {

//        super.onScroll(view, firstVisibleItem, visibleItemCount,totalItemCount)

        val visibleItemCount = layoutManager.getChildCount()
        val totalItemCount = layoutManager.getItemCount()
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= getTotalPageCount()
            ) {
                loadMoreItems()
            }
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    protected abstract fun loadMoreItems()

    abstract fun getTotalPageCount(): Int

    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}