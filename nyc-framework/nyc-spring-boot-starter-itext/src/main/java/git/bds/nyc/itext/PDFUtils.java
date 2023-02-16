package git.bds.nyc.itext;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 成大事
 * @since 2023/1/1 15:30
 */
public class PDFUtils {
    private PDFUtils(){

    }
    /**
     * 根据PDF模版生成PDF文件
     *
     * @param templateFilePath PDF模版文件路径
     * @param formFlattening   false：生成后的PDF文件表单域仍然可编辑 true：生成后的PDF文件表单域不可编辑
     * @param pdfFilePath      生成PDF的文件路径
     * @param pdfData          pdf数据
     * @param imageData        图像数据
     * @throws Exception 例外
     */
    public static void createPDF(String templateFilePath, PdfData pdfData, Map<String,String> imageData,
                                  boolean formFlattening, String pdfFilePath) throws Exception{
        PdfReader reader = null;
        ByteArrayOutputStream bos = null;
        PdfStamper pdfStamper;
        FileOutputStream fos = null;
        try{
            // 读取PDF模版文件
            reader = new PdfReader(templateFilePath);
            // 输出流
            bos = new ByteArrayOutputStream();
            // 构建PDF对象
            pdfStamper = new PdfStamper(reader, bos);

            // 获取表单数据
            AcroFields form = pdfStamper.getAcroFields();

            // 使用中文字体 使用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体 Adobe 宋体 std L
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(bfChinese);

            Map<String, Object> data = objectToMap(pdfData);

            // 表单赋值
            for(String key : data.keySet()){
                form.setField(key,String.valueOf(data.get(key)));
                // 也可以指定字体
                form.setFieldProperty(key, "textfont", bfChinese, null);
            }

            // 添加图片
            if(null != imageData && imageData.size() > 0){
                for(String key : imageData.keySet()){
                    int pageNo = form.getFieldPositions(key).get(0).page;
                    Rectangle signRect = form.getFieldPositions(key).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    // 读图片
                    Image image = Image.getInstance(imageData.get(key));
                    // 获取操作的页面
                    PdfContentByte under = pdfStamper.getOverContent(pageNo);
                    // 根据域的大小缩放图片
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    // 添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                }
            }

            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.setFormFlattening(formFlattening);
            pdfStamper.close();

            // 保存文件
            fos = new FileOutputStream(pdfFilePath);
            fos.write(bos.toByteArray());
            fos.flush();
        }finally {
            if(null != fos){
                try {fos.close(); }catch (Exception e){e.printStackTrace();}
            }

            if(null != bos){
                try {bos.close(); }catch (Exception e){e.printStackTrace();}
            }

            if(null != reader){
                try {reader.close(); }catch (Exception e){e.printStackTrace();}
            }
        }
    }


    /**
     * 对象转map
     *
     * @param t            吨
     * @param ignoreFields 忽略字段
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public static <T> Map<String, Object> objectToMap(T t, String... ignoreFields) {
        Map<String, Object> map = new HashMap<>(15);
        Field[] declaredFields = t.getClass().getDeclaredFields();
        List<String> ignoreFieldList = Arrays.asList(ignoreFields);
        Arrays.stream(declaredFields).forEach(data -> {
            data.setAccessible(true);
            try {
                if (ignoreFieldList.isEmpty() || !ignoreFieldList.contains(data.getName())) {
                    map.put(data.getName(), data.get(t));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

}
