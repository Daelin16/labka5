public class Hemul extends Character implements Readable {
    private int age=23;
    public Hemul (String name, int height, int width,int weight, Place area) {
        super(name, height, width,weight, area);
        try {
        if (height > 100 || width > 75 || height < 10 || width < 5)
            throw new VolumeException("Некорректные данные размера: 10<heigh<100, 5<width<75");
    } catch (VolumeException e) {
        e.printStackTrace();
    }}
   @Override
    public void read() throws AdNotFoundException{

        if(Ads.area==Place.JAIL ) {
            System.out.println("Это послание узникам?");

        }else throw new AdNotFoundException("Читать нечего");}
   }


