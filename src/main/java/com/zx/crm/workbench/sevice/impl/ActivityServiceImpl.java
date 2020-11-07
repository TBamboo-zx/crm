package com.zx.crm.workbench.sevice.impl;

import com.zx.crm.settings.dao.UserDao;
import com.zx.crm.settings.domain.User;
import com.zx.crm.utils.SqlSessionUtil;
import com.zx.crm.vo.PageList;
import com.zx.crm.workbench.dao.ActivityDao;
import com.zx.crm.workbench.domain.Activity;
import com.zx.crm.workbench.sevice.ActivitySevice;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivitySevice {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    @Override
    public int save(Activity activity) {
        int i = activityDao.save(activity);
        return i ;
    }

    @Override
    public PageList<Activity> getPageList(Map<String, Object> map) {
        List<Activity> activityList=activityDao.getPageList(map);
        int n = activityDao.getPageCount();
        PageList<Activity> activityPageList = new PageList<Activity>();
        activityPageList.setPageList(activityList);
        activityPageList.setCount(n);

        return activityPageList;
    }
}
