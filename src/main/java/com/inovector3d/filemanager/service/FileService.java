package com.inovector3d.filemanager.service;

import com.inovector3d.filemanager.dto.FileDTO;
import com.inovector3d.filemanager.entities.File;
import com.inovector3d.filemanager.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public Page<FileDTO> findAllPaged(Pageable pageable){
        String userEmail =  (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<File> list = fileRepository.findAllByUserEmail(userEmail,pageable);

        return list.map(x-> new FileDTO(x));
    }

    public Page<FileDTO> findAllByUserEmail(String userEmail, Pageable pageable) {
        Page<File> list = fileRepository.findAllByUserEmail(userEmail,pageable);

        return list.map(x-> new FileDTO(x));
    }
}
