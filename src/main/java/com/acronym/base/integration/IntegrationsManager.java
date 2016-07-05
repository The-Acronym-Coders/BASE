package com.acronym.base.integration;

import com.acronym.base.Base;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;

import java.util.ArrayList;
import java.util.List;

public class IntegrationsManager {
    private static IntegrationsManager INSTANCE = new IntegrationsManager();
    private final List<IIntegration> integrationMods = new ArrayList<>();

    public static IntegrationsManager instance() {
        return INSTANCE;
    }

    public void index() {
        List<IntegrationIDs> integrationClasses = new ArrayList<IntegrationIDs>();
        for(IntegrationIDs id : IntegrationIDs.values()) {
            try {
                integrationClasses.add(id);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        // TODO: hmm... configuration stuff?

        for (IntegrationIDs entry : integrationClasses) {
            if (Loader.isModLoaded(entry.getModid())) {
                try {
                    integrationMods.add(entry.getIIntegration().newInstance());
                    Base.logger.info("Integration with " + entry.getModid() + ": Enabled");
                } catch (Throwable ex) {
                    Base.logger.error("Failed to load integration correctly");
                    ex.printStackTrace();
                }
            } else {
                if(entry.isRequired()) {
                    Base.logger.fatal("Integration with "+ entry.getModid() + ": Missing [REQUIRED]");
                    throw new MissingModsException(null,entry.getModid(),entry.name());
                } else {
                    Base.logger.info("Integration with " + entry.getModid() + ": Disabled");
                }
            }
        }
    }

    public void preInit() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.preInit();
            } catch (Throwable ex) {
                Base.logger.error("(Pre Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }

    public void init() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.init();
            } catch (Throwable ex) {
                Base.logger.error("(Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }

    public void postInit() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.postInit();
            } catch (Throwable ex) {
                Base.logger.error("(Post Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }
}
