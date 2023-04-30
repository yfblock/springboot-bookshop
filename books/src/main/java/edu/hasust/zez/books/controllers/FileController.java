package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @RequestMapping("/upload")
    public ResultCode upload(
            @RequestParam("file") MultipartFile uploadFile,
            HttpServletRequest req
    ) {
        String realPath =
                req.getSession().getServletContext().getRealPath("/api/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        String filePath="";
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            uploadFile.transferTo(new File(folder, newName));
            filePath = req.getScheme() + "://" + req.getServerName() + ":" +
                    req.getServerPort() + "/api/uploadFile/" + format + newName;

        } catch (IOException e) {
            e.printStackTrace();
            return ResultCode.err(RepErrorCode.FILE_UPLOAD_FAILED);
        }
        return ResultCode.ok("上传成功", filePath);
    }
}
