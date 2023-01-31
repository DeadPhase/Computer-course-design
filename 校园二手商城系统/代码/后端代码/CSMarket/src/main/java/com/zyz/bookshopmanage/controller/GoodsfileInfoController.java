package com.zyz.bookshopmanage.controller;

import cn.hutool.core.io.FileUtil;
import com.zyz.bookshopmanage.config.response.Result;
import com.zyz.bookshopmanage.mapper.GoodsFileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/11 21:32
 */
@RestController
public class GoodsfileInfoController {
    @Autowired
    GoodsFileInfoMapper gfMapper;

    /**
     * System.getProperty("user.dir") 获取当前的工作目录 虚拟路径
     * E:\workspace\服装库存管理springboot+vue\book-mall-management
     */
    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/file/";
    private static final String VIRTUAL_FILE_PATH = "http://localhost:8282/images/";

//    private static final String ABSOLUTE_FILE_PATH = "E:\\Lenovo\\Documents\\upload\\images\\";

    /**
     * 文件上传 绝对
     * @param file
     * @return
     */
    @RequestMapping(value = "/goodsInfo/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String originName = file.getOriginalFilename();
        // 1、文件名加个时间戳
        String fileName = FileUtil.mainName(originName) + System.currentTimeMillis() + "." + FileUtil.extName(originName);
        System.out.println(fileName);

        // 2、文件存放到项目中的静态资源下
        String storageName = BASE_PATH + fileName;

        // 3. 文件上传
        FileUtil.writeBytes(file.getBytes(),storageName );
        String final_fileName =VIRTUAL_FILE_PATH + fileName;
        return Result.ok().data("fileName",final_fileName);


        /**
         * FileUtil.writeBytes(file.getBytes(), BASE_PATH + fileName);


        System.out.println(fileName);

        GoodsFileInfo goodsFileInfo = new GoodsFileInfo();
        goodsFileInfo.setOriginName(originName);
        goodsFileInfo.setFileName(fileName);
        int result = gfMapper.insert(goodsFileInfo);
        if(result > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("file_name", fileName);
            List<GoodsFileInfo> baseGFile = gfMapper.selectByMap(map);
            if(baseGFile != null){
                return Result.ok().data("baseGFile",baseGFile);
            }else{
                return Result.error().data("errMessage","查询失败");
            }
        }else{
            return Result.error().data("errMessage","文件上传失败");
        }

         */



    }
}
