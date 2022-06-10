package com.ruoyi.invoice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 单据管理启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.invoice.mapper")
public class RuoYiInvoiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiInvoiceApplication.class, args);
        System.out.println("单据管理启动成功");
//        System.out.println("(♥◠‿◠)ﾉﾞ  若依单据管理启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
//                " .-------.       ____     __        \n" +
//                " |  _ _   \\      \\   \\   /  /    \n" +
//                " | ( ' )  |       \\  _. /  '       \n" +
//                " |(_ o _) /        _( )_ .'         \n" +
//                " | (_,_).' __  ___(_ o _)'          \n" +
//                " |  |\\ \\  |  ||   |(_,_)'         \n" +
//                " |  | \\ `'   /|   `-'  /           \n" +
//                " |  |  \\    /  \\      /           \n" +
//                " ''-'   `'-'    `-..-'              ");
    }
}
