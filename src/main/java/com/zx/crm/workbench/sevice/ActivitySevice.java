package com.zx.crm.workbench.sevice;

import com.zx.crm.settings.domain.User;
import com.zx.crm.vo.PageList;
import com.zx.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivitySevice {

    int save(Activity activity);

    PageList<Activity> getPageList(Map<String, Object> map);
}
