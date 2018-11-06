package com.example.baselibrary;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/04
 * desc: activity 管理类
 * version:1.0
 */
public class ActivityController {

    private static List<Activity> activities = new ArrayList<>();

    /**
     * onCreate 调用
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * onDestroy 调用
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.finish();
    }

    /**
     * 获取栈顶 Activity
     */
    public static Activity getTopActivity() {
        if (!activities.isEmpty()) {
            return activities.get(activities.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * 关闭所有 Activity
     */
    public static void finishAllActivity() {
        if (activities.isEmpty()) {
            return;
        }

        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}










