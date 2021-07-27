package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

public class ConfigHelper {
    private static final Properties CONIFG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);


    /**
     * get JDBC driver
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * get JDBC URL
     */

    public static String getJdbcUrl() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * get JDBC username
     */
    public static String getJdbcUsername() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * get JDBC password
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * get app base package
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * get JSP root path
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONIFG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * get static resource path
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONIFG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
    }
}
