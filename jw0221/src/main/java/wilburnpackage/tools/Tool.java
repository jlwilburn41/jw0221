//Tool holds the base attributes for all tool instances.
package wilburnpackage.tools;

public class Tool{
    private String TOOL_TYPE;
    private String BRAND;
    private String TOOL_CODE;
    private double DAILY_CHARGE = 0.0;
    private boolean WEEKEND_CHARGE = false;
    private boolean HOLIDAY_CHARGE = false;


    public void setToolType(String toolType){
        this.TOOL_TYPE = toolType;
    }

    public String getToolType(){
        return this.TOOL_TYPE;
    }

    public void setBrand(String brand){
        this.BRAND = brand;
    }

    public String getBrand(){
        return this.BRAND;
    }

    public void setToolCode(String toolCode){
        this.TOOL_CODE = toolCode;
    }

    public String getToolCode(){
        return this.TOOL_CODE;
    }

    public void setDailyCharge(double dailyCharge){
        this.DAILY_CHARGE = dailyCharge;
    }

    public double getDailyCharge(){
        return this.DAILY_CHARGE;
    }

    public void setWeekendCharge(boolean weekendCharge){
        this.WEEKEND_CHARGE = weekendCharge;
    }

    public boolean getWeekendCharge(){
        return this.WEEKEND_CHARGE;
    }

    public void setHolidayCharge(boolean holidayCharge){
        this.HOLIDAY_CHARGE = holidayCharge;
    }

    public boolean getHolidayCharge(){
        return this.HOLIDAY_CHARGE;
    }
}