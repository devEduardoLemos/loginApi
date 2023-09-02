package com.inovector3d.filemanager.resources;


import com.inovector3d.filemanager.dto.FileDTO;
import com.inovector3d.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/files")
public class FileResource {
    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<Page<FileDTO>> findAll(Pageable pageable){
        Page<FileDTO> list = fileService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value="/{userEmail}")
    public ResponseEntity<Page<FileDTO>> findAllByUserEmail(@PathVariable String userEmail, Pageable pageable){
        Page<FileDTO> list = fileService.findAllByUserEmail(userEmail, pageable);
        return ResponseEntity.ok().body(list);
    }

 /*   @PostMapping
    public File insert(@RequestBody File file){
        File result = fileRepository.save(file);
        return result;
    }*/


}
