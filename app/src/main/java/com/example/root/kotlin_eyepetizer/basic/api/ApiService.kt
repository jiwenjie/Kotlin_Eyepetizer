package com.example.root.kotlin_eyepetizer.basic.api

import com.example.root.kotlin_eyepetizer.mvp.bean.AuthorInfoBean
import com.example.root.kotlin_eyepetizer.mvp.bean.CategoryBean
import com.example.root.kotlin_eyepetizer.mvp.bean.HomeBean
import com.example.root.kotlin_eyepetizer.mvp.bean.TabInfoBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/15
 *  desc:Api 接口部分
 *  version:1.0
 */
interface ApiService {

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstIndexData(@Query("num") num: Int): Observable<HomeBean>

    /**
     * 根据 nextPageUrl 请求下一个的数据
     */
    @GET
    fun getMoreIndexData(@Url url: String): Observable<HomeBean>

    /**
     * 根据 item id 获取相关的视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Observable<HomeBean.Issue>

    /**
     * 获取分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

    /**
     * 获取分类详情 List
     */
    @GET("v4/categories/videoList?")
    fun getCategoryDetailList(@Query("id") id: Long): Observable<HomeBean.Issue>

    /**
     * 获取更多的 Issue
     */
    @GET
    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>

    /**
     * 获取全部排行榜的 Info (包括 title 和 Url)
     */
    @GET("v4/rankList")
    fun getRankList(): Observable<TabInfoBean>

    /**
     * 获取搜索信息
     */
    @GET("v1/search?&num=10&start=10")
    fun getSearchData(@Query("query") query: String): Observable<HomeBean.Issue>

    /**
     * 热门搜索词
     */
    @GET("v3/queries/hot")
    fun getHotWord(): Observable<ArrayList<String>>

    /**
     * 关注
     */
    @GET("v4/tabs/follow")
    fun getFollowInfo(): Observable<HomeBean.Issue>

    /**
     * 作者信息
     */
    @GET("v4/pgcs/detail/tab?")
    fun getAuthorInfo(@Query("id") id: Long): Observable<AuthorInfoBean>

}



















