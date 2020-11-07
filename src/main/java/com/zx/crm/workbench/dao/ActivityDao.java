package com.zx.crm.workbench.dao;

import com.zx.crm.settings.domain.User;
import com.zx.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {

    int save(Activity activity);

    List<Activity> getPageList(Map<String, Object> map);

    int getPageCount();
}
