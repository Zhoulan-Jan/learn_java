package dao;

public class Project {
    private String name;
    private String url;
    private String description;

    private int starCnt;
    private int forkCnt;
    private int issueCnt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarCnt() {
        return starCnt;
    }

    public void setStarCnt(int starCnt) {
        this.starCnt = starCnt;
    }

    public int getForkCnt() {
        return forkCnt;
    }

    public void setForkCnt(int forkCnt) {
        this.forkCnt = forkCnt;
    }

    public int getIssueCnt() {
        return issueCnt;
    }

    public void setIssueCnt(int issueCnt) {
        this.issueCnt = issueCnt;
    }

    @Override
    public String toString() {
        return "dao.Project{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", starCnt=" + starCnt +
                ", forkCnt=" + forkCnt +
                ", issueCnt=" + issueCnt +
                '}';
    }
}
