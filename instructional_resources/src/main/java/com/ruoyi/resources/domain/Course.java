package com.ruoyi.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程管理对象 course
 *
 * @author tans
 * @date 2024-02-27
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    private String ids;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private String category;

    /** 主题图片 */
    @Excel(name = "主题图片")
    private String subjectUrl;

    /** 标签列表 */
    @Excel(name = "标签列表")
    private String tags;

    /** 描述 */
    @Excel(name = "描述")
    private String describes;

    public void setId(Long id)
    {
        this.id = id;
    }

    private String channel;// 查看课程的渠道 0:课程管理 1:学习中心我的课程

    private Long refUsersCount; // 课程关联的用户数量

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setSubjectUrl(String subjectUrl)
    {
        this.subjectUrl = subjectUrl;
    }

    public String getSubjectUrl()
    {
        return subjectUrl;
    }
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getRefUsersCount() {
        return refUsersCount;
    }

    public void setRefUsersCount(Long refUsersCount) {
        this.refUsersCount = refUsersCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("category", getCategory())
            .append("subjectUrl", getSubjectUrl())
            .append("tags", getTags())
            .append("describes", getDescribes())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
