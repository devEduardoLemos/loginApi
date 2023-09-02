package com.inovector3d.filemanager.dto;

import com.inovector3d.filemanager.entities.File;
import com.inovector3d.filemanager.entities.Folder;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.Instant;

public class FileDTO implements Serializable {

    private Long id;
    private String userEmail;
    private String fileName;
    private String realName;
    private Double fileSize;
    private String fileType;
    private Instant createdOn;
    private Folder folder;

    public FileDTO(){

    }

    public FileDTO(Long id, String userEmail, String fileName, String realName, Double fileSize, String fileType, Instant createdOn, Folder folder) {
        this.id = id;
        this.userEmail = userEmail;
        this.fileName = fileName;
        this.realName = realName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.createdOn = createdOn;
        this.folder = folder;
    }

    public FileDTO(File entity){
        this.id = entity.getId();;
        this.userEmail = entity.getUserEmail();;
        this.fileName = entity.getFileName();
        this.realName = entity.getRealName();
        this.fileSize = entity.getFileSize();
        this.fileType = entity.getFileType();
        this.createdOn = entity.getCreatedOn();
        this.folder = entity.getFolder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
