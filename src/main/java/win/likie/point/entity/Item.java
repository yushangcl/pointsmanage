package win.likie.point.entity;

/**
 * Created by huahui.wu on 2017/7/24.
 */
public class Item {
    private Long ukid;
    private String name;
    private Integer age;

    public Long getUkid() {
        return ukid;
    }

    public void setUkid(Long ukid) {
        this.ukid = ukid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (ukid != null ? !ukid.equals(item.ukid) : item.ukid != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return age != null ? age.equals(item.age) : item.age == null;
    }

    @Override
    public int hashCode() {
        int result = ukid != null ? ukid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ukid=" + ukid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
