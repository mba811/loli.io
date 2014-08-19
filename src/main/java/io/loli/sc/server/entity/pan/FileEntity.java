package io.loli.sc.server.entity.pan;

import io.loli.sc.server.entity.StorageBucket;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "storage_file")
@Entity
public class FileEntity implements Serializable {

    private static final long serialVersionUID = -2369576302405913467L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "file_name")
    private String originName;

    @Column(name = "new_name")
    private String newName;

    @Column(name = "full_path")
    private String fullPath;

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private StorageBucket storageBucket;

    @Column(name = "file_key")
    private String key;

    @Column
    private String md5;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    // 所属的父文件夹
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FolderEntity folder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public FolderEntity getFolder() {
        return folder;
    }

    public void setFolder(FolderEntity folder) {
        this.folder = folder;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public StorageBucket getStorageBucket() {
        return storageBucket;
    }

    public void setStorageBucket(StorageBucket storageBucket) {
        this.storageBucket = storageBucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}