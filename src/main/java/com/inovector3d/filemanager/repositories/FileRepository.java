package com.inovector3d.filemanager.repositories;

import com.inovector3d.filemanager.entities.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    Page<File> findAllByUserEmail(String userEmail, Pageable pageable);

}
