package common;

public class Result {
    private String title;
    private String showUrl;
    private String clickUrl;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "common.Result{" +
                "title='" + title + '\'' +
                ", showUrl='" + showUrl + '\'' +
                ", clickUrl='" + clickUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
