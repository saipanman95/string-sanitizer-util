/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.util.sanitize.annotation.impl;

/**
 *
 * @author michael.rodgers1
 */
public interface ParserTypes {

    public static final String[] SCRIPT_TAGS = {"<script>", "</script>", "<script", "<script", "<script src=", "<script src ="};
    public static final String[] HTML_TAGS_ATTRIBUTES = {"<input", "<input type=", "type='button'", "type='text'", "type='radio'", "type='checkbox'", "type='input'", "type =", 
        "name=", "name =", "id=", "id =", "class=", "class =", "style=", "style =", "value=", "value =", "</input>", "</input", "<form", "</form>", "<select", "</select>", 
        "<option", "</option>", "<link", "</link>", "<image", "</image>", "<body", "</body>", "<button", "</button>", "<object", "</object>", "<area", "</area>", "<base", 
        "</base>", "<meta", "</meta>", "<b>", "<b", "</b>", "<span>", "<span", "</span>", "<div>", "<div", "</div>", "<h1>", "<h1", "</h1>", "<h2>", "<h2", "</h2>", "<h3>", 
        "<h3", "</h3>", "<h4>", "<h4", "</h4>", "<h5>", "<h5", "</h5>", "<h6>", "<h6", "</h6>", "<h7>", "<h7", "</h7>", "<h8>", "<h8", "</h8>", "<table>", "<table", "</table>", 
        "<thead>", "<thead", "</thead>", "<tbody>", "<tbody", "</tbody>", "<tr>", "<tr", "</tr>", "<td>", "<td", "</td>", "<th>", "<th", "</th>", "<tfoot>", "<tfoot", "</tfoot>", 
        "<canvas>", "<canvas", "</canvas>", "<br>", "<br", "</br>", "<br/>", "<style>", "<style", "</style>", "<label>", "<label", "</label>", "<img>", "<img", "</img>", "<p>", 
        "<p", "</p>", "<strike>", "<strike", "</strike>", "<title>", "<title", "</title>", "<u>", "<u", "</u>", "<ul>", "<ul", "</ul>", "<li>", "<li", "</li>", "<i>", "<i", 
        "</i>", "<hr>", "<hr", "</hr>", "<fieldset>", "<fieldset", "</fieldset>", "<caption>", "<caption", "</caption>", "accesskey=", "class=", "contenteditable=", 
        "contextmenu=", "dir=", "draggable=", "dropzone=", "hidden=", "id=", "lang=", "spellcheck=", "style=", "tabindex=", "title=", "translate=", "accesskey =", "class  =", 
        "contenteditable=", "contextmenu =", "dir =", "draggable =", "dropzone =", "hidden =", "id =", "lang =", "spellcheck =", "style =", "tabindex =", "title =", 
        "translate =", "accesskey ="};
    public static final String[] HTML_EVENT_LISTENERS = {"onclick=", "ondblclick=", "onmousedown=", "onmousemove=", "onmouseover=", "onmouseout=", "onmouseup=", "onkeydown=", "onkeypress=", "onkeyup=", "onabort=", "onerror=", "onload=", "onresize=", "onscroll=", "onunload=", "onblur=", "onchange=", "onfocus=", "onreset=", "onselect=", "onsubmit="};
    public static final String[] JS_DOCUMENT_GET_ELEMENT_CALLS = {"document.getElementById", "document.getElemenstByTagName", "document.getElementsByClassName", "document.forms", "document.write", "document.createElement", "document.removeChild", "document.appendChild", "document.replaceChild", ".innerHTML=", ".attribute=", ".style.attribute="};
    public static final String[] TAG_OPEN_CLOSERS = {"<", "/>", ">"};
}
