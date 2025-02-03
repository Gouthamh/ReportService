package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {
    private static final Logger logger = LogManager.getFormatterLogger(Example.class);

    public static void main(String[] args) {
    	
    	System.out.println("pre_loggerDOTinfo");
        System.out.println();
        //logger.setLevel(Level.FATAL);   //used to be.. 

        logger.info("Hello, this is an INFO message");
        logger.warn("Hello, this is an WARN  message");
        logger.fatal("Hello, this is an FATAL  message");
        logger.fatal("Hello, this is an FATAL  message ALSO, #2");
        logger.fatal("Hello, this is an FATAL  message ALSO, #3");
        logger.fatal("Hello, this is an FATAL  message ALSO, #4");
        logger.debug("Hello, this is an FATAL  message");

        logger.info("Hello, this is an INFO message");
        logger.info("Hello, this is an INFO message");
        System.out.println();
        System.out.println("post_loggerDOTinfo");

    }
}

