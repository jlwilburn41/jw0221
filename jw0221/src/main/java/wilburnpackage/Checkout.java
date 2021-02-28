//Checkout allows the user to rent tools and produces a RentalAgreement for each.
package wilburnpackage;

public final class Checkout{
    RentalAgreement rental = null;
    public Checkout(String toolCode, int rentalDays, int discountPercent, String checkoutDate) throws Exception {

        if(rentalDays<1){
            System.out.println("Rental Days must be greater than 0");
            throw new Exception();
        }else if(!(0<=discountPercent && discountPercent<=100)){
            System.out.println("Discount Percent must be within the range [0,100]");
            throw new Exception();
        }else{
            rental = new RentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);
            rental.printAgreement();
        }

    }
}