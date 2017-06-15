package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Consult;
import com.Vshop.service.module.trade.dao.ConsultDao;
import com.Vshop.service.module.trade.dao.mapper.ConsultMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@Repository
public class ConsultDaoImpl implements ConsultDao{

    @Resource
    private ConsultMapper consultMapper;
    /**
     * 查询条数
     *
     * @param pager
     * @return
     */
    @Override
    public int findCount(Consult consult) {
        return consultMapper.findCount(consult);
    }

    /**
     * 分页列表
     *
     * @param pager
     * @return
     */
    @Override
    public List<Consult> findList(Pager pager) {
        return consultMapper.findList(pager);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        consultMapper.delete(id);
    }
    
    @Override
    public void updateReply(Consult consult) {
        consultMapper.updateReply(consult);
    }


    @Override
    public Consult findById(int id) {
        return consultMapper.findById(id);
    }

	@Override
	public void save(Consult consult) {
		consultMapper.save(consult);
		
	}

	@Override
	public int findMemberCount(Consult consult) {
		
		return consultMapper.findMemberCount(consult);
	}

	@Override
	public List<Consult> findMemberList(Pager pager) {
		
		return consultMapper.findMemberList(pager);
	}

	@Override
	public Consult findById(Integer id) {
		
		return consultMapper.findById(id);
	}
}
