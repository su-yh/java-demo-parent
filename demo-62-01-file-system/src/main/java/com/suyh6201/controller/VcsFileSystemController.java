package com.suyh6201.controller;

import com.suyh6201.component.VcsFileSystemComponent;
import com.suyh6201.dto.FileStoreLocationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件系统
 *
 * @author suyh
 * @since 2023-12-01
 */
@RestController
@RequestMapping("/vcs/fs")
@RequiredArgsConstructor
@Validated
@Slf4j
public class VcsFileSystemController {

    private final VcsFileSystemComponent vcsFileSystemComponent;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public List<FileStoreLocationDto> httpUploadFile(
            @RequestParam("files") MultipartFile[] files) {
        return vcsFileSystemComponent.filesUpload(files);
    }

    // 存储到系统磁盘文件下载
    @RequestMapping(value = "/file/download/disk/{fileName}", method = RequestMethod.GET)
    public void diskFileDownload(
            HttpServletResponse response,
            @PathVariable("fileName") String fileName) {
        vcsFileSystemComponent.downloadSystemDiskFile(fileName, response);
    }
}
