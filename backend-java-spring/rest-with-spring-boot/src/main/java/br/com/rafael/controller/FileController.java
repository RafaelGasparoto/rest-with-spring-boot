package br.com.rafael.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import br.com.rafael.models.UploadFileResponse;
import br.com.rafael.services.FileStorageService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        var filename = this.fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/v1/uploadFile/")
                .path(filename).toUriString();

        return new UploadFileResponse(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

}
