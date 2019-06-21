import java.util.List;

public class Mumla extends Character implements Drawable, Comparable<Mumla> {
    //private int age=17;

    public void setTroll(Homsa troll) {
        this.troll = troll;
    }

    public Homsa getTroll() {
        return troll;
    }

    Homsa troll;
    public Mumla (String name, int height, int width,int weight, Place area) {
        super(name, height, width,weight, area);


        try {
            if (height > 100 || width > 75 || height < 10 || width < 5)
                throw new VolumeException("Некорректные данные размера: 10<heigh<100, 5<width<75");
        } catch (VolumeException e) {
            e.printStackTrace();
        }
    }

    public Mumla() {

    }

    @Override
    public int compareTo(Mumla o) {
        return name.compareTo(o.name);
    }
    @Override
    public void drow(){
        System.out.println("Спектакль состоится у нас дома в 11:40, прошу не опаздывайте");
    }
    public void IzdatZvuk(){
        if (getHeight()>getWidth())
            System.out.println("Какая чудесная погода для рисования");
        else
        if (getHeight()<getWidth())
            System.out.println("Я так проголодалась");
    }

    @Override
    public String toString() {
        return "Mumla{" +
                //"age=" + age +
                ", name=" + name +
                ", area=" + area +
                ", height=" + height +
                ", weight=" + weight +
                ", width='" + width + '\'' +
                ", troll=" + troll +
                '}';
    }
}
