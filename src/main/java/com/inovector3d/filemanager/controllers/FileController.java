package com.inovector3d.filemanager.controllers;


import com.inovector3d.filemanager.entities.File;
import com.inovector3d.filemanager.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/files")
public class FileController {

    @Autowired
    private FileRepository files;

    @GetMapping
    public List<File> findAll(){
        List<File> result = files.findAll();
        return result;
    }

    @PostMapping
    public File insert(@RequestBody File file){
        File result = files.save(file);
        return result;
    }
}
