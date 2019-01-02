package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.ReviewDAO;
import com.xxx.model.base.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 客户回访业务逻辑类
 */
@Service
public class ReviewService extends BaseService<Review> {

    @Resource
    private ReviewDAO reviewDAO;

    /** 保存 */
    @Transactional
    public void save(Review review) {
        if(review.getId() == null){
            this.insert(review);
        }else{
            this.updateParams(review);
        }
    }

    public void add(Review review){
        reviewDAO.insert(review);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page,Review review){
        return page.setRecords(reviewDAO.findList(page, review));
    }

    public Map<String,Object> getReviewById(int id){
        return reviewDAO.getReviewById(id);
    }
}