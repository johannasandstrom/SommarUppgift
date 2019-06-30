import java.util.HashMap;

public class Rank {
    private String description;
    private int value;

    public Rank(int value){
        this.value = value;
        setDescription(value);
    }

    public String getDescription() {
        return description;
    }

    //based on the card's value, it gets a description (if 12 -> queen etc.)
    private void setDescription(int value){
        switch (value) {
            case 1:
                description = "ace";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                description = Integer.toString(value);
                break;
            case 11:
                description = "knave";
                break;
            case 12:
                description = "queen";
                break;
            case 13:
                description = "king";
                break;
            default:
                System.out.println(value + " is not a valid rank value.");
        }
    }

    public int getValue() {
        return value;
    }

    public String toString(){
        return description;
    }

}
