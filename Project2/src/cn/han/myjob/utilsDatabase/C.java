package cn.han.myjob.utilsDatabase;

//出处:http://icycream.blog.51cto.com/8432709/1346693

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class C {
    private static Properties prop = null;
    static {
        prop = new Properties();
        try {
            prop.load(C.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties"));
                                                                                                                                        
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
                                                                                                                                
    public static String CONFIG_FILE_LOCATION = System.getProperty("user.dir") + "/jdbc.properties";
    //数据库1
    public static final String TEST1_DRIVER = prop.getProperty("test1.jdbcdriver");
    public static final String TEST1_URL = prop.getProperty("test1.url");
    public static final String TEST1_USER = prop.getProperty("test1.user");
    public static final String TEST1_PASSWORD = prop.getProperty("test1.password");
    //数据库2
    public static final String TEST2_DRIVER = prop.getProperty("test2.jdbcdriver");
    public static final String TEST2_URL = prop.getProperty("test2.url");
    public static final String TEST2_USER = prop.getProperty("test2.user");
    public static final String TEST2_PASSWORD = prop.getProperty("test2.password");
    //数据库3
    public static final String TEST3_DRIVER = prop.getProperty("test3.jdbcdriver");
    public static final String TEST3_URL = prop.getProperty("test3.url");
    public static final String TEST3_USER = prop.getProperty("test3.user");
    public static final String TEST3_PASSWORD = prop.getProperty("test3.password");
}
