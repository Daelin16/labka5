public class Birds extends Character implements flyable {
    private int amount=6;
    public Birds (String name, int height, int width,int weight, Place area) {
        super(name, height, width,weight, area);
        try {
            if (height > 100 || width > 75 || height < 10 || width < 5)
                throw new VolumeException("Некорректные данные размера: 10<heigh<100, 5<width<75");
        } catch (VolumeException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void fly(){
        if (getArea()==Place.GARDEN)
        System.out.print("Соловей летает над "+ getArea());
        if (getArea()==Place.WATER)
        System.out.print("Чайка летает над "+ getArea());
        if (getArea()==Place.GRASSLAND)
        System.out.print("ОРЕЛ летает над"+ getArea());
        if (getArea()==Place.SHORE)
        System.out.print("БАКЛАН летает над "+ getArea());
        if (getArea()==Place.ROOF)
        System.out.print("Воробiй летает над "+ getArea());
        if (getArea()==Place.FOREST)
        System.out.print("ДЯТЕЛ летает над "+ getArea());
    }
    public  void throwing(){
        if (getArea()==Place.ROOF)
            System.out.println(" сбрасывает "+ getWeight()+" афишек");
        if (getArea()==Place.WATER)
            System.out.println(" сбрасывает "+ getWidth()+" афишек");
        if (getArea()==Place.GARDEN)
            System.out.println(" сбрасывает "+ getHeight()+" афишек");
        if (getArea()== Place.GRASSLAND)
            System.out.println(" сбрасывает 4 афишек");
        if (getArea()== Place.SHORE)
            System.out.println(" сбрасывает 5 афишек");
        if (getArea()== Place.FOREST)
            System.out.println(" сбрасывает 3 афишек");



    }
    public void sing(){
        class Melodia{
            private boolean melodichnost;
            public void sound(boolean melodichnost){
                this.melodichnost =melodichnost;
                if (melodichnost==true) {
                System.out.println("Какая прекрасная мелодия");
                }else System.out.println("Отвратные завывания");
            }
        }
        if (getArea()==Place.GARDEN)
        System.out.println("Чуи-чуи");
        if (getArea()==Place.WATER)
            System.out.println("АРгххх");
        if (getArea()==Place.GRASSLAND)
            System.out.println("*звуки орла*");
        if (getArea()==Place.SHORE)
            System.out.println("*звуки баклана*");
        if (getArea()==Place.ROOF)
            System.out.println("Чик-чирик");
        if (getArea()==Place.FOREST)
            System.out.println("ВУДИ вудпекер");

    }
}

