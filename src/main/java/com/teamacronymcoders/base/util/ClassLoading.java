package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.proxies.LibCommonProxy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ClassLoading {
    private ClassLoading() {
    }

    public static LibCommonProxy createProxy(String clientString, String serverString) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        String proxyString = side == Side.CLIENT ? clientString : serverString;
        Object proxyObject = createObjectInstance(proxyString);
        if (proxyObject instanceof LibCommonProxy) {
            return (LibCommonProxy) proxyObject;
        }
        return null;
    }

    public static <T> T createInstanceOf(Class<T> tClass, String path) {
        Object object = createObjectInstance(path);
        if (object != null) {
            return tClass.cast(object);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Object createObjectInstance(String path) {
        try {
            Class classToGrab;
            classToGrab = Class.forName(path);
            return createObjectInstance(classToGrab);
        } catch (ClassNotFoundException exception) {
            Platform.attemptLogExceptionToCurrentMod(exception);
            Platform.attemptLogErrorToCurrentMod(path + " did not initialize. Something's gonna break.");
        }
        return null;
    }

    public static <T> T createObjectInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            Platform.attemptLogExceptionToCurrentMod(exception);
            Platform.attemptLogErrorToCurrentMod(clazz.getName() + " did not initialize. Something's gonna break.");
        }
        return null;
    }

    public static <T> List<T> getInstances(@Nonnull ASMDataTable asmDataTable, Class annotationClass,
                                           Class<T> instanceClass) {
        return getInstances(asmDataTable, annotationClass, instanceClass, aClass -> true);
    }

    public static <T> List<T> getInstances(@Nonnull ASMDataTable asmDataTable, Class annotationClass,
                                           Class<T> instanceClass, Function<Class<? extends T>, Boolean> createInstance) {
        String annotationClassName = annotationClass.getCanonicalName();
        Set<ASMDataTable.ASMData> asmDataSet = asmDataTable.getAll(annotationClassName);
        List<T> instances = new ArrayList<>();
        for (ASMDataTable.ASMData asmData : asmDataSet) {
            try {
                Class<?> asmClass = Class.forName(asmData.getClassName());
                Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
                if (createInstance.apply(asmInstanceClass)) {
                    T instance = asmInstanceClass.newInstance();
                    instances.add(instance);
                }
            } catch (IllegalAccessException | InstantiationException exception) {
                Platform.attemptLogErrorToCurrentMod("Failed to load: " + asmData.getClassName());
                Platform.attemptLogExceptionToCurrentMod(exception);
            } catch (ClassNotFoundException e) {
                //Silence for some things are side only....
            }
        }
        return instances;
    }
}
