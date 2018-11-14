package com.example.root.kotlin_eyepetizer.bean

import java.io.Serializable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/09
 *  desc: 存放首页所有有关的 bean
 *  version:1.0
 */
data class IndexBean(val issueList: ArrayList<Issue>,
                     val nextPageUrl: String,
                     val nextPublishTime: Long,
                     val newestIssueType: String,
                     val dialog: Any) {

   data class Issue(val releaseTime: Long,
                    val type: String,
                    val date: Long,
                    val publishTime: Long,
                    val itemList: ArrayList<Item>,
                    val count: Int) {

      data class Item(val type: String,
                      val data: Data?,
                      val tag: Any,
                      val id: Int,
                      val adIndex: Int) {

         data class Data(val dataType: String,
                         val id: Long,
                         val title: String,
                         val description: String,
                         val library: String,
                         val tags: ArrayList<Tag>,
                         val consumption: Consumption,
                         val resourceType: String,
                         val slogan: String,
                         val provider: Provider,
                         val category: String,
                         val author: Author,
                         val cover: Cover,
                         val playUrl: String,
                         val thumbPlayUrl: String?,
                         val duration: Long,
                         val webUrl: WebUrl,
                         val releaseTime: Long,
                         val playInfo: ArrayList<PlayInfo>?,
                         val campaign: Any,
                         val waterMarks: Any,
                         val ad: Boolean,
                         val adTrack: Any,
                         val type: String,
                         val titlePgc: Any,
                         val descriptionPgc: Any,
                         val remark: Any,
                         val ifLimitVideo: Boolean,
                         val searchWeight: Int,
                         val idx: Int,
                         val shareAdTrack: Any,
                         val favoriteAdTrack: Any,
                         val webAdTrack: Any,
                         val date: Long,
                         val promotion: Any,
                         val label: Any,
                         val labelList: Any,
                         val descriptionEditor: String,
                         val collected: Boolean,
                         val played: Boolean,
                         val subtitles: Any,
                         val lastViewTime: Any,
                         val playlists: Any,
                         val src: Any): Serializable {

            data class Tag(val id: Int,
                           val name: String,
                           val actionUrl: String,
                           val adTrack: Any,
                           val desc: Any,
                           val bgPicture: String,
                           val headerImage: String,
                           val tagRecType: String,
                           val childTagList: Any,
                           val childTagIdList: Any,
                           val communityIndex: Int)

            data class Consumption(val collectionCount: Int,
                                   val shareCount: Int,
                                   val replyCount: Int)

            data class Provider(val name: String,
                                val alias: String,
                                val icon: String)

            data class Author(val id: Int,
                              val icon: String,
                              val name: String,
                              val description: String,
                              val link: String,
                              val latestReleaseTime: Long,
                              val videoNum: Int,
                              val adTrack: Any,
                              val follow: Follow,
                              val shield: Shield,
                              val approvedNotReadyVideoCount: Int,
                              val ifPgc: Boolean) {

               data class Follow(val itemType: String,
                                 val itemId: Int,
                                 val followed: Boolean)

               data class Shield(val itemType: String,
                                 val itemId: Int,
                                 val shielded: Boolean)
            }

            data class Cover(val feed: String,
                             val detail: String,
                             val blurred: String,
                             val sharing: Any,
                             val homepage: String)

            data class WebUrl(val raw: String,
                              val forWeibo: String)

            data class PlayInfo(val height: Int,
                                val width: Int,
                                val urlList: ArrayList<Url>,
                                val name: String,
                                val type: String,
                                val url: String) {

               data class Url(val name: String,
                              val url: String,
                              val size: Long)
            }
         }
      }
   }

}


