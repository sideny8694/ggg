package com.ruoyi.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源管理对象 resources
 *
 * @author tans
 * @date 2024-02-27
 */
public class Resources extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private Long type;

    /** 格式类型 */
    @Excel(name = "格式类型")
    private Long formatType;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private String category;

    /** 主题图片 */
    @Excel(name = "主题图片")
    private String subjectPic;

    /** 资源标题 */
    @Excel(name = "资源标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String describes;

    /** 标签列表 */
    @Excel(name = "标签列表")
    private String tags;

    /** 资源地址 */
    @Excel(name = "资源地址")
    private String url;

    /** 关联资源地址 */
    @Excel(name = "关联资源地址")
    private String refUrl;

    /** 资源内容 */
    @Excel(name = "资源内容")
    private String content;

    /** 被关联数 */
    @Excel(name = "被关联数")
    private Long refs;

    /** 时长 */
    @Excel(name = "时长")
    private Long duration;

    /** 绑定课程 */
    @Excel(name = "绑定课程")
    private String forCourse;

    private Long detailsId;

    public void setId(Long id)
    {
        this.id = id;
    }

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
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setFormatType(Long formatType)
    {
        this.formatType = formatType;
    }

    public Long getFormatType()
    {
        return formatType;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setSubjectPic(String subjectPic)
    {
        this.subjectPic = subjectPic;
    }

    public String getSubjectPic()
    {
        return subjectPic;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setRefUrl(String refUrl)
    {
        this.refUrl = refUrl;
    }

    public String getRefUrl()
    {
        return refUrl;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setRefs(Long refs)
    {
        this.refs = refs;
    }

    public Long getRefs()
    {
        return refs;
    }
    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public Long getDuration()
    {
        return duration;
    }
    public void setForCourse(String forCourse)
    {
        this.forCourse = forCourse;
    }

    public String getForCourse()
    {
        return forCourse;
    }


    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("formatType", getFormatType())
            .append("category", getCategory())
            .append("subjectPic", getSubjectPic())
            .append("title", getTitle())
            .append("describes", getDescribes())
            .append("tags", getTags())
            .append("url", getUrl())
            .append("refUrl", getRefUrl())
            .append("content", getContent())
            .append("refs", getRefs())
            .append("duration", getDuration())
            .append("forCourse", getForCourse())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
