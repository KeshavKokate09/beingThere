package com.kkokate.beingthere.service;

public class AppConfigService {

    protected boolean isActivationComplete;

    public boolean isAppActivationComplete()
    {
        if(isActivationComplete == false)
        {
           /* ReadAppConfig readAppConfig = new ReadAppConfig();
            AppConfig appConfig = readAppConfig.read(new AppConfig(ACTIVATION_STATUS_KEY, ""));
            if(appConfig != null && Boolean.TRUE.toString().equalsIgnoreCase(appConfig.getAppConfigValue()))
            {
                isActivationComplete= true;
            }*/
            isActivationComplete=true;
        }

        return isActivationComplete;
    }
}
