package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.Base;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClassLoading {
    private ClassLoading() {
    }

    @Nullable
    public static <T> T createInstanceOf(Class<T> tClass, String path) {
        Object object = createObjectInstance(path);
        if (object != null) {
            return tClass.cast(object);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static Object createObjectInstance(String path) {
        try {
            Class classToGrab;
            classToGrab = Class.forName(path);
            return createObjectInstance(classToGrab);
        } catch (ClassNotFoundException exception) {
            Base.instance.getLogger().warn(path + " did not initialize. Something's gonna break.");
        }
        return null;
    }

    @Nullable
    public static <T> T createObjectInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            Base.instance.getLogger().warn(clazz.getName() + " did not initialize. Something's gonna break.");
        }
        return null;
    }

    public static <T> List<T> getInstances(Class annotationClass, Class<T> instanceClass) {
        return getInstances(annotationClass, instanceClass, aClass -> true);
    }

    public static <T> List<T> getInstances(Class annotationClass, Class<T> instanceClass,
                                    Function<Map<String, Object>, Boolean> createInstance) {
        return ModList.get().getAllScanData().parallelStream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(List::stream)
                .filter(checkType(annotationClass))
                .filter(checkLoad(createInstance))
                .map(loadInstances(instanceClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private static <T> Function<ModFileScanData.AnnotationData, Optional<T>> loadInstances(Class<T> instanceClass) {
        return data -> {
            try {
                Class<?> asmClass = Class.forName(data.getClassType().getClassName());
                Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
                return Optional.ofNullable(asmInstanceClass.newInstance());
            } catch (IllegalAccessException | InstantiationException exception) {
                Base.instance.getLogger().warn(exception);
            } catch (ClassNotFoundException e) {
                //Silence for some things are side only....
            }
            return Optional.empty();
        };
    }

    private static Predicate<ModFileScanData.AnnotationData> checkLoad(Function<Map<String, Object>, Boolean> createInstance) {
        return annotationData -> createInstance.apply(annotationData.getAnnotationData());
    }

    private static Predicate<ModFileScanData.AnnotationData> checkType(Class annotationClass) {
        final Type annotationType = Type.getType(annotationClass);
        return annotationData -> annotationData.getAnnotationType().equals(annotationType);
    }
}
