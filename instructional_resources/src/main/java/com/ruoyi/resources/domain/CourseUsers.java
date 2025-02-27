package com.ruoyi.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程用户管理对象 course_users
 *
 * @author tans
 * @date 2024-02-28
 */
public class CourseUsers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;
    private String courseIdStr;
    // 课程名称
    private String courseName;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long usersId;
    private String usersIdStr;
    // 用户名称
    private String userName;

    private Long[] usersIds;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setUsersId(Long usersId)
    {
        this.usersId = usersId;
    }

    public Long getUsersId()
    {
        return usersId;
    }

    public Long[] getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(Long[] usersIds) {
        this.usersIds = usersIds;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseIdStr() {
        return courseIdStr;
    }

    public void setCourseIdStr(String courseIdStr) {
        this.courseIdStr = courseIdStr;
    }

    public String getUsersIdStr() {
        return usersIdStr;
    }

    public void setUsersIdStr(String usersIdStr) {
        this.usersIdStr = usersIdStr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("usersId", getUsersId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
