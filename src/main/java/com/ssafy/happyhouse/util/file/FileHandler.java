package com.ssafy.happyhouse.util.file;

import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileHandler {

    public List<RealtyPicture> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception{
        List<RealtyPicture> fileList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(multipartFiles)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = now.format(dateTimeFormatter);


            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            String path = "images" + File.separator + currentDate;
            File file = new File(path);

            if (!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                if (!wasSuccessful) {
                    log.info("file : was not successful");
                }
                for (MultipartFile multipartFile : multipartFiles) {

                    String originFileExtension;
                    String contentType = multipartFile.getContentType();

                    if (ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        if (contentType.contains("image/jpeg"))
                            originFileExtension = ".jpg";
                        else if (contentType.contains("image/png"))
                            originFileExtension = ".png";
                        else
                            break;
                    }

                    String newFileName = System.nanoTime() + originFileExtension;

                    file = new File(absolutePath + path + File.separator + newFileName);
                    multipartFile.transferTo(file);

                    file.setWritable(true);
                    file.setWritable(true);
                }
            }
        }
        return fileList;
    }
}
