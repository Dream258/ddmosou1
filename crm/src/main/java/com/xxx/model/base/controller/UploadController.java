package com.xxx.model.base.controller;

import com.xxx.common.interfaces.NotLogin;
import com.xxx.common.util.DateUtils;
import com.xxx.common.util.GlobalValue;
import com.xxx.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NotLogin
@Controller
public class UploadController {
    //文件上传相关代码
    @ResponseBody
    @RequestMapping(value = "upload")
    public Map upload(@RequestParam("file") MultipartFile file, String catalog) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtil.isEmpty(catalog)){
            catalog = "image";
        }
        if (file.isEmpty()) {
            map.put("success","文件为空");
            return map;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = GlobalValue.baseUrl+"upload/"+catalog+"/"+ DateUtils.format(new Date(),DateUtils.YYYY_MM_DD)+"/";
        // 文件访问路径
        String fileUrl = "/upload/"+catalog+"/"+ DateUtils.format(new Date(),DateUtils.YYYY_MM_DD)+"/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        //新建文件
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Map<String, Object> m = new HashMap<>();
            m.put("image",fileUrl+fileName);
            map.put("data",m);
            map.put("success","000");
            map.put("message","请求成功！");
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("success","004");
        map.put("message","上传失败！");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "delFile")
    public Map delFile(@RequestParam String img){
        Map<String, Object> map = new HashMap<>();
        try {
            // 删除以前图片
            if(img!=null&&!"".equals(img)){
                File f = new File(GlobalValue.baseUrl+img);
                if(f.exists()){
                    f.delete();
                }
            }
            map.put("success","000");
            map.put("message","请求成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
