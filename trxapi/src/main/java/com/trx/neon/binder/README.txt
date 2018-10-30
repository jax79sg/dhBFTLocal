com.trx.neon.binder

This package is responsible for all of the low level binding code that transforms data going to and from the NeonLocationService.  
Modifying any of the classes in this package (even at the level of changing field names) could very easily break compatibility with 
the NeonLocationServices.  Application developers should not need to make modifications to this package.  

If there's something missing or broken in this package, please let TRX Systems know.

support@trxsystems.com

Rules for editing binders and not breaking compatibility:
//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT

-Dan
