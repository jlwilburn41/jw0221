//Chainsaw sets the attributes for a Chainsaw instance of Tool.
package wilburnpackage.tools;

public class Chainsaw extends Tool{

    public Chainsaw(String toolType, String brand, String toolCode){
        this.setToolType(toolType);
        this.setBrand(brand);
        this.setToolCode(toolCode);
        this.setDailyCharge(1.49);
        this.setHolidayCharge(true);
    }

}