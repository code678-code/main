package test;

public class ImgPojo {
    private String id;
    private String src;

    public ImgPojo(){}

    public ImgPojo(String id, String src) {
        this.id = id;
        this.src = src;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "ImgPojo{" +
                "id='" + id + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
