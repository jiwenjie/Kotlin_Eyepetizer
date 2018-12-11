package com.example.root.kotlin_eyepetizer.mvp.more_mvp.more_function_bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/11
 *  desc:豆瓣电影接口使用到的 bean
 *  version:1.0
 */

/**
 * rating : {"max":10,"average":6.9,"stars":"35","min":0}
 * reviews_count : 2440
 * wish_count : 22882
 * douban_site :
 * year : 2016
 * images : {"small":"https://img3.doubanio
 * .com/view/movie_poster_cover/ipst/public/p2378133884.jpg","large":"https://img3.doubanio
 * .com/view/movie_poster_cover/lpst/public/p2378133884.jpg","medium":"https://img3.doubanio
 * .com/view/movie_poster_cover/spst/public/p2378133884.jpg"}
 * （更多信息）alt : https://movie.douban.com/subject/26630781/
 * id : 26630781
 * mobile_url : https://movie.douban.com/subject/26630781/mobile
 * title : 我不是潘金莲
 * do_count : null
 * share_url : https://m.douban.com/movie/subject/26630781
 * seasons_count : null
 * schedule_url : https://movie.douban.com/subject/26630781/cinema/
 * episodes_count : null
 * countries : ["中国大陆"]
 * genres : ["剧情","喜剧"]
 * collect_count : 73047
 * casts : [{"alt":"https://movie.douban.com/celebrity/1050059/",
 * "avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg",
 * "large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3
 * .doubanio.com/img/celebrity/medium/1691.jpg"},"name":"范冰冰","id":"1050059"},
 * {"alt":"https://movie.douban.com/celebrity/1274274/","avatars":{"small":"https://img3
 * .doubanio.com/img/celebrity/small/39703.jpg","large":"https://img3.doubanio
 * .com/img/celebrity/large/39703.jpg","medium":"https://img3.doubanio
 * .com/img/celebrity/medium/39703.jpg"},"name":"郭涛","id":"1274274"},{"alt":"https://movie
 * .douban.com/celebrity/1324043/","avatars":{"small":"https://img3.doubanio
 * .com/img/celebrity/small/58870.jpg","large":"https://img3.doubanio
 * .com/img/celebrity/large/58870.jpg","medium":"https://img3.doubanio
 * .com/img/celebrity/medium/58870.jpg"},"name":"大鹏","id":"1324043"},{"alt":"https://movie
 * .douban.com/celebrity/1275044/","avatars":{"small":"https://img3.doubanio
 * .com/img/celebrity/small/28615.jpg","large":"https://img3.doubanio
 * .com/img/celebrity/large/28615.jpg","medium":"https://img3.doubanio
 * .com/img/celebrity/medium/28615.jpg"},"name":"张嘉译","id":"1275044"}]
 * current_season : null
 * original_title : 我不是潘金莲
 * summary : 一个普通的农村妇女李雪莲（范冰冰
 * 饰），为了纠正一句话，与上上下下、方方面面打了十年交道。打交道的过程中，她没想到一件事变成了另一件事，接着变成了第三件事。十年过去，她没有把这句话纠正过来，但她饱尝了世间的人情冷暖，悟明白了另外一个道理。李雪莲要纠正的这句话是她前夫说的。她前夫说：你是李雪莲吗？我咋觉得你是潘金莲呢？李雪莲要告诉大家的是：我不是潘金莲。©豆瓣
 * subtype : movie
 * directors : [{"alt":"https://movie.douban.com/celebrity/1274255/",
 * "avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/45667.jpg",
 * "large":"https://img1.doubanio.com/img/celebrity/large/45667.jpg","medium":"https://img1
 * .doubanio.com/img/celebrity/medium/45667.jpg"},"name":"冯小刚","id":"1274255"}]
 * comments_count : 36102
 * ratings_count : 68772
 * aka : ["我是李雪莲","我叫李雪莲","I Am Not Madame Bovary"]
 */
data class MovieDetailBean(val rating: RatingBean,
                           val reviews_count: Int,
                           val wish_count: Int,
                           val douban_site: String,
                           val year: String,
                           val images: ImagesBean,
                           val alt: String,
                           val id: String,
                           val mobile_url: String,
                           val title: String,
                           val do_count: Int,
                           val share_url: String,
                           val seasons_count: Int,
                           val schedule_url: String,
                           val episodes_count: Int,
                           val collect_count: Int,
                           val current_season: String,
                           val original_title: String,
                           val summary: String,
                           val subtype: String,
                           val comments_count: Int,
                           val ratings_count: Int,
                           val countries: ArrayList<String>,
                           val genres: ArrayList<String>,
                           val casts: ArrayList<PersonBean>,
                           val directors: ArrayList<PersonBean>,
                           val aka: ArrayList<String>)

/** 豆瓣热映电影，每日更新 **/
data class HotMovieBean(val count: Int,
                        val start: Int,
                        val total: Int,
                        val title: String,
                        val subjects: ArrayList<SubjectsBean>) : Serializable


/**
 * max : 10
 * average : 6.9
 * stars : 35
 * min : 0
 */
data class RatingBean(val max: Int,
                      val average: Double,
                      val stars: String,
                      val min: Int) : Serializable


/**
 * alt : https://movie.douban.com/celebrity/1050059/
 * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg",
 * "large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3
 * .doubanio.com/img/celebrity/medium/1691.jpg"}
 * name : 范冰冰  or
 * name : 冯小刚
 * id : 1050059
 * type: 导演 或 演员
 */
data class PersonBean(val alt: String,
                      val type: String,
                      val avatars: ImagesBean,
                      val name: String,
                      val id: String) : Serializable

/**
 * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2378133884.jpg
 * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2378133884.jpg
 * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2378133884.jpg
 */
data class ImagesBean(val small: String,
                      val large: String,
                      val medium: String) : Serializable


/**
 * rating : {"max":10,"average":6.9,"stars":"35","min":0}
 * genres : ["剧情","喜剧"]
 * title : 我不是潘金莲
 * casts : [{"alt":"https://movie.douban.com/celebrity/1050059/",
 * "avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg",
 * "large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3
 * .doubanio.com/img/celebrity/medium/1691.jpg"},"name":"范冰冰","id":"1050059"},
 * {"alt":"https://movie.douban.com/celebrity/1274274/","avatars":{"small":"https://img3
 * .doubanio.com/img/celebrity/small/39703.jpg","large":"https://img3.doubanio
 * .com/img/celebrity/large/39703.jpg","medium":"https://img3.doubanio
 * .com/img/celebrity/medium/39703.jpg"},"name":"郭涛","id":"1274274"},{"alt":"https://movie
 * .douban.com/celebrity/1324043/","avatars":{"small":"https://img3.doubanio
 * .com/img/celebrity/small/58870.jpg","large":"https://img3.doubanio
 * .com/img/celebrity/large/58870.jpg","medium":"https://img3.doubanio
 * .com/img/celebrity/medium/58870.jpg"},"name":"大鹏","id":"1324043"}]
 * （多少人评分）collect_count : 56325
 * （原名）original_title : 我不是潘金莲
 * subtype : movie
 * directors : [{"alt":"https://movie.douban.com/celebrity/1274255/",
 * "avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/45667.jpg",
 * "large":"https://img1.doubanio.com/img/celebrity/large/45667.jpg","medium":"https://img1
 * .doubanio.com/img/celebrity/medium/45667.jpg"},"name":"冯小刚","id":"1274255"}]
 * year : 2016
 * images : {"small":"https://img3.doubanio
 * .com/view/movie_poster_cover/ipst/public/p2378133884.jpg","large":"https://img3.doubanio
 * .com/view/movie_poster_cover/lpst/public/p2378133884.jpg","medium":"https://img3.doubanio
 * .com/view/movie_poster_cover/spst/public/p2378133884.jpg"}
 * （更多信息）alt : https://movie.douban.com/subject/26630781/
 * id : 26630781
 */
data class SubjectsBean(@SerializedName("rating") val rating: RatingBean,
                        @SerializedName("title") val title: String,
                        @SerializedName("collect_count") val collect_count: Int,
                        @SerializedName("original_title") val original_title: String,
                        @SerializedName("subtype") val subtype: String,
                        @SerializedName("year") val year: String,
                        @SerializedName("images") val images: ImagesBean,
                        @SerializedName("alt") val alt: String,
                        @SerializedName("id") val id: String,
                        @SerializedName("genres") val genres: ArrayList<String>,
                        @SerializedName("casts") val casts: ArrayList<PersonBean>,
                        @SerializedName("directors") val directors: ArrayList<PersonBean>) : Serializable


/********************************** 书籍相关 **************************************/


/**
 * count : 1
 * start : 0
 * total : 200
 * books : [{"rating":{"max":10,"numRaters":116375,"average":"7.9","min":0},"subtitle":"",
 * "author":["韩寒"],"pubdate":"2010-9","tags":[{"count":38955,"name":"韩寒","title":"韩寒"},
 * {"count":16343,"name":"小说","title":"小说"},{"count":12037,"name":"我想和这个世界谈谈",
 * "title":"我想和这个世界谈谈"},{"count":10674,"name":"公路小说","title":"公路小说"},{"count":6392,
 * "name":"1988","title":"1988"},{"count":5868,"name":"中国文学","title":"中国文学"},{"count":5260,
 * "name":"中国","title":"中国"},{"count":4730,"name":"文学","title":"文学"}],"origin_title":"",
 * "image":"https://img5.doubanio.com/mpic/s4477716.jpg","binding":"平装","translator":[],
 * "catalog":"曹操与刘备的一生","pages":"215","images":{"small":"https://img5.doubanio
 * .com/spic/s4477716.jpg","large":"https://img5.doubanio.com/lpic/s4477716.jpg",
 * "medium":"https://img5.doubanio.com/mpic/s4477716.jpg"},"alt":"https://book.douban
 * .com/subject/5275059/","id":"5275059","publisher":"国际文化出版公司","isbn10":"751250098X",
 * "isbn13":"9787512500983","title":"1988：我想和这个世界谈谈","url":"https://api.douban
 * .com/v2/book/5275059","alt_title":"","author_intro":"韩寒
 * 1982年9月23日出生。作家，赛车手。已出版作品：《三重门》、《零下一度》、《像少年啦飞驰》、《通稿2003
 * 》、《毒》、《韩寒五年文集》、《长安乱》、《就这么漂来漂去》、《一座城池》、《寒》、《光荣日》、《杂的文》或有其他。",
 * "summary":"系列主题：《我想和这个世界谈谈》\n目前在韩寒主编的杂志《独唱团》中首度连载，这是韩寒预谋已久的一个系列，也是国内首度实际尝试\u201c公路小说\u201d
 * 这一概念的第一本\u2014\u2014《1988》。\n所谓\u201c公路小说\u201d就是以路途为载体反应人生观，现实观的小说。\n
 * 如果说一件真正的艺术品的面世具有任何重大意义的话，韩寒新书的出版将会在中国创造一个历史事件，文章开头\u201c空气越来越差，我必须上路了。我开着一台1988
 * 年出厂的旅行车，在说不清是迷雾还是毒气的夜色里拐上了318国道。\u201d
 * 用一部旅行车为载体，通过在路上的见闻、过去的回忆、扑朔迷离的人物关系等各种现实场景，以韩寒本人对路上所见、所闻引发自己的观点，这场真正的旅途在精神层面；如果说似乎逾越了部分法律和道德的界限，但出发点也仅仅是希望在另一侧找到信仰。韩寒是\u201c叛逆的\u201d，他\u201c试图用能给世界一些新意的眼光来看世界。试图寻找令人信服的价值\u201d。他认为这一切通过文学都可以实现，产生了要创造一种批判现有一切社会习俗的\u201c新幻象\u201d的念头\u2014\u2014《1988》就此问世。\n目前\u201c公路小说\u201d的系列已经开始策划，韩寒的作品首当其冲，韩寒表示将会撰写三部作品与聚石文华联合打造\u201c公路小说\u201d这一品牌","price":"25.00元"}]
 */
data class BookListBean(val count: Int,
                        val start: Int,
                        val total: Int,
                        val books: ArrayList<BookItemBean>): Serializable


/**
 * rating : {"max":10,"numRaters":72,"average":"7.3","min":0}
 * subtitle : VAN LOON'S GEOGRAPHY
 * author : ["（美）房龙　著，王希发　译"]
 * pubdate : 2008-9
 * tags : [{"count":33,"name":"地理","title":"地理"},{"count":24,"name":"房龙","title":"房龙"},
 * {"count":8,"name":"世界地理","title":"世界地理"},{"count":6,"name":"综合","title":"综合"},{"count":4,
 * "name":"世界","title":"世界"},{"count":4,"name":"科普","title":"科普"},{"count":4,"name":"社会学",
 * "title":"社会学"},{"count":3,"name":"人文","title":"人文"}]
 * origin_title :
 * image : https://img3.doubanio.com/mpic/s9007440.jpg
 * binding :
 * translator : ["王希发"]
 * catalog :
 * 一　人类与家园二　什么是“地理学”三　地球的特点、规律和状况四　地图：万水千山寻路难五　地球有四季六　海洋中的大陆七　发现欧洲八　希腊：连接古老亚洲和新兴欧洲的桥梁九　意大利：地理造就的海上霸主或陆上强国十　西班牙：非洲与欧洲交锋之地十一　法国：应有尽有的国家十二　比利时：几页文件决定了它的命运十三  卢森堡：遭遇历史的捉弄十四  瑞士：四个语言不同的民族和睦相处十五　德国：建国太迟的国家十六  奥地利：无人喝彩的国家十七  丹麦：小国在某些方面胜过大国的典范十八　冰岛：北冰洋上一个有趣的政治实验室十九　斯堪的纳维亚半岛：瑞典王国和挪威王国的属地二十　荷兰：沼泽上崛起的帝国二十一  英国：小小岛国人满为患二十二  俄罗斯：欧洲之国还是亚洲之国二十三  波兰：自家的土地别人的走廊二十四  捷克斯洛伐克：《凡尔赛和约》的果实二十五  南斯拉夫：《凡尔赛和约》的另一件作品二十六  保加利亚：最正统的巴尔干国家二十七  罗马尼亚：一个有石油有王室的国家二十八  匈牙利：或者匈牙利的残余二十九　芬兰：勤劳和智慧战胜恶劣环境的又一明证三十　发现亚洲三十一　亚洲与世界三十二  亚洲中部高原三十三　亚洲西部高原三十四　阿拉伯三十五  印度：人和自然相互促进，共同发展三十六  亚洲南部半岛的主人三十七　中国：东亚大半岛三十八　朝鲜与蒙古：前途未卜三十九　日本：野心勃勃的岛国四十　菲律宾：原墨西哥的领地四十一　荷属东印度群岛：小人物掌大权四十二  澳大利亚：造物主的随意之作四十三　新西兰：珊瑚岛屿的王国四十四  太平洋群岛：不耕不织，照样生活四十五  非洲：矛盾重重的大陆四十六　美洲：最幸福的大陆四十七　创造新世界
 * pages : 308
 * images : {"small":"https://img3.doubanio.com/spic/s9007440.jpg","large":"https://img3
 * .doubanio.com/lpic/s9007440.jpg","medium":"https://img3.doubanio.com/mpic/s9007440.jpg"}
 * alt : https://book.douban.com/subject/3235564/
 * id : 3235564
 * publisher : 北京出版社
 * isbn10 : 7200073261
 * isbn13 : 9787200073263
 * title : 地理的故事
 * url : https://api.douban.com/v2/book/3235564
 * alt_title :
 * author_intro :
 * 房龙，荷裔美国人。他是一位才艺卓绝的博学之士。房龙的人生经历异常丰富，曾经从事过各种各样的工作，先后当过教师、编辑、记者和播音员。他一生创作了大量饮誉世界的作品。在写作中，他善于运用生动活泼的文字，撰写通俗易懂的历史著作。自20世纪20年代起，凡是他发表的作品，都在美国畅销一空，并被译成多种文字在世界各国出版发行，深受各国年轻读者的喜爱。在他众多的畅销书中，就包括这部独树一帜的地理学著作--《地理的故事》。瓣 房龙的这部著作保持了其惯有的行文风格。他用诙谐幽默的文字把枯燥的地理知识描述得活灵活现，使读者在轻松愉快之际不仅了解了人类漫长历史发展的来龙去脉，且能在掩卷之后获得不少启发。世界地理在房龙的笔下，既非气象风云的亘古变迁，也非沧海桑田的物换星移。他所写的地理，是一部有血有肉的“人的”地理。因为他坚信，世界上任何一块土地的重要性都取决于这块土地上的人民以科学、商业、宗教或某种艺术形式为全人类的幸福所作出的或大或小的贡献。
 * 为什么丹麦人偏好静谧的书斋，而西班牙人则热衷于广阔的天地?为什么日本总是千方百计想要扩张，而瑞士则想方设法追求中立?为什么亚洲国家总是安于现状，而欧洲国家却总是强调改革?一个国家的民族性格和历史发展与其地理因素究竟有何关联?房龙在这部书中给出了他自己的答案。
 * 房龙在本书中摒弃了枯燥乏味的科普说教和传统填鸭式的内容灌输，而是以一种清新活泼的方式讲述世界地理知识，从而激发读者的阅读兴趣，让地理知识变得生动有趣。与此同时，他在书中对一部分国家的地理环境进行了浓重的描述，并从中分析出地理对一个国家的历史演变和一个民族的性格形成所产生的影响。
 * summary :
 * 沧海桑田、物换星移，几度风雨、几度春秋，地理变迁永无止歇。然而，这变迁展现的仅仅是一种自风情吗？当然不是。在房龙的笔下，世界地理远非如此，它是一部有血有肉的“人的”地理。在这部地理学著作中，房龙以幽默睿智的文风，用一个个小故事，将每个国家的民族性格、历史发展与地理环境的关联娓娓而来，为读者打开了从另一个角度看世界的窗户，使枯燥的地理知识不再乏味。跟随着这位伟大的文化传播者和出色的通俗读物作家的笔触，读者既能轻松愉快地了解人类漫长历史的来龙去脉，也会在掩卷之后回味沉思，久久不忍释卷。
 * --------------------------------------------------------------------------------
 * 一人类与家园
 * 二什么是“地理学”
 * 三地球的特点、规律和状况
 * 四地图：万水千山寻路难
 * 五地球有四季
 * 六海洋中的大陆
 * 七发现欧洲
 * 八希腊：连接古老亚洲和新兴欧洲的桥梁
 * 九意大利：地理造就的海上霸主或陆上强国
 * 十西班牙：非洲与欧洲交锋之地
 * 十一法国：应有尽有的国家
 * 十二比利时：几页文件决定了它的命运
 * 十三卢森堡：遭遇历史的捉弄
 * 十四瑞士：四个语言不同的民族和睦相处
 * 十五德国：建国太迟的国家
 * 十六奥地利：无人喝彩的国家
 * 十七丹麦：小国在某些方面胜过大国的典范
 * 十八冰岛：北冰洋上一个有趣的政治实验室
 * 十九斯堪的纳维亚半岛：瑞典王国和挪威王国的属地
 * 二十荷兰：沼泽上崛起的帝国
 * 二十一英国：小小岛国人满为患
 * 二十二俄罗斯：欧洲之国还是亚洲之国
 * 二十三波兰：自家的土地别人的走廊
 * 二十四捷克斯洛伐克：《凡尔赛和约》的果实
 * 二十五南斯拉夫：《凡尔赛和约》的另一件作品
 * 二十六保加利亚：最正统的巴尔干国家
 * 二十七罗马尼亚：一个有石油有王室的国家
 * 二十八匈牙利：或者匈牙利的残余
 * 二十九芬兰：勤劳和智慧战胜恶劣环境的又一明证
 * 三十发现亚洲
 * 三十一亚洲与世界
 * 三十二亚洲中部高原
 * 三十三亚洲西部高原
 * 三十四阿拉伯
 * 三十五印度：人和自然相互促进，共同发展
 * 三十六亚洲南部半岛的主人
 * 三十七中国：东亚大半岛
 * 三十八朝鲜与蒙古：前途未
 * 三十九日本：野心勃勃的岛国
 * 四十菲律宾：原墨西哥的领地
 * 四十一荷属东印度群岛：小人物掌大权
 * 四十二澳大利亚：造物主的随意之作
 * 四十三新西兰：珊瑚岛屿的王国
 * 四十四太平洋群岛：不耕不织，照样生活
 * 四十五非洲：矛盾重重的大陆
 * 四十六美洲：最幸福的大陆
 * 四十七创措新世界
 * price : 23.90元
 */
data class BookDetailBean(@SerializedName("rating") val rating: RatingBean,
                          @SerializedName("subtitle") val subtitle: String,
                          @SerializedName("pubdate") val pubdate: String,
                          @SerializedName("origin_title") val origin_title: String,
                          @SerializedName("image") val image: String,
                          @SerializedName("binding") val binding: String,
                          @SerializedName("catalog") val catalog: String,
                          @SerializedName("pages") val pages: String,
                          @SerializedName("images") val images: ImagesBean,
                          @SerializedName("alt") val alt: String,
                          @SerializedName("id") val id: String,
                          @SerializedName("publisher") val publisher: String,
                          @SerializedName("isbn10") val isbn10: String,
                          @SerializedName("isbn13") val isbn13: String,
                          @SerializedName("title") val title: String,
                          @SerializedName("url") val url: String,
                          @SerializedName("alt_title") val alt_title: String,
                          @SerializedName("author_intro") val author_intro: String,
                          @SerializedName("summary") val summary: String,
                          @SerializedName("price") val price: String,
                          @SerializedName("author") val author: ArrayList<String>,
                          @SerializedName("tags") val tags: ArrayList<BookItemBean.TagsBean>,
                          @SerializedName("translator") val translator: ArrayList<String>): Serializable

/**
 * rating : {"max":10,"numRaters":116375,"average":"7.9","min":0}
 * subtitle :
 * author : ["韩寒"]
 * pubdate : 2010-9
 * tags : [{"count":38955,"name":"韩寒","title":"韩寒"},{"count":16343,"name":"小说","title":"小说"},
 * {"count":12037,"name":"我想和这个世界谈谈","title":"我想和这个世界谈谈"},{"count":10674,"name":"公路小说",
 * "title":"公路小说"},{"count":6392,"name":"1988","title":"1988"},{"count":5868,"name":"中国文学",
 * "title":"中国文学"},{"count":5260,"name":"中国","title":"中国"},{"count":4730,"name":"文学",
 * "title":"文学"}]
 * origin_title :
 * image : https://img5.doubanio.com/mpic/s4477716.jpg
 * binding : 平装
 * translator : []
 * catalog : 曹操与刘备的一生
 * pages : 215
 * images : {"small":"https://img5.doubanio.com/spic/s4477716.jpg","large":"https://img5
 * .doubanio.com/lpic/s4477716.jpg","medium":"https://img5.doubanio.com/mpic/s4477716.jpg"}
 * alt : https://book.douban.com/subject/5275059/
 * id : 5275059
 * publisher : 国际文化出版公司
 * isbn10 : 751250098X
 * isbn13 : 9787512500983
 * title : 1988：我想和这个世界谈谈
 * url : https://api.douban.com/v2/book/5275059
 * alt_title :
 * author_intro : 韩寒 1982年9月23日出生。作家，赛车手。已出版作品：《三重门》、《零下一度》、《像少年啦飞驰》、《通稿2003
 * 》、《毒》、《韩寒五年文集》、《长安乱》、《就这么漂来漂去》、《一座城池》、《寒》、《光荣日》、《杂的文》或有其他。
 * summary : 系列主题：《我想和这个世界谈谈》
 * 目前在韩寒主编的杂志《独唱团》中首度连载，这是韩寒预谋已久的一个系列，也是国内首度实际尝试“公路小说”这一概念的第一本——《1988》。
 * 所谓“公路小说”就是以路途为载体反应人生观，现实观的小说。
 * 如果说一件真正的艺术品的面世具有任何重大意义的话，韩寒新书的出版将会在中国创造一个历史事件，文章开头“空气越来越差，我必须上路了。我开着一台1988
 * 年出厂的旅行车，在说不清是迷雾还是毒气的夜色里拐上了318
 * 国道。”用一部旅行车为载体，通过在路上的见闻、过去的回忆、扑朔迷离的人物关系等各种现实场景，以韩寒本人对路上所见、所闻引发自己的观点，这场真正的旅途在精神层面；如果说似乎逾越了部分法律和道德的界限，但出发点也仅仅是希望在另一侧找到信仰。韩寒是“叛逆的”，他“试图用能给世界一些新意的眼光来看世界。试图寻找令人信服的价值”。他认为这一切通过文学都可以实现，产生了要创造一种批判现有一切社会习俗的“新幻象”的念头——《1988》就此问世。
 * 目前“公路小说”的系列已经开始策划，韩寒的作品首当其冲，韩寒表示将会撰写三部作品与聚石文华联合打造“公路小说”这一品牌
 * price : 25.00元
 */
data class BookItemBean(@SerializedName("rating") val rating: RatingBean,
                        @SerializedName("subtitle") val subtitle: String,
                        @SerializedName("pubdate") val pubdate: String,
                        @SerializedName("origin_title") val origin_title: String,
                        @SerializedName("image") val image: String,
                        @SerializedName("binding") val binding: String,
                        @SerializedName("catalog") val catalog: String,
                        @SerializedName("pages") val pages: String,
                        @SerializedName("images") val images: ImagesBean,
                        @SerializedName("alt") val alt: String,
                        @SerializedName("id") val id: String,
                        @SerializedName("publisher") val publisher: String,
                        @SerializedName("isbn10") val isbn10: String,
                        @SerializedName("isbn13") val isbn13: String,
                        @SerializedName("title") val title: String,
                        @SerializedName("url") val url: String,
                        @SerializedName("alt_title") val alt_title: String,
                        @SerializedName("author_intro") val author_intro: String,
                        @SerializedName("summary") val summary: String,
                        @SerializedName("price") val price: String,
                        @SerializedName("author") val author: ArrayList<String>,
                        @SerializedName("tags") val tags: ArrayList<TagsBean>,
                        @SerializedName("translator") val translator: ArrayList<String>): Serializable {

   /**
    * count : 38955
    * name : 韩寒
    * title : 韩寒
    */
   data class TagsBean(val count: Int,
                       val name: String,
                       val title: String): Serializable
}









