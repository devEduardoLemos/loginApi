package com.inovector3d.filemanager.repositories;

import com.inovector3d.filemanager.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {

    List<File> findAllByUserEmail(String userEmail);
}
