//Jackhammer sets the attributes for a Jackhammer instance of Tool.
package wilburnpackage.tools;

public class Jackhammer extends Tool{
    
    public Jackhammer(String toolType, String brand, String toolCode){
        this.setToolType(toolType);
        this.setBrand(brand);
        this.setToolCode(toolCode);
        this.setDailyCharge(2.99);
    }

}