package com.ruoyi.project.service.impl;

import com.ruoyi.project.domain.SelfPayReceive;
import com.ruoyi.project.mapper.SelfPayReceiveMapper;
import com.ruoyi.project.service.ISelfPayReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-02
 */
@Service
public class SelfPayReceiveServiceImpl implements ISelfPayReceiveService
{
    @Autowired
    private SelfPayReceiveMapper selfPayReceiveMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param payReceiveId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SelfPayReceive selectSelfPayReceiveByPayReceiveId(String payReceiveId)
    {
        return selfPayReceiveMapper.selectSelfPayReceiveByPayReceiveId(payReceiveId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SelfPayReceive> selectSelfPayReceiveList(SelfPayReceive selfPayReceive)
    {
        return selfPayReceiveMapper.selectSelfPayReceiveList(selfPayReceive);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSelfPayReceive(SelfPayReceive selfPayReceive)
    {
        return selfPayReceiveMapper.insertSelfPayReceive(selfPayReceive);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param selfPayReceive 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSelfPayReceive(SelfPayReceive selfPayReceive)
    {
        return selfPayReceiveMapper.updateSelfPayReceive(selfPayReceive);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param payReceiveIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSelfPayReceiveByPayReceiveIds(String[] payReceiveIds)
    {
        return selfPayReceiveMapper.deleteSelfPayReceiveByPayReceiveIds(payReceiveIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param payReceiveId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSelfPayReceiveByPayReceiveId(String payReceiveId)
    {
        return selfPayReceiveMapper.deleteSelfPayReceiveByPayReceiveId(payReceiveId);
    }
}
