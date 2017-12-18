package com.cundream.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTag {

    public static void main(String[] args) {
        String htmlStr = "Product<!----><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633579_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633581_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633583_extra.gif' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633585_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633587_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633589_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><img src='http://img.crazysales.com.au/products_pictures/1093/109319_633591_extra.jpg' alt='Racing Office Computer Chair Lumbar Support Adjustable PU Leather Home Gaming Chair'/><p>Is your back getting you down? Are you having back pains?  If so what are the solutions? Getting a massage sounds like a great idea but  that is only a band-aid solution. What you need is something that solves the  problem permanently. Like a good Adjustable Leather Office Chair, this chair is  comfortable thanks to the foam padding allowing it to soothe your back. It has  a 360 degree swivel letting you move around easier. Lumbar support, height and  back seat tilt are adjustable to your needs. Sturdy and durable in construction  while being easy to clean makes it a Winner! </p><!---->Features<!----><ul>  <li>Nylon Base</li>  <li>Easy to Clean</li>  <li>360 Degree Swivel</li>  <li>Easy To Assemble</li>  <li>Color :Black and Red</li>  <li>Premium PU Faux Fur</li>  <li>Adjustable Seat Height </li>  <li>Reliable Base with Castors</li><li>Adjustable back and seat tilt </li><li>Gas Lift for Height Adjustment</li><li>Adjustable Inbuilt Lumbar Support</li><li>Comfortable, Filled Foam Padding</li></ul><!---->Specification<!----><table border='1' cellspacing='0' cellpadding='0'>  <tr>    <td width='319' valign='top'>Max Capacity Weight: </td>   <td width='319' valign='top'><p>120kg</p></td>  </tr>  <tr>    <td width='319' valign='top'>Color    : </td>   <td width='319' valign='top'><p>Black    and red </p></td> </tr>  <tr>   <td width='319' valign='top'><p>Product    size:</p></td>    <td width='319' valign='top'><p>W62 x    D72 x H110-120cm approx<br/>Seat Height:49-59cm<br/>Seat Depth:50cm<br/>Backrest Height:71cm</p></td>  </tr>  <tr>    <td width='319' valign='top'><p>Package    size:</p></td><td width='319' valign='top'><p>80 x 32    x 58 cm approx</p></td>  </tr><tr><td width='319' valign='top'><p>G.W:</p></td><td width='319' valign='top'><p>17.6KG    approx</p></td></tr></table><!---->Warranty<!--crazysales--><!----><p>This product comes with a 3 Years Manufacturer's Warranty, please <a href='http://www.crazysales.com.au/index.php?faq=yes&cate=12'>click here</a> for details.</p><p>For warranty and returns assistance please <a href='http://www.crazysales.com.au/index.php?feedback=yes'>contact us</a>. </p><p>Statutory conditions and warranties also apply.</p><!---->Warranty<!--ebay--><!----><p><img src='http://www.crazysales.com.au/images/manfacture-icon.jpg' width='160' height='92' style='float: left' /></p><p> This product comes with a 3 Years Manufacturer's Warranty, </p>'";
        String htmlUpdae = HtmlTag.updateHtmlTag(htmlStr, "img", "src", "http://img");
        System.out.println("最后字符   " + htmlUpdae);
    }
    
    /**   
     * @param htmlStr  html文本   
     * @param searchTag  要修改的目标标签  
     * @param searchAttrib  目标标签中的属性  
     * @param startStr  开始文本       
     */
    public static String updateHtmlTag(String htmlStr, String searchTag, String searchAttrib, String startStr) {
        String regxpForTag = "<\\s*" + searchTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = searchAttrib + "\\s*=\\s*[\"|']http://([^\"|']+)[\"|']";// "=[\"|']([^[\"|']]+)[\"|']";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(htmlStr);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer("<" + searchTag + " ");
            System.out.println(matcherForTag.group(1));
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));

            if (matcherForAttrib.find()) {
                String html = matcherForAttrib.group(1);
                String htmlUpdate = html.substring(3, html.length());
                matcherForAttrib.appendReplacement(sbreplace, searchAttrib + "=\"" + startStr + htmlUpdate);
            }
            // matcherForTag.appendReplacement(sb, sbreplace.toString());
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString() + ">");
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }
}
