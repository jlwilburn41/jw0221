//Ladder sets the attributes for a Ladder instance of Tool.
package wilburnpackage.tools;

public class Ladder extends Tool{
    
    public Ladder(String toolType, String brand, String toolCode){

        this.setToolType(toolType);
        this.setBrand(brand);
        this.setToolCode(toolCode);
        this.setDailyCharge(1.99);
        this.setWeekendCharge(true);

    }

}