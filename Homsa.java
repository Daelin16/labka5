public class Homsa extends Character implements Drawable {
    private int age=23;
    public Homsa(){}
       public Homsa (String name, int height, int width,int weight, Place area) {
           super(name, height, width,weight, area);

try {
        if (height > 100 || width > 75 || height < 10 || width < 5)
            throw new VolumeException("Некорректные данные размера: 10<heigh<100, 5<width<75");
    } catch (VolumeException e) {
        e.printStackTrace();
    }}
    @Override
    public void drow(){
        if (getArea()==Place.WATER)
        System.out.println("sfzhkgckllvj");
        else
            System.out.println("Приглашаю всех на представление");
    }
    public void IzdatZvuk(){
        if (getArea()==Place.ROOF)
            System.out.println("Крыша-лучшее место для рисования афиш");
        else
            if (getWeight()>=60 & getArea()==Place.GARDEN)
                System.out.println("Хочу съесть то яблоко");
        else System.out.println("Наконец-то я закончил!!!");
    }
}

