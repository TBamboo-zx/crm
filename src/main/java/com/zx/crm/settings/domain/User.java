package com.zx.crm.settings.domain;

public class User {
    /*

        关于字符串表现日期及事件
        我们在市场上常用的有两种方式
        日期：年月日
            yyyy--MM--dd 10位字符串  使用char

        日期：年月日时分秒 19位字符串
            yyyy-


    */
    /*
        关于登录
        User user = select * from tbl_user where loginAct=? and loginPwd=?


        user 为空 密码错误
        User 不为空 账号密码正确；
            密码是通过MD5加密的 所以查询的时候也需要先进行加密在比较
            String pwd="123";//用户输入的
            pwd = MD5Util.getMD5(pwd);
            再将进行查询比较
        需要继续向下验证其他的字段信息
        从uer中get到
        其他需要验证的信息
        lockState:验证锁定状态。
            if("0".equals(lockState)){
                账号被锁定。
            }
        expireTime:验证失效时间
             String expireTime = "";
             String currentTime=DataTimeUtil.getSysTime();//工具类
             int count = expireTime.compareTO(currentTime);//只要这个大于零就可以

        allowIps：怎么取得浏览器ip地址 request
               String ip="ljljl";//用户访问后台的ip地址
               String allowIps="";//允许的ip地址群
               if(allowIps.contains(ip){  //String类里的方法  判断字符串是否包含子字符串
                   System.out.println("有效ip地址，允许访问系统")；

               }else{
                   System.out.println("ip地址balabalabala)
               }
    */
  private String  id;//编号  主键
  private String  loginAct;// 登录账号
  private String  name;//姓名
  private String  loginPwd;//登录密码
  private String  email;//邮箱
  private String  expireTime;//失效时间  19
  private String  lockState;//锁定状态 0：锁定 1：启用
  private String  deptno;//部门编号
  private String  allowIps;//允许访问的ip地址
  private String  createTime;//创建时间
  private String  createBy;//创建人
  private String  ditTime;//修改时间  19
  private String  editBy;//修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDitTime() {
        return ditTime;
    }

    public void setDitTime(String ditTime) {
        this.ditTime = ditTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }
}
