package com.expocms.server.db.model;

public class RdDepartment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_department.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_department.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_department.empNum
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Integer empnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rd_department.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    private Long createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_department.ids
     *
     * @return the value of rd_department.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getIds() {
        return ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_department.ids
     *
     * @param ids the value for rd_department.ids
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_department.name
     *
     * @return the value of rd_department.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_department.name
     *
     * @param name the value for rd_department.name
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_department.empNum
     *
     * @return the value of rd_department.empNum
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Integer getEmpnum() {
        return empnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_department.empNum
     *
     * @param empnum the value for rd_department.empNum
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setEmpnum(Integer empnum) {
        this.empnum = empnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rd_department.createTime
     *
     * @return the value of rd_department.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rd_department.createTime
     *
     * @param createtime the value for rd_department.createTime
     *
     * @mbggenerated Sun Sep 13 15:37:00 CST 2015
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}