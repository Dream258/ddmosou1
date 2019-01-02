package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.FeedbackDAO;
import com.xxx.model.base.entity.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 客户反馈业务逻辑类
 */
@Service
public class FeedbackService extends BaseService<Feedback> {

    @Resource
    private FeedbackDAO feedbackDAO;

    /** 保存 */
    @Transactional
    public void save(Feedback feedback) {
        if(feedback.getId() == null){
            this.insert(feedback);
        }else{
            this.updateParams(feedback);
        }
    }

    public void add(Feedback feedback){
        feedback.setCreatetime(new Date());
        feedback.setReply("0");
        feedbackDAO.insert(feedback);
    }

    public void update(int id,String answer){
        Feedback feedback = feedbackDAO.selectById(id);
        feedback.setAnswer(answer);
        feedback.setReplytime(new Date());
        feedback.setReply("1");
        feedbackDAO.updateById(feedback);
    }

    public Page<Map<String,Object>> findList(Page<Map<String,Object>> page,Feedback feedback){
        return page.setRecords(feedbackDAO.findList(page,feedback));
    }

    public Map<String,Object> getFeedbackById(int id){
        return feedbackDAO.getFeedbackById(id);
    }
}