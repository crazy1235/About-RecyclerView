package jacksen.recyclerviewdemo;

/**
 * Created by Admin on 2016/6/15.
 */

public class HeroBean {

    private String imageName;
    private String name;
    private String introduce;

    public HeroBean() {
    }

    public HeroBean(String imageName, String name, String introduce) {
        this.imageName = imageName;
        this.introduce = introduce;
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
