package com.Vshop.service.module.store.dao.impl;
//package com.Vshop.service.module.store.dao.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Repository;
//
//import com.Vshop.core.entity.Brand;
//import com.Vshop.service.module.store.dao.BrandDao;
//import com.Vshop.service.module.store.dao.mapper.BrandMapper;
//import com.Vshop.service.utils.page.Pager;
//
///**
// * 
// * 
// * @项目名称：Vshop-seller
// * @类名称：BrandDaoImpl
// * @类描述：
// * @创建人：shining
// * @创建时间：2014年12月3日 下午6:22:21
// * @修改人：shining
// * @修改时间：2014年12月3日 下午6:22:21
// * @修改备注：
// * @version
// * 
// */
//@Repository
//public class BrandDaoImpl implements BrandDao {
//	@Resource
//	private BrandMapper brandMapper;
//
//	@Override
//	public List<Brand> queryList(Pager pager) {
//		return brandMapper.queryList(pager);
//	}
//
//	@Override
//	public void delete(int id) {
//		brandMapper.delete(id);
//	}
//
//	@Override
//	public void save(Brand brand) {
//		brandMapper.save(brand);
//	}
//
//	@Override
//	public Brand findById(long id) {
//		return brandMapper.findById(id);
//	}
//
//	@Override
//	public void update(Brand brand) {
//		brandMapper.update(brand);
//	}
//
//    /**
//     * 查询条数
//     * @return
//     */
//    @Override
//    public int findCount(Pager pager) {
//        return brandMapper.findCount(pager);
//    }
//}
