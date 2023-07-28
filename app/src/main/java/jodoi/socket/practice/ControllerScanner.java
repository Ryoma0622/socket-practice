package jodoi.socket.practice;

import jodoi.socket.practice.annotation.Controller;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ControllerScanner {
    private final Map<String, Handler> handlers = new HashMap<>();

    public ControllerScanner(String packageName) throws Exception {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);

        Reflections httpMethodReflections = new Reflections("jodoi.socket.practice.annotation.httpmethod");
        Set<Class<?>> httpMethodAnnotations = httpMethodReflections.getTypesAnnotatedWith(Retention.class);

        for (Class<?> controllerClass : controllerClasses) {
            Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            Controller controller = controllerClass.getAnnotation(Controller.class);
            String basePath = controller.value();

            for (Method method : controllerClass.getDeclaredMethods()) {
                for (Class<?> httpMethodClass : httpMethodAnnotations) {
                    Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) httpMethodClass;
                    if (method.isAnnotationPresent(annotationClass)) {
                        Annotation annotation = method.getAnnotation(annotationClass);
                        Method valueMethod = annotation.getClass().getMethod("value");
                        String path = (String) valueMethod.invoke(annotation);
                        String fullPath = basePath + path;
                        handlers.put(httpMethodClass.getSimpleName().toUpperCase() + fullPath, new Handler(controllerInstance, method));
                    }
                }
            }
        }
    }

    public Optional<Handler> getHandler(String method, String path) {
        return Optional.ofNullable(handlers.get(method + path));
    }
}
