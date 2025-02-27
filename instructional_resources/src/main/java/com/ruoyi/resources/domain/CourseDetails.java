package com.ruoyi.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程资源管理对象 course_details
 *
 * @author tans
 * @date 2024-02-27
 */
public class CourseDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 父级id */
    @Excel(name = "父级id")
    private Long parentId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    /** 资源id */
    @Excel(name = "资源id")
    private Long resourceId;

    // 资源id数组
    private Long[] resourceIds;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setResourceId(Long resourceId)
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId()
    {
        return resourceId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public Long[] getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Long[] resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("courseId", getCourseId())
            .append("resourceId", getResourceId())
            .append("title", getTitle())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
