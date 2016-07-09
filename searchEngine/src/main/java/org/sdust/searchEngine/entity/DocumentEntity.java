package org.sdust.searchEngine.entity;

/**
 * Created by zzy on 2016/5/15.
 */

public class DocumentEntity {

    private String url;
    private String title;
    private String filePath;
    private String digest;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }


}
