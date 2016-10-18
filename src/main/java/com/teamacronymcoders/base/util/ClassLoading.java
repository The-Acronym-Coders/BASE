package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.proxies.LibCommonProxy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ClassLoading {
    private ClassLoading() { }

    public static LibCommonProxy createProxy(String clientString, String serverString) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        String proxyString = side == Side.CLIENT ? clientString : serverString;
        Object proxyObject = createObjectInstance(proxyString);
        if(proxyObject instanceof LibCommonProxy) {
            return (LibCommonProxy) proxyObject;
        }
        return null;
    }

    public static <T> T createInstanceOf(Class<T> tClass, String path) {
        Object object = createObjectInstance(path);
        if(object != null) {
            return tClass.cast(object);
        }
        return null;
    }

    public static Object createObjectInstance(String path) {
        try {
            Class classToGrab;
            classToGrab = Class.forName(path);
            return createObjectInstance(classToGrab);
        } catch(ClassNotFoundException exception) {
            Platform.attemptLogExceptionToCurrentMod(exception);
            Platform.attemptLogErrorToCurrentMod(path + " did not initialize. Something's gonna break.");
        }
        return null;
    }

    public static Object createObjectInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch(InstantiationException | IllegalAccessException exception) {
            Platform.attemptLogExceptionToCurrentMod(exception);
            Platform.attemptLogErrorToCurrentMod(clazz.getName() + " did not initialize. Something's gonna break.");
        }
        return null;
    }
}
