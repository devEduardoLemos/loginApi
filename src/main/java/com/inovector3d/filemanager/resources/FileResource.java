package com.inovector3d.filemanager.resources;


import com.inovector3d.filemanager.entities.File;
import com.inovector3d.filemanager.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/files")
public class FileResource {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping
    public List<File> findAll(){
        List<File> result = fileRepository.findAll();
        return result;
    }

    @GetMapping(value="/{userEmail}")
    public List<File> findAllByUserEmail(@PathVariable String userEmail){
        List<File> result = fileRepository.findAllByUserEmail(userEmail);
        return result;
    }

    @PostMapping
    public File insert(@RequestBody File file){
        File result = fileRepository.save(file);
        return result;
    }


}
