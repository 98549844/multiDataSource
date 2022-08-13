package com.ace.multidatasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class MultiDataSourceApplication {


    public static void main(String[] args) {

        SpringApplication.run(MultiDataSourceApplication.class, args);

        System.out.println("Independent multiple data sources demo");
        System.out.println("Each data source has a corresponding: ");
        System.out.println("controller,service,serviceImpl,dao,xml,entity");

    }


}
