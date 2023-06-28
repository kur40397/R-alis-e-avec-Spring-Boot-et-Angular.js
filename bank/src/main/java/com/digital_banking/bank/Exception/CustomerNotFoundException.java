package com.digital_banking.bank.Exception;
// checked Exception mnin katrownihoum must be declared in the method signature
// and try-catch  wla tdir appel lwa7de handler
// kaydarou 5aj 3la itar lprog ==> database(fitchi mn db), error deial fichier (I/O) , error de conn
//****************************************************//
//Uncheck exception machi darouri thandliha wla tdir liha try catch
// error de programme dial code
// null,argument machi howa hadal , ArrayIndexOutOfBoundsException
//A fully checked exception is a checked exception where all its child classes are also checked, like IOException, and InterruptedException.
// A partially checked exception is a checked exception where some of its child classes are unchecked, like an Exception
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg) {
     super(msg); // invoki le constructeur de superclass
        // et initilizi le field
        //et mn tmak le constructeur kaydir additional operations
        // loggin dial message , modification dial lflage, details

    }
}

// mnin katinvoqui super method the constructor of the superclass and performs necessary operations defined within that constructors