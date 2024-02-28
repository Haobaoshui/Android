package com.example.touchassistant;


import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AutoClickAccessibilityService extends AccessibilityService {
    String TAG ="MyTag";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        try {
            //拿到根节点
            AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
            if (rootInfo == null)
                return;
                //开始遍历，这里拎出来细讲，直接往下看正文
            for (int i = 0; i < rootInfo.getChildCount(); i++) {
                    final AccessibilityNodeInfo frameLayoutInfo = rootInfo.getChild(i);
                String classinfo=frameLayoutInfo.getClassName().toString();
                Log.v(TAG,classinfo);
                }
            } catch (Exception e) {
                Log.v(TAG,"Exception:" + e.getMessage());
            }

    }

    @Override
    public void onInterrupt() {

    }
}
