package com.kinnarastudio.kecakplugins.rawhtml.userview;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.userview.model.UserviewMenu;
import org.joget.plugin.base.PluginManager;
import org.springframework.context.ApplicationContext;

public class RawHtmlUserview extends UserviewMenu {
    private final static String LABEL = "Raw HTML Userview";

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClassName(), "/properties/userview/RawHtmlUserview.json");
    }

    @Override
    public String getDescription() {
        return getClass().getPackage().getImplementationTitle();
    }

    @Override
    public String getName() {
        return LABEL;
    }

    @Override
    public String getVersion() {
        PluginManager pluginManager = (PluginManager) AppUtil.getApplicationContext().getBean("pluginManager");
        ResourceBundle resourceBundle = pluginManager.getPluginMessageBundle(getClassName(), "/messages/BuildNumber");
        String buildNumber = resourceBundle.getString("buildNumber");
        return buildNumber;
    }

    @Override
    public String getCategory() {
        return "Kecak";
    }

    @Override
    public String getDecoratedMenu() {
        return null;
    }

    @Override
    public String getIcon() {
        return "<i class=\"fa fa-code\" aria-hidden=\"true\"></i>";
    }

    @Override
    public String getRenderPage() {
        final ApplicationContext applicationContext = AppUtil.getApplicationContext();
        final PluginManager pluginManager = (PluginManager) applicationContext.getBean("pluginManager");

        Map<String, Object> dataModel = new HashMap<>();

        final String htmlCode = getPropertyString("htmlCode");

        dataModel.put("htmlCode", htmlCode);

        return pluginManager.getPluginFreeMarkerTemplate(dataModel, getClassName(), "/templates/RawHtmlUserview.ftl", null);
    }

    @Override
    public boolean isHomePageSupported() {
        return true;
    }
    
}
