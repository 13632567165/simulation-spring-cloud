package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 系统平台对象 sys_platform
 * 
 * @author ruoyi
 * @date 2024-03-28
 */
public class SysPlatform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer platformId;

    private String platformCode;

    private String platformName;

    private String platformUrl;

    private Integer enable;

    public void setPlatformId(Integer platformId) 
    {
        this.platformId = platformId;
    }

    public Integer getPlatformId() 
    {
        return platformId;
    }
    public void setPlatformCode(String platformCode) 
    {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() 
    {
        return platformCode;
    }
    public void setPlatformName(String platformName) 
    {
        this.platformName = platformName;
    }

    public String getPlatformName() 
    {
        return platformName;
    }
    public void setPlatformUrl(String platformUrl) 
    {
        this.platformUrl = platformUrl;
    }

    public String getPlatformUrl() 
    {
        return platformUrl;
    }
    public void setEnable(Integer enable) 
    {
        this.enable = enable;
    }

    public Integer getEnable() 
    {
        return enable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("platformId", getPlatformId())
            .append("platformCode", getPlatformCode())
            .append("platformName", getPlatformName())
            .append("platformUrl", getPlatformUrl())
            .append("enable", getEnable())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
