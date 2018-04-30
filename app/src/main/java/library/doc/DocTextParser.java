package library.doc;

import org.textmining.text.extraction.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2018/5/1 0001.
 */

public class DocTextParser {
    public static String readWord(String file) {
        // 创建输入流用来读取doc文件
        FileInputStream in;
        String text = null;
        try {
            in = new FileInputStream(new File(file));
            WordExtractor extractor = null;
            // 创建WordExtractor
            extractor = new WordExtractor();
            // 进行提取对doc文件
            text = extractor.extractText(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
