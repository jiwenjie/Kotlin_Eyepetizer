package com.example.root.kotlin_eyepetizer.basic.api

import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.BookDetailBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.BookListBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.HotMovieBean
import com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean.MovieDetailBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:豆瓣的 api
 *  version:1.0
 */
interface DoubanApi {

   /**
    * 豆瓣热映电影，每日更新
    */
   @GET("v2/movie/in_theaters")
   fun getHotMovie(): Observable<HotMovieBean>

   /**
    * 获取电影详情
    */
   @GET("v2/movie/subject/{id}")
   fun getMovieDetail(@Path("id") id: String): Observable<MovieDetailBean>

   /**
    * 获取豆瓣电影 top250
    * start 从多少开始，如从 "0" 开始
    * count 一次请求的数目，如 "10" 条，最多 100
    */
   @GET("v2/movie/top250")
   fun getMovieTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<HotMovieBean>

   /**
    * 根据 tag 获取图书
    * tag 搜索关键字
    * start 从多少开始，如从 "0" 开始
    * count 一次请求的数目 最多 100
    */
   @GET("v2/book/search")
   fun getBookListWithTag(@Query("tag") tag: String, @Query("start") start: Int,
                          @Query("count") count: Int): Observable<BookListBean>

   @GET("v2/book/{id}")
   fun getBookDetail(@Path("id") id: String): Observable<BookDetailBean>
}

