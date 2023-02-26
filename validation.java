import java.util.regex.*;

/**
 * A class to represent an address
 */
class Address {
    private String streetLine1, streetLine2, city, state, postalCode;

    /**
     * Constructor to initialize an Address object
     * @param streetLine1 the first line of the street address
     * @param streetLine2 the second line of the street address
     * @param city the city of the address
     * @param postalCode the postal code of the address
     */
    Address(String streetLine1, String streetLine2, String city, String postalCode) {
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.city = city;
        this.postalCode = postalCode;
    }

    /**
     * Display the address details
     */
    void displayAddress() {
        System.out.println("Street Line 1: " + this.streetLine1);
        System.out.println("Street Line 2: " + this.streetLine2);
        System.out.println("City: " + this.city);
        System.out.println("Postal Code: " + this.postalCode);
    }
}

/**
 * Exception class to indicate invalid email
 */
class EmailNotValidException extends Exception {
    EmailNotValidException(String message) {
        super(message);
    }
}

/**
 * Exception class to indicate invalid phone number
 */
class PhoneNumberNotValidException extends Exception {
    PhoneNumberNotValidException(String message) {
        super(message);
    }
}

/**
 * A utility class to validate email and phone number
 */
class Validator {
    /**
     * Validate if the provided email address is valid
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate if the provided phone number is valid
     * @param phoneNo the phone number to validate
     * @return true if the phone number is valid, false otherwise
     */
    static boolean validatePhoneNo(String phoneNo) {
        // Allows only Indian phone numbers
        String regex = "^\\+?[9][1]-?[1-9][0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);

        if (phoneNo == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }
}

/**
 * A class to represent a person
 */
class Person
{
    
    /**
     * Constructor to initialize a Person object
     * @param name the name of the person
     * @param email the email address of the person
     * @param phone the phone number of the person
     * @param address the address of the person
     * @param manager the manager of the person
     * @throws EmailNotValidException if the provided email is not valid
     * @throws PhoneNumberNotValidException if the provided phone number is not valid
     */
    
    String name,email,phone;
    Person manager;
    Address address;

    Person(String name,String email,String phone,Address address,Person manager) throws Exception
    {
        if(!Validate.validateEmail(email))
        {
            throw new EmailNotValidException("Please Enter a valid email address for "+ name);
        }
        if(!Validate.validatePhoneNo(phone))
        {
            throw new PhoneNumberNotValidException("Please Enter a valid phone number for "+name);
        }

        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.manager=manager;
    }


    void displayPerson()
    {
        System.out.println("name =  "+this.name );
        System.out.println("email = " +this.email );
        System.out.println("phone = " +this.phone );
        if(this.manager!=null)
            System.out.println("manager = " +this.manager.name);
    }

}

public class Main
{
    public static void main(String[] args) throws Exception{

        Address p1ad=new Address("s1","s223","city1","500084");
        Address p2ad=new Address("s2","s2312","city2","500044");
        Address p3ad=new Address("s3","s23154","city3","500084");
        Address p4ad=new Address("s4","s2222","city4","500074");

        Person p1=null,p2=null,p3=null,p4=null,p5=null;

        try {
            p1=new Person("p1","p1@email.com","+91-4156232215",p1ad,null);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        } catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p2=new Person("p2","p2@email.com","+91-651511516516",p2ad,p1);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p3=new Person("p3","p3@email.com","+91-165165151",p3ad,p2);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p4=new Person("p4","p4@email.com","+91-9354999989",p4ad,p3);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p5=new Person("p5","@email.com","+91-8799699989",p3ad,p4);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }


        // if the entered phone number or email is not valid object will not be created 
        // the reference will remain null

        //get details of p4

        p4.displayPerson();
        System.out.println("Address");
        p4.address.displayAddress();

        Person p=p4.manager;
        System.out.println();
        while(p!=null)
        {
            p.displayPerson();
            System.out.println("Address");
            p.address.displayAddress();
            p=p.manager;
            System.out.println();
        }


    }
}
