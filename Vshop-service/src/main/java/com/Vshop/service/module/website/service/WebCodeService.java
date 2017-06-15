package com.Vshop.service.module.website.service;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.WebCode;
import com.Vshop.core.entity.vo.FloorVo;
import com.Vshop.service.module.website.vo.BannerVo;
import com.Vshop.service.module.website.vo.FaceVo;

/**
 * @author llf
 * @Package com.Vshop.service.module.website.service
 * @Description:
 * @date 2014/12/16 14:25
 */
public interface WebCodeService {

    /**
     * 保存
     * @param floorVo
     */
    void save(FloorVo floorVo);

    /**
     * 查询所有
     * @return
     */
    List<WebCode> queryAll();

    /**
     * 查询单条
     * @param id
     * @return
     */
    FloorVo queryById(int id);

    /**
     * 修改
     * @param floorVo
     */
    void update(FloorVo floorVo, int id);

    /**
     * 删除
     * @param id
     */
    void delete(int id);


    void saveBanner(List<BannerVo> bannerList, List<BannerVo> recommendList);

    void updateBanner(FaceVo vo, int id);

    Map<String,List<BannerVo>> queryBannerById(int id);

    void saveBrand(String[] brandIds);
    
    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public List<WebCode> queryAllByType(String type);

    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public WebCode queryNewByType(String type);
}
