package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPlatformMapper;
import com.ruoyi.system.domain.SysPlatform;
import com.ruoyi.system.service.ISysPlatformService;

/**
 * 系统平台Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-28
 */
@Service
public class SysPlatformServiceImpl implements ISysPlatformService 
{
    @Autowired
    private SysPlatformMapper sysPlatformMapper;

    /**
     * 查询系统平台
     * 
     * @param platformId 系统平台主键
     * @return 系统平台
     */
    @Override
    public SysPlatform selectSysPlatformByPlatformId(Integer platformId)
    {
        return sysPlatformMapper.selectSysPlatformByPlatformId(platformId);
    }

    /**
     * 查询系统平台列表
     * 
     * @param sysPlatform 系统平台
     * @return 系统平台
     */
    @Override
    public List<SysPlatform> selectSysPlatformList(SysPlatform sysPlatform)
    {
        return sysPlatformMapper.selectSysPlatformList(sysPlatform);
    }

    /**
     * 新增系统平台
     * 
     * @param sysPlatform 系统平台
     * @return 结果
     */
    @Override
    public int insertSysPlatform(SysPlatform sysPlatform)
    {
        sysPlatform.setCreateTime(DateUtils.getNowDate());
        return sysPlatformMapper.insertSysPlatform(sysPlatform);
    }

    /**
     * 修改系统平台
     * 
     * @param sysPlatform 系统平台
     * @return 结果
     */
    @Override
    public int updateSysPlatform(SysPlatform sysPlatform)
    {
        sysPlatform.setUpdateTime(DateUtils.getNowDate());
        return sysPlatformMapper.updateSysPlatform(sysPlatform);
    }

    /**
     * 批量删除系统平台
     * 
     * @param platformIds 需要删除的系统平台主键
     * @return 结果
     */
    @Override
    public int deleteSysPlatformByPlatformIds(Integer[] platformIds)
    {
        return sysPlatformMapper.deleteSysPlatformByPlatformIds(platformIds);
    }

    /**
     * 删除系统平台信息
     * 
     * @param platformId 系统平台主键
     * @return 结果
     */
    @Override
    public int deleteSysPlatformByPlatformId(Integer platformId)
    {
        return sysPlatformMapper.deleteSysPlatformByPlatformId(platformId);
    }
}
