package com.haiwai.housekeeper.activity.base;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.LocationPlugin;
import io.rong.imkit.widget.provider.TakingPicturesActivity;
import io.rong.imlib.model.Conversation;

/**
 * Created by ihope006 on 2017/4/27.
 */

public class SampleExtensionModule extends DefaultExtensionModule {

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
//        super.getPluginModules(conversationType);  如果需要对红包进行排序可从父类中的 getPluginModules 集合中过滤取出 JrmfExtensionModule
        List<IPluginModule> pluginModuleList = new ArrayList<>();
//        pluginModuleList.add(new SamplePlugin());
        pluginModuleList.add(new ImagePlugin());
        pluginModuleList.add(new LocationPlugin());

        return pluginModuleList;
    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}
