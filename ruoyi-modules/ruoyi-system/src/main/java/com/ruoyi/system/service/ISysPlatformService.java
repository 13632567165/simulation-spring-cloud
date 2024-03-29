package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPlatform;

/**
 * 系统平台Service接口
 * 
 * @author ruoyi
 * @date 2024-03-28
 */
public interface ISysPlatformService 
{
    /**
     * 查询系统平台
     * 
     * @param platformId 系统平台主键
     * @return 系统平台
     */
    public SysPlatform selectSysPlatformByPlatformId(Integer platformId);

    /**
     * 查询系统平台列表
     * 
     * @param sysPlatform 系统平台
     * @return 系统平台集合
     */
    public List<SysPlatform> selectSysPlatformList(SysPlatform sysPlatform);

    /**
     * 新增系统平台
     * 
     * @param sysPlatform 系统平台
     * @return 结果
     */
    public int insertSysPlatform(SysPlatform sysPlatform);

    /**
     * 修改系统平台
     * 
     * @param sysPlatform 系统平台
     * @return 结果
     */
    public int updateSysPlatform(SysPlatform sysPlatform);

    /**
     * 批量删除系统平台
     * 
     * @param platformIds 需要删除的系统平台主键集合
     * @return 结果
     */
    public int deleteSysPlatformByPlatformIds(Integer[] platformIds);

    /**
     * 删除系统平台信息
     * 
     * @param platformId 系统平台主键
     * @return 结果
     */
    public int deleteSysPlatformByPlatformId(Integer platformId);
}
