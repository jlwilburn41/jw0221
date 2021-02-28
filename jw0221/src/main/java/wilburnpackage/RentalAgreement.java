//Holds all data relevant to a Tool Rental for use in Checkout.java
package wilburnpackage;

import wilburnpackage.tools.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RentalAgreement{
    
    private String TOOL_TYPE = "";
    private String BRAND = "";
    private String TOOL_CODE = "";
    private int RENTAL_DAYS = 0;
    private String CHECKOUT_DATE = "";
    private String DUE_DATE = "";
    private double DAILY_CHARGE = 0;
    private double PRE_DISCOUNT_CHARGE = 0;
    private int DISCOUNT_PERCENT = 0;
    private double DISCOUNT_AMOUNT = 0;
    private double FINAL_CHARGE = 0;
    private int CHARGE_DAYS = 0;

    public RentalAgreement(String toolCode, int rentalDays, int discountPercent, String checkoutDate){
        this.RENTAL_DAYS = rentalDays;
        this.DISCOUNT_PERCENT = discountPercent;
        this.CHECKOUT_DATE = checkoutDate;

        //Generate a Rental Agreement for a given TOOL_CODE
        switch(toolCode){
            case "LADW":
                Ladder ladder = new Ladder("Ladder", "Werner", "LADW");
                this.TOOL_TYPE = ladder.getToolType();
                this.BRAND = ladder.getBrand();
                this.TOOL_CODE = ladder.getToolCode();
                this.DAILY_CHARGE = ladder.getDailyCharge();
                generateAgreement(ladder.getHolidayCharge(), ladder.getWeekendCharge());
                break;
            case "CHNS":
                Chainsaw chainsaw = new Chainsaw("Chainsaw", "Stihl", "CHNS");
                this.TOOL_TYPE = chainsaw.getToolType();
                this.BRAND = chainsaw.getBrand();
                this.TOOL_CODE = chainsaw.getToolCode();
                this.DAILY_CHARGE = chainsaw.getDailyCharge();
                generateAgreement(chainsaw.getHolidayCharge(), chainsaw.getWeekendCharge());
                break;
            case "JAKR":
                Jackhammer ridgidJackhammer = new Jackhammer("Jackhammer", "Ridgid", "JAKR");
                this.TOOL_TYPE = ridgidJackhammer.getToolType();
                this.BRAND = ridgidJackhammer.getBrand();
                this.TOOL_CODE = ridgidJackhammer.getToolCode();
                this.DAILY_CHARGE = ridgidJackhammer.getDailyCharge();
                generateAgreement(ridgidJackhammer.getHolidayCharge(), ridgidJackhammer.getWeekendCharge());
                break;
            case "JAKD":
                Jackhammer dewaltJackhammer = new Jackhammer("Jackhammer", "DeWalt", "JAKD");
                this.TOOL_TYPE = dewaltJackhammer.getToolType();
                this.BRAND = dewaltJackhammer.getBrand();
                this.TOOL_CODE = dewaltJackhammer.getToolCode();
                this.DAILY_CHARGE = dewaltJackhammer.getDailyCharge();
                generateAgreement(dewaltJackhammer.getHolidayCharge(), dewaltJackhammer.getWeekendCharge());
                break;
            default:
                System.out.println("Invalid TOOL_CODE.");
                break;
        }
    }

    public void generateAgreement(boolean holidayCharge, boolean weekendCharge){

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Calendar c = Calendar.getInstance();

        try{
            c.setTime(sdf.parse(this.CHECKOUT_DATE));

            //Determine and set the due date
            c.add(Calendar.DAY_OF_MONTH, this.RENTAL_DAYS);
            this.DUE_DATE = sdf.format(c.getTime());

            //Check for holidays and weekends, set CHARGE_DAYS accordingly
            parseChargeDays(holidayCharge, weekendCharge);

            //Calculate Pre Discount Charge
            this.PRE_DISCOUNT_CHARGE = this.DAILY_CHARGE * this.CHARGE_DAYS;
            BigDecimal bd = new BigDecimal(this.PRE_DISCOUNT_CHARGE).setScale(2, RoundingMode.HALF_UP);
            this.PRE_DISCOUNT_CHARGE = bd.doubleValue();

            //Calculate discount amount
            if(this.DISCOUNT_PERCENT > 0){
                this.DISCOUNT_AMOUNT = this.PRE_DISCOUNT_CHARGE * (Double.valueOf(this.DISCOUNT_PERCENT)/100);
                bd = new BigDecimal(this.DISCOUNT_AMOUNT).setScale(2, RoundingMode.HALF_UP);
                this.DISCOUNT_AMOUNT = bd.doubleValue();
            }

            //Calculate Final Charge
            this.FINAL_CHARGE = this.PRE_DISCOUNT_CHARGE - this.DISCOUNT_AMOUNT;
            bd = new BigDecimal(this.FINAL_CHARGE).setScale(2, RoundingMode.HALF_UP);
            this.FINAL_CHARGE = bd.doubleValue();

        }catch(ParseException e){
            e.printStackTrace();
        }
        
    }

    //Check for holidays or weekends between checkout and due date and set CHARGE_DAYS
    public void parseChargeDays(boolean holidayCharge, boolean weekendCharge){
        
        int freeDays = 0; //Used to count how many free days are in the rental term.

        
        SimpleDateFormat tempSDF = new SimpleDateFormat("MM/dd/yy");
        Calendar tempCalendar = Calendar.getInstance();

        try{
            tempCalendar.setTime(tempSDF.parse(this.CHECKOUT_DATE));

            for(int i = this.RENTAL_DAYS; i >= 0; i--){
                tempCalendar.add(Calendar.DATE, 1);

                //Store the month, date and day of week
                int month = tempCalendar.getTime().getMonth();
                int date = tempCalendar.getTime().getDate();
                int day = tempCalendar.getTime().getDay();
    
                if(!holidayCharge){ //If this tool gets free holidays then check for any holidays
    
                    if(month == 6 && date == 4){ //Check if July 4th
    
                        if(day == 6){ //If the 4th is on Saturday, we observe on Friday (yesterday)
                            
                            if(i+1 <= this.RENTAL_DAYS){ //If yesterday is in rental period, then honor the free day
                                freeDays++;
                            }
    
                        }else if(day == 0){ //If the 4th is on Sunday, we observe on Monday (tomorrow)
    
                            if(i > 1){ //If tomorrow is in rental period, then give them the free day
                                freeDays++;
                            }
    
                        }else{//The holiday is not on a weekend so we just count it.
    
                            freeDays++;;
    
                        }
                            
                    }else if(month == 8 && day == 1){ //Check if the current day in the loop is a Monday in September
    
                        if(date < 8){ //If the date is less than September 8th we can assume it's the first Monday in September
    
                            freeDays++;
    
                        }
                    }
                }
                
                if(!weekendCharge){ //If this tool gets free weekends then check for Saturdays or Sundays within the rental term.
                    if(tempCalendar.getTime().getDay() == 6 || tempCalendar.getTime().getDay() == 0){
                        freeDays++;
                    }
                }
            }
            
            this.CHARGE_DAYS = this.RENTAL_DAYS - freeDays; 
        }catch(ParseException e){
            e.printStackTrace();
        }


    }

    public void printAgreement(){
        System.out.println("TOOL CODE: " +this.TOOL_CODE);
        System.out.println("TOOL TYPE: " + this.TOOL_TYPE);
        System.out.println("BRAND: " + this.BRAND);
        System.out.println("RENTAL DAYS: " + this.RENTAL_DAYS);
        System.out.println("CHECKOUT DATE: " + this.CHECKOUT_DATE);
        System.out.println("DUE: " + this.DUE_DATE);
        System.out.println("DAILY CHARGE: $" + this.DAILY_CHARGE);
        System.out.println("CHARGE DAYS: " + this.CHARGE_DAYS);
        System.out.println("PRE DISCOUNT: $" + this.PRE_DISCOUNT_CHARGE);
        System.out.println("DISCOUNT: " + this.DISCOUNT_PERCENT + "%");
        System.out.println("DISCOUNT AMOUNT: $" + this.DISCOUNT_AMOUNT);
        System.out.println("FINAL CHARGE: $" + this.FINAL_CHARGE);
    
    }


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

    public void setRentalDays(int rentalDays){
        this.RENTAL_DAYS = rentalDays;
    }

    public int getRentalDays(){
        return RENTAL_DAYS;
    }

    public void setCheckoutDate(String checkoutDate){
        this.CHECKOUT_DATE = checkoutDate;
    }

    public String getCheckoutDate(){
        return this.CHECKOUT_DATE;
    }

    public void setDueDate(String dueDate){
        this.DUE_DATE = dueDate;
    }

    public String getDueDate(){
        return this.DUE_DATE;
    }

    public void setDailyCharge(int dailyCharge){
        this.DAILY_CHARGE = dailyCharge;
    }

    public double getDailyCharge(){
        return this.DAILY_CHARGE;
    }

    public void setPreDiscountCharge(int preDiscountCharge){
        this.PRE_DISCOUNT_CHARGE = preDiscountCharge;
    }

    public double getPreDiscountCharge(){
        return this.PRE_DISCOUNT_CHARGE;
    }

    public void setDiscountPercent(int discountPercent){
        this.DISCOUNT_PERCENT = discountPercent;
    }

    public int getDiscountPercent(){
        return this.DISCOUNT_PERCENT;
    }

    public void setDiscountAmount(double discountAmount){
        this.DISCOUNT_AMOUNT = discountAmount;
    }

    public double getDiscountAmount(){
        return this.DISCOUNT_AMOUNT;
    }

    public void setFinalCharge(double finalCharge){
        this.FINAL_CHARGE = finalCharge;
    }

    public double getFinalCharge(){
        return this.FINAL_CHARGE;
    }
}