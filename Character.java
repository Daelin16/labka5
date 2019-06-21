public abstract class Character {
public String name;
public double height;
public double width;
public double weight;
public Place area;

public Character(){}
public Character (String name, double height, double width, double weight, Place area){
    this.name=name;
    this.height=height;
    this.width=width;
    this.weight=weight;
    this.area=area;
}
    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Place getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public void setArea(String area) {

            switch (area.toLowerCase()) {
                case "water": {
                    this.area = Place.WATER;
                }
                break;
                case "garden": {
                    this.area = Place.GARDEN;
                }
                break;
                case "shore": {
                    this.area = Place.SHORE;
                }
                break;
                case "jail": {
                    this.area = Place.JAIL;
                }
                break;
                case "roof": {
                    this.area = Place.ROOF;
                }
                break;
                case "grassland": {
                    this.area = Place.GRASSLAND;
                }
                break;
                case "forest": {
                    this.area = Place.FOREST;
                }
                break;
            }
        }


    public void setPlace(Place area) {
        this.area = area;
    }
    @Override
    public String toString() {
    return "Character{" + "name='" + name + '\'' + ", height=" + height + ", weight=" + weight + ", width=" + width + '}'; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (height != character.height) return false;
        if (width != character.width) return false;
        if (weight != character.weight) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        return area == character.area;
    }

    //@Override
    /*public double hashCode() {
        double result = name != null ? name.hashCode() : 0;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + weight;
        result = 31 * result + area.hashCode();
        return result;*/
    }
    class Somnenia {
        private boolean somneniya;   // если true => включена

        public void zasomnevatsa() {
            System.out.println( getName() + " заподозрил неладное и цепко схватил листок");
            somneniya = true;
        }

        public void nesomnevatsa() {
            somneniya = false;
        }

        public boolean getName() {
            return somneniya;
        }

        public void setName(boolean name) {
            this.somneniya = name;
        }
    }

