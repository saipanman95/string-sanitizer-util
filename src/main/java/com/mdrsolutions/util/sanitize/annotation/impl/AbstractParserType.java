/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.annotation.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import static com.mdrsolutions.util.sanitize.annotation.impl.ParserTypes.HTML_EVENT_LISTENERS;
import static com.mdrsolutions.util.sanitize.annotation.impl.ParserTypes.HTML_TAGS_ATTRIBUTES;
import static com.mdrsolutions.util.sanitize.annotation.impl.ParserTypes.JS_DOCUMENT_GET_ELEMENT_CALLS;
import static com.mdrsolutions.util.sanitize.annotation.impl.ParserTypes.SCRIPT_TAGS;
import static com.mdrsolutions.util.sanitize.annotation.impl.ParserTypes.TAG_OPEN_CLOSERS;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author michael.rodgers1
 */
public class AbstractParserType implements ParserTypes {

    public static enum METHOD_TYPE {

        SETTER, GETTER;
    }

    public static String replaceHtmlJavascript(String charString) {

        if (null != charString) {
            charString = stripScriptTags(charString);
            charString = stripHtmlTags(charString);
            charString = stripElementEventListeners(charString);
            charString = stripDocumentGetElementCalls(charString);
            charString = stripTagOpenClose(charString);
            charString = stripNonAsciUnicode(charString);
            charString = charString.replaceAll("\\Qalert(\\E", "");
            //escape the html syntax
            //charString = StringEscapeUtils.escapeHtml4(charString);
            //escape the javascript syntax
            //charString = StringEscapeUtils.escapeEcmaScript(charString); 
            charString = StringUtils.stripAccents(charString);
        }
        return charString;
    }

    private static String stripTagOpenClose(String charString) {
        if (null != charString) {
            for (String pattern : TAG_OPEN_CLOSERS) {
                charString = replaceULAll(charString, pattern, "");
            }
        }
        return charString;
    }

    private static String stripScriptTags(String charString) {
        if (null != charString) {
            for (String pattern : SCRIPT_TAGS) {
                charString = replaceULAll(charString, pattern, "");
            }
        }
        return charString;
    }

    private static String stripHtmlTags(String charString) {
        if (null != charString) {
            for (String pattern : HTML_TAGS_ATTRIBUTES) {
                charString = replaceULAll(charString, pattern, "");
            }
        }
        return charString;
    }

    private static String stripElementEventListeners(String charString) {
        if (null != charString) {
            for (String pattern : HTML_EVENT_LISTENERS) {
                charString = replaceULAll(charString, pattern, "");
            }
        }
        return charString;
    }

    private static String stripDocumentGetElementCalls(String charString) {
        if (null != charString) {
            for (String pattern : JS_DOCUMENT_GET_ELEMENT_CALLS) {
                charString = replaceULAll(charString, pattern, "");
            }
        }
        return charString;
    }

    private static String wrapp(String charString) {
        //if (charString.contains("<") || charString.contains(">")) {
        //    return "(?i)" + charString + "([^>]+)(.+?)";
        //} else {
        //    return "\\b(" + charString + ")\\W";
        //}
        return charString;
    }

    private static String replaceULAll(String charString, String pattern, String replaceWith) {
        if (null != charString) {
            charString = charString.replaceAll(wrapp(pattern), replaceWith);
            if (!pattern.contains("(?i")) {
                charString = charString.replaceAll(wrapp(pattern.toLowerCase()), replaceWith);
                charString = charString.replaceAll(wrapp(pattern.toUpperCase()), replaceWith);
            }
        }
        return charString;
    }

    private static String stripNonAsciUnicode(String charString) {
        Set<String> keySet = getUnicodeMap().keySet();
        for (String key : keySet) {
            Pattern p = Pattern.compile("[" + key + "]");
            charString = charString.replaceAll(p.pattern(), getUnicodeMap().get(key));
        }
        return charString;
    }

    private static Map<String, String> getUnicodeMap() {
        Map<String, String> unicodeMap = new HashMap<String, String>(100);
        unicodeMap.put("•", "*");
        unicodeMap.put("", "*");
        unicodeMap.put("", "*");
        unicodeMap.put("", "*");
        unicodeMap.put("", "*");
        unicodeMap.put("«", "\"");
        unicodeMap.put("­", "-");
        unicodeMap.put("´", "'");
        unicodeMap.put("»", "\"");
        unicodeMap.put("÷", "/");
        unicodeMap.put("ǀ", "|");
        unicodeMap.put("ǃ", "!");
        unicodeMap.put("ʹ", "'");
        unicodeMap.put("ʺ", "\"");
        unicodeMap.put("ʼ", "'");
        unicodeMap.put("’", "'");
        unicodeMap.put("˄", "^");
        unicodeMap.put("ˆ", "^");
        unicodeMap.put("ˈ", "'");
        unicodeMap.put("ˋ", "`");
        unicodeMap.put("ˍ", "_");
        unicodeMap.put("˜", "~");
        unicodeMap.put("։", ":");
        unicodeMap.put("٪", "%");
        unicodeMap.put("٭", "*");
        unicodeMap.put("‐", "-");
        unicodeMap.put("‑", "-");
        unicodeMap.put("‒", "-");
        unicodeMap.put("–", "-");
        unicodeMap.put("—", "-");
        unicodeMap.put("―", "--");
        unicodeMap.put("‗", "_");
        unicodeMap.put("‘", "'");
        unicodeMap.put("’", "'");
        unicodeMap.put("‚", ",");
        unicodeMap.put("‛", "'");
        unicodeMap.put("“", "\"");
        unicodeMap.put("”", "\"");
        unicodeMap.put("„", "\"");
        unicodeMap.put("‟", "\"");
        unicodeMap.put("′", "'");
        unicodeMap.put("″", "\"");
        unicodeMap.put("‴", "'''");
        unicodeMap.put("‵", "`");
        unicodeMap.put("‶", "\"");
        unicodeMap.put("‷", "'''");
        unicodeMap.put("‸", "^");
        unicodeMap.put("‹", "<");
        unicodeMap.put("›", ">");
        unicodeMap.put("‽", "?");
        unicodeMap.put("⁄", "/");
        unicodeMap.put("⁎", "*");
        unicodeMap.put("⁒", "%");
        unicodeMap.put("⁓", "~");
        unicodeMap.put("⁠", "");
        unicodeMap.put("−", "-");
        unicodeMap.put("∕", "/");
        unicodeMap.put("∖", "\"");
        unicodeMap.put("∗", "*");
        unicodeMap.put("∣", "|");
        unicodeMap.put("∶", ":");
        unicodeMap.put("∼", "~");
        unicodeMap.put("≤", "<=");
        unicodeMap.put("≥", ">=");
        unicodeMap.put("≦", "<=");
        unicodeMap.put("≧", ">=");
        unicodeMap.put("⌃", "^");
        unicodeMap.put("〈", "<");
        unicodeMap.put("〉", ">");
        unicodeMap.put("♯", "#");
        unicodeMap.put("✱", "*");
        unicodeMap.put("❘", "|");
        unicodeMap.put("❢", "!");
        unicodeMap.put("⟦", "[");
        unicodeMap.put("⟨", "<");
        unicodeMap.put("⟩", ">");
        unicodeMap.put("⦃", "{");
        unicodeMap.put("⦄", "}");
        unicodeMap.put("〃", "\"");
        unicodeMap.put("〈", "<");
        unicodeMap.put("〉", ">");
        unicodeMap.put("〛", "]");
        unicodeMap.put("〜", "~");
        unicodeMap.put("〝", "\"");
        unicodeMap.put("〞", "\"");


        return unicodeMap;
    }

    public AbstractParserType() {
    }
}
