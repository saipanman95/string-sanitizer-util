string-sanitizer-util
=====================

String sanitizer utility that strips unwanted HTML and Javascript from annotated model/entity classes.

To use, simply ensure that model or entity class implements Sanitizable and is annotated with @JsHtmlSanitizer like in the following example:

@JsHtmlSanitizer
public class MyPojo implements Sanitizable

If you want a method skipped, meaning you do not want any HTML, CSS, or JavaScript stripped from the process then simply annotate that method with @IgnoreSanitizer.
