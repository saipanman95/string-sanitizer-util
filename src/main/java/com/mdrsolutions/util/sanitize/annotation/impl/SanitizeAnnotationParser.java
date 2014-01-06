/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.annotation.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set; 
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mdrsolutions.util.sanitize.Sanitizable;
import com.mdrsolutions.util.sanitize.annotation.IgnoreSanitizer;


/**
 * 
 * @author michael.rodgers1
 */
public class SanitizeAnnotationParser extends AbstractParserType implements ParserTypes {

    public static final Logger logger = Logger.getLogger(SanitizeAnnotationParser.class.toString());

    /**
     * This method requires that object passed in inherits from Sanitizable
     * interface and then parses each field for setter and getter method which
     * it then invokes to get the value. Once value is obtained, if the value is
     * a string then Apache StringEscapeUtils to ensure that harmful html and
     * javascript are escaped
     *
     * @param parserObj
     * @return
     */
    public static Object stripParser(Object parserObj) {
        if (null != parserObj) {
            for (Field declaredField : parserObj.getClass().getDeclaredFields()) {
                try {
                    if (declaredField.getType().isInstance("")) {
                        String name = declaredField.getName();

                        if (doesMethodExist(name, parserObj, METHOD_TYPE.GETTER)) {
                            parserObj = stripObject(name, parserObj);
                        }
                    } else if (!declaredField.getType().isPrimitive() && !declaredField.getType().isInstance("")) {
                        Class type = findType(declaredField, parserObj);
                        if (null != type) {
                            if (List.class.isAssignableFrom(type)) {
                                parserObj = tertiaryObjectCollection(declaredField, parserObj, type);
                            } else if (Map.class.isAssignableFrom(type)) {
                                parserObj = tertiaryObjectCollection(declaredField, parserObj, type);
                            } else if (Sanitizable.class.isAssignableFrom(declaredField.getDeclaringClass())) {
                                parserObj = tertiaryObject(declaredField, parserObj);
                            }
                        }
                    }

                }
                catch (Throwable ex) {
                    logger.logrb(Level.WARNING, SanitizeAnnotationParser.class.getSimpleName(), "stripParser(Object parserObj)", null, "ATTENTION!!! STRIP ANNOTATION PARSER ERROR: Encountered error attempting to determine if declaredField isInstance of ", ex);
                }
            }
        }
        return parserObj;
    }

    /**
     * Finds the type of Object from the Field and Object parameter and returns
     * the Type of Class returned from its associated getReadMethod. If no
     * getReadMethod exists or if method is private then a null Class object is
     * returned;
     * <br/>
     * <br/>
     * <b>Note</b>: the Field <i>field</i> parameter must be a child object of
     * the Object
     * <i>parserObj</i>
     * parameter
     *
     * @param field Field to be evaluated for the Class Type
     * @param parserObj Object that contains the field being evaluated
     * @return Class object
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Class findType(Field field, Object parserObj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Class subObj = null;

        if (null != field) {
            String name = field.getName();

            if (null != parserObj) {
                if (doesMethodExist(name, parserObj, METHOD_TYPE.GETTER)) {
                    Method getMethod = new PropertyDescriptor(name, parserObj.getClass()).getReadMethod();
                    subObj = getMethod.getReturnType();

                }
            }
        }
        return subObj;
    }

    /**
     * this method is called when sub Object inherits from Sanitizable interface
     *
     * @param tertiaryObject
     * @param parserObj
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object tertiaryObject(Field tertiaryObject, Object parserObj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (null != tertiaryObject) {
            String name = tertiaryObject.getName();

            if (null != parserObj) {

                if (doesMethodExist(name, parserObj, METHOD_TYPE.GETTER)) {

                    Method setMethod = new PropertyDescriptor(name, parserObj.getClass()).getWriteMethod();
                    Method getMethod = new PropertyDescriptor(name, parserObj.getClass()).getReadMethod();
                    Object subObj = getMethod.invoke(parserObj);

                    Object secondaryParserObject = stripParser(subObj);
                    Object[] secondaryObjectValue = {secondaryParserObject};
                    setMethod.invoke(parserObj, secondaryObjectValue);
                }
            }
        }
        return parserObj;
    }

    /**
     * this method can accept for the parameter Class type List and Map
     * interface objects
     *
     * @param tertiaryObject
     * @param parserObj
     * @param type
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object tertiaryObjectCollection(Field tertiaryObject, Object parserObj, Class type) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (null != tertiaryObject) {
            String name = tertiaryObject.getName();

            if (null != parserObj) {
                if (doesMethodExist(name, parserObj, METHOD_TYPE.GETTER)) {

                    Method setMethod = new PropertyDescriptor(name, parserObj.getClass()).getWriteMethod();
                    Method getMethod = new PropertyDescriptor(name, parserObj.getClass()).getReadMethod();
                    Object subObj = getMethod.invoke(parserObj);

                    if (null != type) {
                        if (null != subObj) {
                            //checking differing Collections types

                            //first checking List types
                            if (List.class.isAssignableFrom(type)) {
                                List list = (List) subObj;
                                if (list.size() > 0) {
                                    List afterList = new LinkedList();

                                    for (Object obj : list) {
                                        afterList.add(stripParser(obj));
                                    }

                                    Object[] secondaryObjectValue = {(List)afterList};
                                    setMethod.invoke(parserObj, secondaryObjectValue);
                                }

                                //section checking Map types
                            } else if (Map.class.isAssignableFrom(type)) {
                                Map map = (Map) subObj;
                                if (map.size() > 0) {
                                    Map afterMap = new LinkedHashMap(map.size());
                                    Set keySet = map.keySet();

                                    for (Object key : keySet) {
                                        afterMap.put(key, stripParser(map.get(key)));
                                    }
                                    Object[] secondaryObjectValue = {(Map)afterMap};
                                    setMethod.invoke(parserObj, secondaryObjectValue);
                                }
                            } else {
                                //may need to check for arrays or vectors
                            }

                        }
                    }
                }
            }
        }
        return parserObj;
    }

    /**
     * This method gets the value of an appropriate getter method and uses
     * Apache StringEscapeUtils to ensure that harmful html and javascript are
     * escaped
     *
     * @param name
     * @param parserObj
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object stripObject(String name, Object parserObj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (null != name) {
            if (null != parserObj) {

                Method getMethod = new PropertyDescriptor(name, parserObj.getClass()).getReadMethod();
                Method setMethod = new PropertyDescriptor(name, parserObj.getClass()).getWriteMethod();

                String fieldValue = (String) getMethod.invoke(parserObj);

                boolean ignoreStripperPresent = setMethod.isAnnotationPresent(IgnoreSanitizer.class);

                //strip as much html and javascript as possible
                if (!ignoreStripperPresent) {
//                    logger.debug("ATTENTION!!!  STRIPPED OBJECT = '{'[getMethod={0}],[fieldValue={1}],[setMethod={2}],[dateTime={3}]'}'", new Object[]{getMethod.getName(), fieldValue, setMethod.getName(), Calendar.getInstance().toString()});
                    fieldValue = replaceHtmlJavascript(fieldValue);
                }

                Object[] escapedValue = {fieldValue};

                setMethod.invoke(parserObj, escapedValue);
            }
        }
        return parserObj;
    }

    /**
     * Utility method that determines if the property <i>name</i> of the
     * associated Object <i>parserObj</i> parameter has a getter or setter
     * <br/>
     * <br/>
     * <b>Note</b>: the String <i>name</i> parameter must be a child object of
     * the Object
     * <i>parserObj</i>
     * parameter
     *
     * @param name
     * @param parserObj
     * @return
     */
    public static boolean doesMethodExist(String name, Object parserObj, METHOD_TYPE methodType) {
        try {
            if (methodType.toString().equalsIgnoreCase(METHOD_TYPE.GETTER.name())) {
                Method m = new PropertyDescriptor(name, parserObj.getClass()).getReadMethod();
                return true;
            } else if (methodType.toString().equalsIgnoreCase(METHOD_TYPE.SETTER.name())) {
                Method m = new PropertyDescriptor(name, parserObj.getClass()).getWriteMethod();
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
}
