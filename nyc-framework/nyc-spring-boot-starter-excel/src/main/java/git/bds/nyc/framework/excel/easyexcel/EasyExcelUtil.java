package git.bds.nyc.framework.excel.easyexcel;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/8/16 20:52
 */
public class EasyExcelUtil {
    private EasyExcelUtil(){}

    public static <T> List<T> read(MultipartFile file, Class<T> head) throws IOException {
        return EasyExcelFactory.read(file.getInputStream(), head, null)
                // 不要自动关闭，交给 Servlet 自己处理
                .autoCloseStream(false)
                .doReadAllSync();
    }


    /**
     * 多个sheet页的数据链式写入（失败了会返回一个有部分数据的Excel）
     * ExcelUtil.writeWithSheets(response, exportFileName)
     *                 .writeModel(ExcelModel.class, excelModelList, "sheetName1")
     *                 .write(headData, data,"sheetName2")
     *                 .finish();
     * @param response 浏览器输出流
     * @param exportFileName 导出的文件名称
     */
    public static <T> void writeWithSheetsWeb(HttpServletResponse response,String exportFileName,Class<T> clazz,List<T> list) throws IOException {
        String time = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        //添加响应头信息
        response.setHeader("Content-disposition", "attachment; filename=" + exportFileName+"-"+time+".xlsx");
        //设置类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //设置头
        response.setHeader("Pragma", "No-cache");
        //设置头
        response.setHeader("Cache-Control", "no-cache");
        //设置日期头
        response.setDateHeader("Expires", 0);
        List<List<T>> partition = ListUtil.partition(list, 10000);

        // 这里 指定文件
        ExcelWriter excelWriter = EasyExcelFactory.write(response.getOutputStream(), clazz)
                .autoCloseStream(Boolean.FALSE)
                .build();
        // 去调用写入实际使用时根据数据库分页的总的页数来。这里最终会写到sheet里面
        for (int i = 0; i < partition.size(); i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(i, "用户" + i).build();
            excelWriter.write(partition.get(i), writeSheet);
        }
        //刷新流
        excelWriter.finish();
        excelWriter.close();

    }


}
